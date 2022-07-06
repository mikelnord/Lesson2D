package com.gb.android.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.gb.android.core.adapter.PageAdapter
import com.gb.android.core.adapter.RESULT_PAGE_INDEX
import com.gb.android.core.adapter.TRANSLATION_SEARCH_PAGE_INDEX
import com.gb.android.core.databinding.FragmentViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator


class HomeViewPagerFragment : Fragment() {

    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        viewPager = binding.viewPager

        viewPager.adapter = PageAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        return binding.root
    }

    fun setResult(){
        viewPager.currentItem = 1
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            TRANSLATION_SEARCH_PAGE_INDEX -> R.drawable.translate_tab_selector
            RESULT_PAGE_INDEX -> R.drawable.result_list_selector
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            TRANSLATION_SEARCH_PAGE_INDEX -> getString(R.string.translate_title)
            RESULT_PAGE_INDEX -> getString(R.string.result_title)
            else -> null
        }
    }


}