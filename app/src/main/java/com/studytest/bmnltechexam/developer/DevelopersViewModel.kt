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
    private val mDevelopers: MutableLiveData<MutableList<Developer>> by lazy {
        MutableLiveData<MutableList<Developer>>()
    }

    private val mDisplayedDeveloper: MutableLiveData<Developer> by lazy {
        MutableLiveData<Developer>()
    }

    val developers: LiveData<MutableList<Developer>> = mDevelopers
    val displayedDeveloper: LiveData<Developer> = mDisplayedDeveloper

    fun requestDevelopers() {
        // TODO: Handle loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val developers = apiService.getDevelopers()

                withContext(Dispatchers.Main) {
                    mDevelopers.value = developers.toMutableList()
                }
            } catch (exception: Exception) {
                // TODO: Properly handle exceptions

                Log.d("DevelopersViewModel", "Error: ${exception.message}")
            }
        }
    }

    fun createDeveloper(developer: Developer) {
        // TODO: Handle loading

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val updatedDeveloper =
                    if (developer.id.isBlank()) apiService.addDeveloper(developer) else
                        apiService.editDeveloper(developer.id, developer)

                withContext(Dispatchers.Main) {
                    mDisplayedDeveloper.value = updatedDeveloper
                    mDevelopers.value = mDevelopers.value?.also { developerList ->
                        val developerIndex =
                            developerList.indexOfFirst { it.id == updatedDeveloper.id }
                        if (developerIndex != -1) {
                            developerList[developerIndex] = developer

                            return@also
                        }
                        developerList.add(developer)
                    }
                }
            } catch (exception: Exception) {
                // TODO: Properly handle exceptions

                Log.d("DevelopersViewModel", "Error: ${exception.message}")
            }
        }
    }
}