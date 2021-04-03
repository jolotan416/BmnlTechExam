package com.studytest.bmnltechexam.developer

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.studytest.bmnltechexam.R
import com.studytest.bmnltechexam.databinding.FragmentDeveloperListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeveloperListFragment(private val developerPageCallback: DeveloperPageCallback) :
    Fragment(R.layout.fragment_developer_list) {
    companion object {
        const val TAG = "DeveloperListFragment"
    }

    private lateinit var binding: FragmentDeveloperListBinding
    private val developersViewModel: DevelopersViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDeveloperListBinding.bind(view)
        configureViews()
        configureRecyclerView()
        initializeViewModel()
    }

    private fun configureViews() {
        binding.addDeveloperFab.setOnClickListener {
            developerPageCallback.showPage(DeveloperPage.DEVELOPER_FORM)
        }
    }

    private fun configureRecyclerView() {
        binding.developersRecyclerView.apply {
            adapter = DevelopersAdapter(developerPageCallback)
            setHasFixedSize(true)
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    outRect.bottom = resources.getDimensionPixelSize(R.dimen.dp_8)
                }
            })
        }
    }

    private fun initializeViewModel() {
        developersViewModel.apply {
            developers.observe(viewLifecycleOwner) { developers ->
                (binding.developersRecyclerView.adapter as DevelopersAdapter).setItems(developers)
            }

            requestDevelopers()
        }
    }
}