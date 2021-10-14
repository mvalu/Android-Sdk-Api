package com.mvalu.bettr_api.application_journey.documents


import com.squareup.moshi.Json

class KycSubmitResult {

    @field:Json(name = "status")
    var status: Boolean? = null

    @field:Json(name = "addressParams")
    var addressParam: AddressParam? = null

    class AddressParam {
        @field:Json(name = "leadId")
        var leadId: String? = null

        @field:Json(name = "organizationId")
        var organisationId: String? = null

        @field:Json(name = "address")
        var address: String? = null

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