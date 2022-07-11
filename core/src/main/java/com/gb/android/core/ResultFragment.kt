package com.gb.android.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gb.android.core.databinding.FragmentResultBinding
import com.gb.android.model.DataModel
import com.gb.android.model.Result
import com.gb.android.utils.viewById
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TranslationSearchViewModel by activityViewModels()
    private val recyclerview by viewById<RecyclerView>(R.id.recyclerResult)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        val data: List<DataModel> = ArrayList()
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerview.layoutManager=layoutManager

        val dividerItemDecoration = DividerItemDecoration(
            recyclerview.context,
            layoutManager.orientation
        )

        recyclerview.addItemDecoration(dividerItemDecoration)
        val resAdapter = ResultAdapter(
            data,
            ClickListener { dataModel: DataModel -> viewModel.onWordClicked(dataModel) })
        recyclerview.adapter = resAdapter
        viewModel.navigateToDetail.observe(viewLifecycleOwner) {
            it?.let {
                findNavController().navigate(
                    R.id.action_homeViewPagerFragment_to_detailSearchFragment
                )
                viewModel.doneNavigatingDetail()
            }
        }
        viewModel.searchList.observe(viewLifecycleOwner) { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    result.data?.let { list ->
                        resAdapter.setData(list)
                    }
                    binding.loading.visibility = View.GONE
                }

                Result.Status.ERROR -> {
                    result.message?.let {
                        showError(it)
                    }
                    binding.loading.visibility = View.GONE
                }

                Result.Status.LOADING -> {
                    binding.loading.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun showError(msg: String) {
        Snackbar.make(binding.resLayout, msg, Snackbar.LENGTH_INDEFINITE).setAction("DISMISS") {
        }.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}