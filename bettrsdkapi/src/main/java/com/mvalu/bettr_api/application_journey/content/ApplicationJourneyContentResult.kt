package com.mvalu.bettr_api.application_journey.content

import com.squareup.moshi.Json

class ApplicationJourneyContentResult {

    class DropdownDetail{
        @field:Json(name = "city")
        var cities: List<DropdownItem>? = null

        @field:Json(name = "maritalStatus")
        var maritalStatuses: List<DropdownItem>? = null

        @field:Json(name = "companyType")
        var companyTypes: List<DropdownItem>? = null

        @field:Json(name = "noOfEmployeeInCompany")
        var noOfEmployeesInCompany: List<DropdownItem>? = null

        @field:Json(name = "roleInCompany")
        var rolesInCompany: List<DropdownItem>? = null

        @field:Json(name = "companyAreaOfBusiness")
        var companyAreasOfBusiness: List<DropdownItem>? = null

        @field:Json(name = "designation")
        var designations: List<DropdownItem>? = null

        @field:Json(name = "typeOfBusiness")
        var typesOfBusiness: List<DropdownItem>? = null

        @field:Json(name = "noOfEmployeeInBusiness")
        var noOfEmployeesInBusiness: List<DropdownItem>? = null

        @field:Json(name = "ownBusiness")
        var ownBusinesses: List<DropdownItem>? = null

        @field:Json(name = "gender")
        var genders: List<DropdownItem>? = null

        @field:Json(name = "areYouEmployed")
        var areYouEmployedOptions: List<DropdownItem>? = null

        @field:Json(name = "havingOfficeMail")
        var havingOfficeMailOptions: List<DropdownItem>? = null
    }
}