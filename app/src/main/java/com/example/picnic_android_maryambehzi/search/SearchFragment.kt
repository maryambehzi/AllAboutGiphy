package com.example.picnic_android_maryambehzi.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
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
import java.lang.reflect.Executable

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
                binding.searchBarEdittext.clearFocus()
            }
        })

        viewModel.urlLink.observe(viewLifecycleOwner, Observer { url ->
            url?.let {
                if (it.isNotEmpty())
                    try {
                        openBrowser(it)
                    }catch (e: Exception){
                        print(e)
                    }

            }
        })

        binding.searchBarEdittext.setOnKeyListener{ v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {
                when (keyCode) {
                    KeyEvent.KEYCODE_ENTER ,
                    KeyEvent.KEYCODE_NUMPAD_ENTER -> {
                        viewModel.search()
                    }
                }
            }
            false
        }

        if (binding.searchBarEdittext.hasFocus())
            viewModel.showSearchResults()

        binding.viewModel = viewModel

        return binding.root
    }

    fun openBrowser(url : String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

}