package com.studytest.bmnltechexam

import androidx.fragment.app.FragmentFactory
import com.studytest.bmnltechexam.developer.DeveloperDetailsFragment
import com.studytest.bmnltechexam.developer.DeveloperFormFragment
import com.studytest.bmnltechexam.developer.DeveloperListFragment
import com.studytest.bmnltechexam.developer.DeveloperPageCallback

class MainFragmentFactory(private val developerPageCallback: DeveloperPageCallback) :
    FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String) =
        when (loadFragmentClass(classLoader, className)) {
            DeveloperListFragment::class.java -> DeveloperListFragment(developerPageCallback)
            DeveloperFormFragment::class.java -> DeveloperFormFragment(developerPageCallback)
            DeveloperDetailsFragment::class.java -> DeveloperDetailsFragment(developerPageCallback)
            else -> super.instantiate(classLoader, className)
        }
}