package com.example.scrumquestions.questionnaire

import android.os.Bundle
import android.view.View
import com.example.scrumquestions.questionnaire.source.InCodeBehavior

class FriendshipQuestionsFragment : AbstractQuestionsFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        questionsSourceBehavior = InCodeBehavior()
        localAdapter.updateList(questionsSourceBehavior.getQuestions(requireContext()))
        localAdapter.notifyDataSetChanged()
    }
}