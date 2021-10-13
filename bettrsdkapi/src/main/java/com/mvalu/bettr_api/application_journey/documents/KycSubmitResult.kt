package com.mvalu.bettr_api.application_journey.documents

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class KycSubmitResult : ApiBaseResponse() {

    @field:Json(name = "status")
    var status: Boolean? = null

    @field:Json(name = "addressParams")
    var addressParam: AddressParam? = null

    class AddressParam {
        @field:Json(name = "house")
        var house: String? = null

        @field:Json(name = "street")
        var street: String? = null

        @field:Json(name = "landmark")
        var landmark: String? = null

        @field:Json(name = "location")
        var location: String? = null

        @field:Json(name = "vtc")
        var vtc: String? = null

        @field:Json(name = "postOffice")
        var postOffice: String? = null

        @field:Json(name = "district")
        var district: String? = null

        @field:Json(name = "state")
        var state: String? = null

        @field:Json(name = "pincode")
        var pincode: String? = null
    }
}