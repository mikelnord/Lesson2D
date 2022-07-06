package com.gb.android.core.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gb.android.core.ResultFragment
import com.gb.android.core.TranslationSearchFragment

const val TRANSLATION_SEARCH_PAGE_INDEX = 0
const val RESULT_PAGE_INDEX = 1

class PageAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        TRANSLATION_SEARCH_PAGE_INDEX to { TranslationSearchFragment() },
        RESULT_PAGE_INDEX to { ResultFragment() }
    )

    override fun getItemCount(): Int = tabFragmentsCreators.size


    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }

}