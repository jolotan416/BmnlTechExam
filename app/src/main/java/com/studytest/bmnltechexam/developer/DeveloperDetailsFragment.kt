package com.studytest.bmnltechexam.developer

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import com.studytest.bmnltechexam.R
import com.studytest.bmnltechexam.databinding.FragmentDeveloperDetailsBinding
import com.studytest.bmnltechexam.views.BmnlToolbarFragment

class DeveloperDetailsFragment(private val developerPageCallback: DeveloperPageCallback) :
    Fragment(R.layout.fragment_developer_details) {
    companion object {
        const val TAG = "DeveloperDetailsFragment"
    }

    private lateinit var binding: FragmentDeveloperDetailsBinding

    private val developersViewModel: DevelopersViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDeveloperDetailsBinding.bind(view)
        configureViews()
        observeViewModel()
    }

    private fun configureViews() {
        configureToolbar()
        configureButtons()
    }

    private fun configureToolbar() {
        val toolbarData = BmnlToolbarFragment.ToolbarData(hasBackButton = true, title = null)
        val arguments = Bundle().apply {
            putParcelable(BmnlToolbarFragment.TOOLBAR_DATA, toolbarData)
        }
        childFragmentManager.commit {
            setReorderingAllowed(true)
            replace(binding.toolbarContainer.id, BmnlToolbarFragment::class.java, arguments)
        }
    }

    private fun configureButtons() {
        binding.apply {
            editButton.setOnClickListener {
                developerPageCallback.showPage(DeveloperPage.DEVELOPER_FORM)
            }
        }
    }

    private fun observeViewModel() {
        developersViewModel.displayedDeveloper.observe(viewLifecycleOwner) { displayedDeveloper ->
            binding.developer = displayedDeveloper
        }
    }
}