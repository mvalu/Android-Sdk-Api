package com.mvalu.bettr_api.upi

import com.squareup.moshi.Json

class UPIGenerateTokenResult {
    @field:Json(name = "token_id")
    var tokenId: String? = null

    @field:Json(name = "encrypted_msg")
    var encryptedMsg: String? = null

    @field:Json(name = "sender_no")
    var senderNo: String? = null
}