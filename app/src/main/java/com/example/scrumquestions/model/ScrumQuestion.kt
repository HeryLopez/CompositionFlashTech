package com.example.scrumquestions.model

data class ScrumQuestion(val question: String, val answers: List<ScrumAnswer>){
    @Transient
    var alreadyValidated : Boolean = false
}