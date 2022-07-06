package com.gb.android.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gb.android.core.databinding.FragmentHistoryBinding
import com.gb.android.model.HistoryEntity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import com.gb.android.model.Result

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TranslationSearchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        val data: List<HistoryEntity> = ArrayList()
        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerHistory.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(
            binding.recyclerHistory.context,
            layoutManager.orientation
        )
        binding.recyclerHistory.addItemDecoration(dividerItemDecoration)
        val historyAdapter = HistoryAdapter(data)

        binding.recyclerHistory.adapter = historyAdapter

        viewModel.historyList.observe(viewLifecycleOwner) { result   ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    result.data?.let { list ->
                        historyAdapter.setData(list)
                    }
                    binding.loading.visibility = View.GONE
                }

                Result.Status.ERROR -> {
                    result.message?.let {
                        showError(it)
                    }
                    historyAdapter.setData(data)
                    binding.loading.visibility = View.GONE
                }

                Result.Status.LOADING -> {
                    binding.loading.visibility = View.VISIBLE
                }
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

    private fun showError(msg: String) {
        Snackbar.make(binding.historyLayout, msg, Snackbar.LENGTH_INDEFINITE).setAction("DISMISS") {
        }.show()
    }

}