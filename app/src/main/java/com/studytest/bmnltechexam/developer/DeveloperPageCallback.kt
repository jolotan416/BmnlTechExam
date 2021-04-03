package com.studytest.bmnltechexam.developer

import android.os.Bundle
import androidx.fragment.app.Fragment

interface DeveloperPageCallback {
    fun showPage(developerPage: DeveloperPage, arguments: Bundle? = null)
}

enum class DeveloperPage(
    val fragmentClass: Class<out Fragment>,
    val tag: String,
    val willAddToBackStack: Boolean = true
) {
    DEVELOPER_LIST(DeveloperListFragment::class.java, DeveloperListFragment.TAG, false),
    DEVELOPER_FORM(DeveloperFormFragment::class.java, DeveloperFormFragment.TAG)
}

enum class DeveloperPageArgument(val argumentName: String) {
    DEVELOPER("developer")
}