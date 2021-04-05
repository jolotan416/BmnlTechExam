package com.studytest.bmnltechexam

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.studytest.bmnltechexam.developer.DeveloperPage
import com.studytest.bmnltechexam.developer.DeveloperPageCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main), DeveloperPageCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = MainFragmentFactory(this)
        super.onCreate(savedInstanceState)

        showPage(DeveloperPage.DEVELOPER_LIST)
    }

    override fun showPage(developerPage: DeveloperPage) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            developerPage.let {
                replace(R.id.fragmentContainer, it.fragmentClass, null, it.tag)
                if (it.willAddToBackStack) {
                    addToBackStack(it.tag)
                }
            }
        }
    }
}