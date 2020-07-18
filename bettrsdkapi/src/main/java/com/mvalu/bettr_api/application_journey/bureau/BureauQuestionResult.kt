package com.mvalu.bettr_api.application_journey.bureau

import com.squareup.moshi.Json

class BureauQuestionResult {
    @field:Json(name = "ChallengeConfigGUID")
    var challengeConfigGUID: String? = null

    @field:Json(name = "QueueName")
    var queueName: String? = null

    @field:Json(name = "question")
    var question: List<Question>? = null

    class Question {
        @field:Json(name = "LastChanceQuestion")
        var lastChanceQuestion: Boolean? = null

        @field:Json(name = "FullQuestionText")
        var fullQuestionText: String? = null

        @field:Json(name = "Key")
        var key: String? = null

        @field:Json(name = "skipEligible")
        var skipEligible: Boolean? = null

        @field:Json(name = "resendEligible")
        var resendEligible: Boolean? = null

        @field:Json(name = "selectMultiple")
        var selectMultiple: Boolean? = null

        @field:Json(name = "AnswerChoice")
        var answerChoice: List<AnswerChoice>? = null
    }

    class AnswerChoice {
        @field:Json(name = "AnswerChoiceText")
        var answerChoiceText: String? = null

        @field:Json(name = "Key")
        var key: String? = null

        @field:Json(name = "AnswerChoiceId")
        var answerChoiceId: String? = null

        @field:Json(name = "Range")
        var range: Range? = null
    }

    class Range {
        @field:Json(name = "UpperLimit")
        var upperLimit: String? = null

        @field:Json(name = "LowerLimit")
        var lowerLimit: String? = null
    }
}