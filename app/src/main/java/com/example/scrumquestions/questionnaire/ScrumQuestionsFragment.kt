package com.example.scrumquestions.questionnaire

import android.os.Bundle
import android.view.View
import com.example.scrumquestions.questionnaire.source.JsonBehavior

class ScrumQuestionsFragment : AbstractQuestionsFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        questionsSourceBehavior = JsonBehavior()
        localAdapter.updateList(questionsSourceBehavior.getQuestions(requireContext()))
        localAdapter.notifyDataSetChanged()
    }
}