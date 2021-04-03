package com.studytest.bmnltechexam.developer

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.studytest.bmnltechexam.R
import com.studytest.bmnltechexam.databinding.FragmentDeveloperFormBinding
import com.studytest.bmnltechexam.views.BmnlToolbarFragment

class DeveloperFormFragment : Fragment(R.layout.fragment_developer_form) {
    companion object {
        const val TAG = "DeveloperFormFragment"
    }

    private lateinit var binding: FragmentDeveloperFormBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDeveloperFormBinding.bind(view)
        configureToolbar()
    }

    private fun configureToolbar() {
        val toolbarData = BmnlToolbarFragment.ToolbarData(
            hasBackButton = true,
            title = getString(R.string.add_contact)
        )
        val arguments = Bundle().apply {
            putParcelable(BmnlToolbarFragment.TOOLBAR_DATA, toolbarData)
        }

        childFragmentManager.commit {
            setReorderingAllowed(true)
            replace(binding.toolbarContainer.id, BmnlToolbarFragment::class.java, arguments)
        }
    }
}