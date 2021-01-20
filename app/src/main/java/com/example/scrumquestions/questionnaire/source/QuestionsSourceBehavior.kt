package com.example.scrumquestions.questionnaire.source

import android.content.Context
import com.example.scrumquestions.model.ScrumQuestion

interface QuestionsSourceBehavior {
    fun getQuestions(context: Context) : MutableList<ScrumQuestion>
}