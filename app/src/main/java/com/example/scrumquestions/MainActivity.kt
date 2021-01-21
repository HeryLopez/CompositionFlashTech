package com.example.scrumquestions

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.scrumquestions.questionnaire.QuestionsViewModel
import com.example.scrumquestions.questionnaire.ScrumQuestionsFragment
import com.facebook.flipper.plugins.navigation.NavigationFlipperPlugin
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val viewModel: QuestionsViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPref = this.getSharedPreferences(
            MyApplication.sharePreferente, Context.MODE_PRIVATE)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<ScrumQuestionsFragment>(R.id.fragment_container_view)
            }

            bottom_navigation.setOnNavigationItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.page_1 -> {
                        NavigationFlipperPlugin.getInstance().sendNavigationEvent("showDevQuestions", "MainActivity", null)
                        with (sharedPref.edit()) {
                            putString("last_fragment", "showDevQuestions")
                            apply()
                        }

                        viewModel.showDevQuestions()
                        true
                    }
                    R.id.page_2 -> {
                        NavigationFlipperPlugin.getInstance().sendNavigationEvent("showFriendshipQuestions", "MainActivity", null)
                        with (sharedPref.edit()) {
                            putString("last_fragment", "showFriendshipQuestions")
                            apply()
                        }
                        viewModel.showFriendshipQuestions()
                        true
                    }
                    else -> false
                }
            }
        }
    }
}