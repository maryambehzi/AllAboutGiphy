package com.example.picnic_android_maryambehzi.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.picnic_android_maryambehzi.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = FragmentSearchBinding.inflate(inflater)

        binding.lifecycleOwner = this

        return binding.root
    }

}