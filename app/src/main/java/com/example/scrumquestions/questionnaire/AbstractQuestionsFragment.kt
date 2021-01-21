package com.example.scrumquestions.questionnaire

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scrumquestions.R
import com.example.scrumquestions.questionnaire.adapter.QuestionsAdapter
import com.example.scrumquestions.questionnaire.source.QuestionsSourceBehavior

abstract class AbstractQuestionsFragment : Fragment(R.layout.fragment_scrum_dev_questions) {

    private lateinit var questionsSourceBehavior: QuestionsSourceBehavior

    val viewModel: QuestionsViewModel by activityViewModels()

    val localAdapter =
        QuestionsAdapter(
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

    fun setSourceBehavior(questionsSourceBehavior : QuestionsSourceBehavior){
        this.questionsSourceBehavior = questionsSourceBehavior
    }

    fun getQuestionsData() = questionsSourceBehavior.getQuestions(requireContext())
}