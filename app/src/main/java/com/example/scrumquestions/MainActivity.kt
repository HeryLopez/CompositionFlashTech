package com.example.scrumquestions

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.scrumquestions.questionnaire.FriendshipQuestionsFragment
import com.example.scrumquestions.questionnaire.ScrumQuestionsFragment
import com.facebook.flipper.plugins.navigation.NavigationFlipperPlugin
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPref : SharedPreferences

    private fun flipperTest(calledFragmentName : String){
        NavigationFlipperPlugin.getInstance().sendNavigationEvent(calledFragmentName, "MainActivity", null)
        with (sharedPref.edit()) {
            putString("last_fragment", calledFragmentName)
            apply()
        }
    }

    private fun changeFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container_view, fragment)
            addToBackStack(null)
            commit()
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
                add<ScrumQuestionsFragment>(R.id.fragment_container_view)
            }

            bottom_navigation.setOnNavigationItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.page_1 -> {
                        flipperTest("showDevQuestions")
                        changeFragment(ScrumQuestionsFragment())
                        true
                    }
                    R.id.page_2 -> {
                        flipperTest("showFriendshipQuestions")
                        changeFragment(FriendshipQuestionsFragment())

                        true
                    }
                    else -> false
                }
            }
        }
    }
}