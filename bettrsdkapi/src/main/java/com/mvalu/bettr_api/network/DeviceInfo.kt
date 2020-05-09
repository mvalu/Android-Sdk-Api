package com.mvalu.bettr_api.network

import com.squareup.moshi.Json


class DeviceInfo {

    @field:Json(name = "model")
    var model: String? = null

    @field:Json(name = "brand")
    var brand: String? = null

    @field:Json(name = "hardware")
    var hardware: String? = null

    @field:Json(name = "board")
    var board: String? = null

    @field:Json(name = "bootloader")
    var bootloader: String? = null

    @field:Json(name = "user")
    var user: String? = null

    @field:Json(name = "host")
    var host: String? = null

    @field:Json(name = "osVersion")
    var osVersion: String? = null

    @field:Json(name = "osApiLevel")
    var osApiLevel: Int? = null

    @field:Json(name = "id")
    var id: String? = null

    @field:Json(name = "time")
    var time: Long? = null

    @field:Json(name = "fingerPrint")
    var fingerPrint: String? = null

    @field:Json(name = "display")
    var display: String? = null

    @field:Json(name = "device")
    var device: String? = null

    @field:Json(name = "product")
    var product: String? = null
}