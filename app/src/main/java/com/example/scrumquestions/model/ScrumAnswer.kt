package com.example.scrumquestions.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ScrumAnswer(
    @Expose
    @SerializedName("ok")
    private val isCorrectAnswer: String,
    @Expose
    @SerializedName("text")
    val answer: String){

    @Transient
    var userSelection : Boolean = false

    @Transient
    var showAnswer : Boolean = false

    fun isCorrectAnswer() : Boolean {
        return isCorrectAnswer == "Y"
    }
}