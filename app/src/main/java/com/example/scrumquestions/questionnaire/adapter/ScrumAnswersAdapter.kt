package com.example.scrumquestions.questionnaire.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.annotation.ColorRes
import com.example.scrumquestions.R
import com.example.scrumquestions.model.ScrumAnswer

class ScrumAnswersAdapter(private val values: List<ScrumAnswer>) :
    RecyclerView.Adapter<ScrumAnswersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item_answer, parent, false)
        return ViewHolder(view)
    }

    fun setShowCorrectAnswer(showCorrectAnswer: Boolean) {
        for (i in values) {
            i.showAnswer = showCorrectAnswer
        }

        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.answer.text = item.answer
        holder.answer.setOnCheckedChangeListener { _, isChecked ->
            item.userSelection = isChecked
        }

        holder.answer.isChecked = item.userSelection

        showResult(item, holder)
        showUserSelection(item, holder)
    }

    private fun showResult(item: ScrumAnswer, holder: ViewHolder) {
        holder.result.visibility = View.GONE
        if (item.showAnswer) {
            holder.result.visibility = View.INVISIBLE
            if (item.userSelection) {
                if (item.userSelection && item.isCorrectAnswer()) {
                    holder.result.setImageResource(R.drawable.ic_check)
                } else {
                    holder.result.setImageResource(R.drawable.ic_uncheck)
                }
                holder.result.visibility = View.VISIBLE
            }
        }
    }

    private fun showUserSelection(item: ScrumAnswer, holder: ViewHolder){
        if (item.showAnswer) {
            if (item.isCorrectAnswer()) {
                changeCheckboxColor(holder.answer,
                    R.color.colorAccent
                )
            } else {
                changeCheckboxColor(holder.answer,
                    R.color.colorNormalTextColor
                )
            }
            holder.answer.isEnabled = false
        } else {
            changeCheckboxColor(holder.answer,
                R.color.colorNormalTextColor
            )
            holder.answer.isEnabled = true
        }
    }

    private fun changeCheckboxColor(checkBox: CheckBox, @ColorRes colorAccent: Int) {
        checkBox.setTextColor(checkBox.context.resources.getColor(colorAccent, null))
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val answer: CheckBox = view.findViewById(R.id.answer)
        val result: ImageView = view.findViewById(R.id.image_view_result)
    }
}