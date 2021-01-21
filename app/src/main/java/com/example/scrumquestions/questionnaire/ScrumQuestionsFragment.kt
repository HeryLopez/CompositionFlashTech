package com.example.scrumquestions.questionnaire

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.scrumquestions.Utils
import com.example.scrumquestions.model.ScrumQuestion
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.ArrayList

class ScrumQuestionsFragment : AbstractQuestionsFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        localAdapter.updateList(
            getScrumQuestions(requireContext())
        )
        localAdapter.notifyDataSetChanged()
    }

    /**
     * Problems
     * - Code duplication if another class needs the same behavior
     * - If it is used as a base class for other children, it complicates the understanding of the code (Various levels of inheritance)
     */
    fun getScrumQuestions2(context: Context): MutableList<ScrumQuestion> {
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
}