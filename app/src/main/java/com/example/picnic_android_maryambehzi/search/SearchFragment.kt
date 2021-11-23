package com.example.picnic_android_maryambehzi.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.picnic_android_maryambehzi.R
import com.example.picnic_android_maryambehzi.databinding.FragmentSearchBinding
import com.example.picnic_android_maryambehzi.utils.PhotoGridAdapter

class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by lazy {
        ViewModelProvider(this).get(SearchViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = FragmentSearchBinding.inflate(inflater)

        binding.lifecycleOwner = requireActivity()

        viewModel.textWatcherSearch(binding.searchBarEdittext)

        binding.photosGrid.adapter = PhotoGridAdapter(PhotoGridAdapter.OnClickListener {
            viewModel.displayPropertyDetails(it)
        })

        viewModel.navigateToSelectedGif.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                this.findNavController().navigate(SearchFragmentDirections.actionShowDetail(it))
                viewModel.displayGifDetailsComplete()
            }
        })

        viewModel.clearSearchBar.observe(viewLifecycleOwner, Observer {
            if (it){
                binding.searchBarEdittext.text = null
            }
        })

        binding.viewModel = viewModel

        return binding.root
    }

}