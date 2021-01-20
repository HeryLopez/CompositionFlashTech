package com.example.scrumquestions.questionnaire.source

import android.content.Context

import java.util.*
import com.example.scrumquestions.model.ScrumAnswer
import com.example.scrumquestions.model.ScrumQuestion

class InCodeBehavior :
    QuestionsSourceBehavior {

    override fun getQuestions(context: Context): MutableList<ScrumQuestion> {
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