package com.graisie.weatherapp.presentation.cities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.graisie.weatherapp.R
import com.graisie.weatherapp.databinding.FragmentCitiesBinding
import com.graisie.weatherapp.databinding.ItemCityBinding
import com.graisie.weatherapp.presentation.cities.model.CityUiEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CitiesFragment : Fragment() {

    private lateinit var binding: FragmentCitiesBinding
    private lateinit var adapter: CitiesAdapter

    private val viewModel: CitiesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCitiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCities.layoutManager = LinearLayoutManager(requireContext())
        adapter = CitiesAdapter { city ->
            findNavController().navigate(
                R.id.weatherFragment, bundleOf("city" to city)
            )
        }
        binding.rvCities.adapter = adapter
        binding.search.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    // no-op
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel.searchCities(newText)
                    return true
                }
            }
        )

        observeStates()
    }

    private fun observeStates() {
        lifecycleScope.launch {
            viewModel.state
                .map { it.isError }
                .filter { it }
                .collectLatest {
                    Toast.makeText(requireContext(), R.string.error_msg, Toast.LENGTH_LONG).show()
                    viewModel.onErrorShown()
                }
        }
        lifecycleScope.launch {
            viewModel.state
                .map { it.isLoading }
                .distinctUntilChanged()
                .collectLatest { binding.progressBar.isVisible = it }
        }
        lifecycleScope.launch {
            viewModel.state
                .map { it.cities }
                .distinctUntilChanged()
                .collectLatest { adapter.submitList(it) }
        }
    }

    private class CitiesAdapter(
        private val onItemClickListener: (CityUiEntity) -> Unit,
    ) : RecyclerView.Adapter<CityViewHolder>() {

        private var cities: List<CityUiEntity> = listOf()

        @SuppressLint("NotifyDataSetChanged")
        fun submitList(newList: List<CityUiEntity>) {
            this.cities = newList
            // DiffUtil is too slow for large list, using old but efficient notifyDataSetChanged()
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemCityBinding.inflate(inflater, parent, false)
            return CityViewHolder(binding)
        }

        override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
            cities[position].apply { holder.bind(this, onItemClickListener) }
        }

        override fun getItemCount(): Int {
            return cities.size
        }
    }

    private class CityViewHolder(
        private val binding: ItemCityBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(city: CityUiEntity, onItemClickListener: (CityUiEntity) -> Unit) {
            binding.tvCityName.text = city.name
            binding.root.setOnClickListener { onItemClickListener(city) }
            Glide.with(binding.root.context)
                .load(city.image)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(binding.ivCityImage)
        }
    }
}
