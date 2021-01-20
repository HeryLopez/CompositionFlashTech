package com.example.scrumquestions.questionnaire.source

import android.content.Context

import java.util.*
import android.util.Log
import com.example.scrumquestions.Utils
import com.example.scrumquestions.model.ScrumQuestion

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import java.lang.reflect.Type

class JsonBehavior :
    QuestionsSourceBehavior {

    override fun getQuestions(context: Context): MutableList<ScrumQuestion> {
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