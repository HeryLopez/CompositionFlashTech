package com.example.scrumquestions.questionnaire

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuestionsViewModel : ViewModel() {
    private val currentQuestions: MutableLiveData<QuestionsEnum> =
        MutableLiveData(QuestionsEnum.DEV)

    fun getCurrentQuestions(): LiveData<QuestionsEnum> {
        return currentQuestions
    }

    fun showDevQuestions() {
        currentQuestions.value =
            QuestionsEnum.DEV
    }

    fun showFriendshipQuestions() {
        currentQuestions.value =
            QuestionsEnum.FRIEND_SHIP
    }
}

enum class QuestionsEnum {
    DEV, FRIEND_SHIP
}
    