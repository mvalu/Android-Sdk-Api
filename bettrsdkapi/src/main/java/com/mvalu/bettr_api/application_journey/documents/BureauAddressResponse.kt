package com.mvalu.bettr_api.application_journey.documents

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json


class BureauAddressResponse : ApiBaseResponse() {

    @field:Json(name = "results")
    var result: List<BureauAddressResult>? = null



    class BureauAddressResult {

        @field:Json(name = "addr1")
        var addr1: String? = null

        @field:Json(name = "addr2")
        var addr2: String? = null

        @field:Json(name = "addr3")
        var addr3: String? = null

        @field:Json(name = "city")
        var city: String? = null

        @field:Json(name = "state")
        var state: String? = null

        @field:Json(name = "pincode")
        var pincode: String? = null
    }

}