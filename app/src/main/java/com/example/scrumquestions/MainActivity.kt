package com.example.scrumquestions

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.scrumquestions.questionnaire.QuestionsViewModel
import com.example.scrumquestions.questionnaire.QuestionsFragment
import com.facebook.flipper.plugins.navigation.NavigationFlipperPlugin
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: QuestionsViewModel by viewModels()

    private lateinit var sharedPref : SharedPreferences

    private fun flipperTest(calledFragmentName : String){
        NavigationFlipperPlugin.getInstance().sendNavigationEvent(calledFragmentName, "MainActivity", null)
        with (sharedPref.edit()) {
            putString("last_fragment", calledFragmentName)
            apply()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPref = this.getSharedPreferences(
            MyApplication.sharePreferente, Context.MODE_PRIVATE)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<QuestionsFragment>(R.id.fragment_container_view)
            }

            bottom_navigation.setOnNavigationItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.page_1 -> {
                        flipperTest("showDevQuestions")
                        viewModel.showDevQuestions()
                        true
                    }
                    R.id.page_2 -> {
                        flipperTest("showFriendshipQuestions")
                        viewModel.showFriendshipQuestions()
                        true
                    }
                    else -> false
                }
            }
        }
    }
}