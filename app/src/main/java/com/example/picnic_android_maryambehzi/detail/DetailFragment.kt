package com.example.picnic_android_maryambehzi.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.picnic_android_maryambehzi.databinding.FragmentDetailsBinding

class DetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val application = requireNotNull(activity).application

        val binding = FragmentDetailsBinding.inflate(inflater)

        val gifModel = DetailFragmentArgs.fromBundle(requireArguments()).gifModel

        val viewModelFactory = DetailViewModelFactory(gifModel, application)

        val viewModel = ViewModelProvider(
            this, viewModelFactory).get(DetailViewModel::class.java)

        binding.viewModel = viewModel

        viewModel.backIsPresses.observe(viewLifecycleOwner, Observer { isPressed ->
            if (isPressed){
                view?.let { view -> Navigation.findNavController(view).popBackStack() }
            }
        })

        viewModel.openUrlLink.observe(viewLifecycleOwner, Observer { isClicked ->
            isClicked?.let {
                if (it)
                    try {
                        openBrowser(viewModel.selectedGif.value?.url)
                        viewModel.linkHasBeenShowed()
                    }catch (e: Exception){
                        print(e)
                    }

            }
        })


        binding.lifecycleOwner = requireActivity()

        return binding.root
    }

    private fun openBrowser(url : String?) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}