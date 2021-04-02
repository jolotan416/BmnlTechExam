package com.studytest.bmnltechexam

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.studytest.bmnltechexam.developer.DeveloperListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        attachFragment(DeveloperListFragment::class.java, DeveloperListFragment.TAG)
    }

    private fun attachFragment(
        fragmentClass: Class<out Fragment>,
        tag: String,
        arguments: Bundle? = null,
    ) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragmentContainer, fragmentClass, arguments, tag)
        }
    }
}