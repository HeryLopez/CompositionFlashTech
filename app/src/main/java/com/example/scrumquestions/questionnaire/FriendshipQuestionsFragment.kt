package com.example.scrumquestions.questionnaire

import android.os.Bundle
import android.view.View
import com.example.scrumquestions.model.ScrumAnswer
import com.example.scrumquestions.model.ScrumQuestion
import java.util.ArrayList

class FriendshipQuestionsFragment : AbstractQuestionsFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        localAdapter.updateList(
            getFriendshipQuestions()
        )
        localAdapter.notifyDataSetChanged()
    }

    /**
     * Problems
     * - Code duplication if another class needs the same behavior
     * - If it is used as a base class for other children, it complicates the understanding of the code (Various levels of inheritance)
     */
    fun getFriendshipQuestions2(): MutableList<ScrumQuestion> {
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