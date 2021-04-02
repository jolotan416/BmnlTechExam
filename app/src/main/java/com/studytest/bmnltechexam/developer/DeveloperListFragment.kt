package com.studytest.bmnltechexam.developer

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.studytest.bmnltechexam.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeveloperListFragment : Fragment(R.layout.fragment_developer_list) {
    companion object {
        const val TAG = "DeveloperListFragment"
    }

    private val developersViewModel: DevelopersViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViewModel()
    }

    private fun initializeViewModel() {
        developersViewModel.apply {
            developers.observe(viewLifecycleOwner) {
                // TODO: Show developers in RecyclerView
                Log.d(TAG, "Developers: $it")
            }

            requestDevelopers()
        }
    }
}