package com.gb.android.core

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.gb.android.core.databinding.FragmentTranslationSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TranslationSearchFragment : Fragment() {

    private lateinit var _binding: FragmentTranslationSearchBinding
    private val binding get() = _binding
    private val viewModel: TranslationSearchViewModel by activityViewModels()


    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(p0: Editable?) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            if (binding.searchEditText.text != null && binding.searchEditText.text.toString()
                    .isNotEmpty()
            ) {
                binding.searchButton.isEnabled = true
                binding.historyButton.isEnabled = true
                binding.clearTextButton.visibility = View.VISIBLE
            } else {
                binding.searchButton.isEnabled = false
                binding.historyButton.isEnabled = false
                binding.clearTextButton.visibility = View.GONE
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTranslationSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchEditText.addTextChangedListener(textWatcher)
        binding.searchButton.setOnClickListener { onButtonSearchClick() }
        addOnClearClickListener()
        binding.historyButton.setOnClickListener {
            onButtonHistoryClick()
        }
        viewModel.navigateToHistory.observe(viewLifecycleOwner) {
            it?.let {
                findNavController().navigate(
                    R.id.action_homeViewPagerFragment_to_historyFragment
                )
                viewModel.doneNavigatingHistory()
            }
        }

    }

    private fun onButtonHistoryClick() {
        viewModel.history(binding.searchEditText.text.toString())
    }

    private fun onButtonSearchClick() {
        viewModel.start(binding.searchEditText.text.toString())
        val home = parentFragment as HomeViewPagerFragment
        home.setResult()
    }


    private fun addOnClearClickListener() {
        binding.clearTextButton.setOnClickListener {
            binding.searchEditText.setText("")
            binding.searchButton.isEnabled = false
            binding.historyButton.isEnabled = false
        }
    }

}