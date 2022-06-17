package com.gb.android.lesson2d.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.gb.android.lesson2d.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class TranslationSearchViewModel @Inject constructor(private val repository: Repository) :
    ViewModel() {

    private val _wordToSearch = MutableLiveData<String>()
    private val _searchList = _wordToSearch.switchMap { id ->
        repository.search(id)
    }
    val searchList = _searchList

    fun start(wordToSearch: String) {
        _wordToSearch.value = wordToSearch
    }
}