package com.studytest.bmnltechexam.views

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.Fragment
import com.studytest.bmnltechexam.R
import com.studytest.bmnltechexam.databinding.FragmentBmnlToolbarBinding
import kotlinx.parcelize.Parcelize

class BmnlToolbarFragment : Fragment(R.layout.fragment_bmnl_toolbar) {
    companion object {
        const val TOOLBAR_DATA = "toolbar_data"
    }

    private lateinit var binding: FragmentBmnlToolbarBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbarData = (requireArguments().getParcelable(TOOLBAR_DATA) as? ToolbarData) ?: return

        binding = FragmentBmnlToolbarBinding.bind(view)
        configureViews(toolbarData)
    }

    private fun configureViews(toolbarData: ToolbarData) {
        binding.apply {
            this.toolbarData = toolbarData
            backButton.setOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }

    @Parcelize
    data class ToolbarData(
        val hasBackButton: Boolean,
        val title: String?
    ) : Parcelable {
        val hasTitle: Boolean
            get() = !title.isNullOrBlank()
    }
}