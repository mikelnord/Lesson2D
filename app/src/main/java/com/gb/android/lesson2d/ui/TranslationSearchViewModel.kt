package com.gb.android.lesson2d.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.gb.android.lesson2d.data.Repository
import com.gb.android.lesson2d.model.DataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class TranslationSearchViewModel @Inject constructor(private val repository: Repository) :
    ViewModel() {

    lateinit var toDataModel: DataModel
    private val _wordToSearch = MutableLiveData<String>()
    private val _searchList = _wordToSearch.switchMap { id ->
        repository.search(id)
    }
    val searchList = _searchList

    private val _navigateToDetail = MutableLiveData<Boolean?>()
    val navigateToDetail
        get() = _navigateToDetail

    fun doneNavigating() {
        _navigateToDetail.value = null
    }

    fun onWordClicked(dataModel: DataModel) {
        toDataModel=dataModel
        _navigateToDetail.value = true
    }


    fun start(wordToSearch: String) {
        _wordToSearch.value = wordToSearch
    }
}