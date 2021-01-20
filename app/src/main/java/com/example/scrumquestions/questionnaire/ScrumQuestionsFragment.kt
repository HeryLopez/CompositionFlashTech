package com.example.scrumquestions.questionnaire

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.scrumquestions.questionnaire.source.InCodeBehavior
import com.example.scrumquestions.questionnaire.source.JsonBehavior

class ScrumQuestionsFragment : AbstractQuestionsFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCurrentQuestions().observe(viewLifecycleOwner, Observer<QuestionsEnum>{ currentQuestions ->

            if(currentQuestions == QuestionsEnum.FRIEND_SHIP){
                setSourceBehavior(InCodeBehavior())
            } else {
                setSourceBehavior(JsonBehavior())
            }

            localAdapter.updateList(
                getQuestionsData()
            )
            localAdapter.notifyDataSetChanged()
        })
    }
}