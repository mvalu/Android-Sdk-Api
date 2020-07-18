package com.mvalu.bettr_api.application_journey.bureau

import com.squareup.moshi.Json

class BureauAnswerRequest {
    @field:Json(name = "userId")
    var userId: String? = null

    @field:Json(name = "applicationId")
    var applicationId: String? = null

    @field:Json(name = "ChallengeConfigGUID")
    var challengeConfigGUID: String? = null

    @field:Json(name = "answer")
    var answer: List<Answer>? = null

    class Answer(
        questionKey: String,
        resendOTP: Boolean,
        userInputAnswer: String,
        answerKey: List<String>,
        skipQuestion: Boolean
    ) {
        @field:Json(name = "questionKey")
        var questionKey: String? = questionKey

        @field:Json(name = "skipQuestion")
        var skipQuestion: Boolean? = null

        @field:Json(name = "resendOTP")
        var resendOTP: Boolean? = resendOTP

        @field:Json(name = "UserInputAnswer")
        var userInputAnswer: String? = userInputAnswer

        @field:Json(name = "answerKey")
        var answerKey: List<String>? = answerKey
    }
}