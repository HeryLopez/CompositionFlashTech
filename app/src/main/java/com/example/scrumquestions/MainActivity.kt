package com.example.scrumquestions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.scrumquestions.questionnaire.QuestionsViewModel
import com.example.scrumquestions.questionnaire.ScrumQuestionsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: QuestionsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<ScrumQuestionsFragment>(R.id.fragment_container_view)
            }

            bottom_navigation.setOnNavigationItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.page_1 -> {
                        viewModel.showDevQuestions()
                        true
                    }
                    R.id.page_2 -> {
                        viewModel.showFriendshipQuestions()
                        true
                    }
                    else -> false
                }
            }
        }
    }
}