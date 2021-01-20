package com.example.scrumquestions.questionnaire

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.scrumquestions.R
import com.example.scrumquestions.model.ScrumQuestion

class ScrumQuestionsAdapter(private var values: List<ScrumQuestion>) :
    RecyclerView.Adapter<ScrumQuestionsAdapter.ViewHolder>() {

    fun updateList(newValues: List<ScrumQuestion>){
        values = newValues
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item_question, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.question.text = item.question

        with(holder.answers) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            adapter =
                ScrumAnswersAdapter(item.answers)
        }

        if(item.alreadyValidated){
            holder.verifyButton.visibility = View.GONE
        } else {
            holder.verifyButton.visibility = View.VISIBLE
            holder.verifyButton.setOnClickListener {
                holder.answers.adapter.apply {
                    this as ScrumAnswersAdapter
                    this.setShowCorrectAnswer(true)
                }
                item.alreadyValidated = true
                notifyItemChanged(position)
            }
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val question: TextView = view.findViewById(R.id.text_view_question)
        val answers: RecyclerView = view.findViewById(R.id.recycler_view_answers)
        val verifyButton: Button = view.findViewById(R.id.button_verify)

    }
}