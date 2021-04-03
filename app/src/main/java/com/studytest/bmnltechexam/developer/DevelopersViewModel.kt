package com.studytest.bmnltechexam.developer

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studytest.bmnltechexam.data.developer.Developer
import com.studytest.bmnltechexam.httpclient.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DevelopersViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {
    private val mDevelopers: MutableLiveData<List<Developer>> by lazy {
        MutableLiveData<List<Developer>>()
    }

    val developers: LiveData<List<Developer>> = mDevelopers

    fun requestDevelopers() {
        // TODO: Handle loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val developers = apiService.getDevelopers()

                withContext(Dispatchers.Main) {
                    mDevelopers.value = developers
                }
            } catch (exception: Exception) {
                // TODO: Properly handle exceptions

                Log.d("DevelopersViewModel", "Error: ${exception.message}")
            }
        }
    }
}