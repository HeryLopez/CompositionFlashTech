package com.example.scrumquestions.questionnaire

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scrumquestions.R
import com.example.scrumquestions.Utils
import com.example.scrumquestions.model.ScrumAnswer
import com.example.scrumquestions.model.ScrumQuestion
import com.example.scrumquestions.questionnaire.adapter.ScrumQuestionsAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.ArrayList

abstract class AbstractQuestionsFragment : Fragment(R.layout.fragment_scrum_dev_questions) {

    val localAdapter =
        ScrumQuestionsAdapter(
            listOf()
        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (view is RecyclerView) {
            val layoutManager = LinearLayoutManager(context)
            with(view) {
                this.layoutManager = layoutManager
                adapter = localAdapter
                addItemDecoration(DividerItemDecoration(
                    this.context,
                    layoutManager.orientation
                ))
            }
        }
    }


    /**
     * Problem : add new class or modify existing can add regressions
     */
    fun getQuestionsData(context: Context) : MutableList<ScrumQuestion>{

        /*
        * Sometimes there is common code of the different fragments, which increases the risk of regression
        * For example
        * - Change the common code to fix something from fragment A
        * - This change impacts fragment B. As fragment B is not part of the changes it will not be tested
        * */


        if(this is ScrumQuestionsFragment){
            return getScrumQuestions(context)
        }

        if(this is FriendshipQuestionsFragment){
            return getFriendshipQuestions()
        }

        return mutableListOf()
    }


    /**
     * Problems :
     * - The code is only used by certain classes
     * - More complex to read the code of this class
     * - DO NOT speak if the class is defined as abstract: duplication of code or empty methods
     *     override fun getScrumQuestions(){
     *         // Do nothing
     *     }
     *
     */
    fun getScrumQuestions(context: Context): MutableList<ScrumQuestion> {
        val items: MutableList<ScrumQuestion> = ArrayList()

        val jsonFileString: String? =
            Utils.getJsonFromAssets(
                context,
                "scrum_dev_question.json"
            )
        val listUserType: Type = object : TypeToken<List<ScrumQuestion>?>() {}.type

        val questions: List<ScrumQuestion> = Gson().fromJson(jsonFileString, listUserType)
        for (i in questions.indices) {
            Log.i("data", "> Item $i${questions[i]}".trimIndent())
            items.add(questions[i])
        }

        return items;
    }

    /**
     * Problems :
     * - The code is only used by certain classes
     * - More complex to read the code of this class
     * - DO NOT speak if the class is defined as abstract: duplication of code or empty methods
     *     override fun getFriendshipQuestions(){
     *         // Do nothing
     *     }
     *
     */
    fun getFriendshipQuestions(): MutableList<ScrumQuestion> {
        val items: MutableList<ScrumQuestion> = ArrayList()

        val answer1 = listOf(ScrumAnswer("Y", "Me plaindre" ), ScrumAnswer("N", "Exercice" ))
        val question1 = ScrumQuestion("Quelle est la première chose que je fais le matin ?", answer1)

        val answer2 = listOf(ScrumAnswer("N", " Télépathie" ), ScrumAnswer("Y", "Ne pas travailler" ))
        val question2 = ScrumQuestion("Si j'avais un super pouvoir quel serait-il ?", answer2)

        val answer3 = listOf(ScrumAnswer("Y", "0" ), ScrumAnswer("N", "1" ), ScrumAnswer("N", ">2" ))
        val question3 = ScrumQuestion("Combien d'enfants est ce que je voudrais ?", answer3)

        items.add(question1)
        items.add(question2)
        items.add(question3)

        return items
    }
}