package com.gb.android.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.gb.android.core.databinding.FragmentDetailSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailSearchFragment : Fragment() {

    private var _binding: FragmentDetailSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TranslationSearchViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.wordTextView.text = viewModel.toDataModel.text
        binding.translationTextView.text =
            viewModel.toDataModel.meanings?.get(0)?.translation?.translation
        Glide.with(this)
            .load("https:${viewModel.toDataModel.meanings?.get(0)?.imageUrl}")
            .placeholder(R.drawable.loading_spinner)
            .into(binding.imageView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}