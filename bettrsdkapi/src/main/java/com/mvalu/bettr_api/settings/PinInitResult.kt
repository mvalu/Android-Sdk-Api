package com.mvalu.bettr_api.settings

import com.squareup.moshi.Json

class PinInitResult {

    @field:Json(name = "public_encryption_key")
    var encryptionKey: String? = null

    @field:Json(name = "public_encryption_key_der")
    var encryptionKeyDer: String? = null

    @field:Json(name = "pin_set_token")
    var pinSetToken: String? = null
}