package com.studytest.bmnltechexam.developer

import androidx.fragment.app.Fragment

interface DeveloperPageCallback {
    fun showPage(developerPage: DeveloperPage)
}

enum class DeveloperPage(
    val fragmentClass: Class<out Fragment>,
    val tag: String,
    val willAddToBackStack: Boolean = true
) {
    DEVELOPER_LIST(DeveloperListFragment::class.java, DeveloperListFragment.TAG, false),
    DEVELOPER_FORM(DeveloperFormFragment::class.java, DeveloperFormFragment.TAG),
    DEVELOPER_DETAILS(DeveloperDetailsFragment::class.java, DeveloperDetailsFragment.TAG)
}