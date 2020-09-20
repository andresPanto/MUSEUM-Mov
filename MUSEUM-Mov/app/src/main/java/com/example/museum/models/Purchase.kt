package com.example.museum.models

import com.beust.klaxon.Converter
import com.beust.klaxon.JsonObject
import com.beust.klaxon.JsonValue
import com.beust.klaxon.Klaxon
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Purchase(
    val createdAt: Long,
    val updatedAt: Long,
    val id: Int,
    val attendanceDate: String,
    val purchaseTime: String,
    val quantity: Int,
    val total: Float,
    val status: Boolean,
    val schedule: Any?,
    val user: Any?

) {

    var dateAttendanceDate: LocalDate
    var timePurchaseTime: LocalDate
    init {
        dateAttendanceDate = LocalDate.parse(attendanceDate, DateTimeFormatter.ISO_DATE_TIME)
        timePurchaseTime = LocalDate.parse(purchaseTime, DateTimeFormatter.ISO_DATE_TIME)
    }

    companion object {
        val conversorPurchase = object : Converter {
            override fun canConvert(cls: Class<*>) = cls == Purchase::class.java


            override fun fromJson(jv: JsonValue): Purchase {
                return if (jv.obj?.get("user") is Int || jv.obj?.get("schedule") is Int)
                    Purchase(
                        jv.objInt("createdAt").toLong(),
                        jv.objInt("updatedAt").toLong(),
                        jv.objInt("id"),
                        jv.objString("attendanceDate"),
                        jv.objString("purchaseTime"),
                        jv.objInt("quantity"),
                        jv.obj?.get("total").toString().toFloat(),
                        jv.obj?.get("status").toString().toBoolean(),
                        jv.objInt("schedule"),
                        jv.objInt("user")
                    )
                else
                    Purchase(
                        jv.objInt("createdAt").toLong(),
                        jv.objInt("updatedAt").toLong(),
                        jv.objInt("id"),
                        jv.objString("attendanceDate"),
                        jv.objString("purchaseTime"),
                        jv.objInt("quantity"),
                        jv.obj?.get("total").toString().toFloat(),
                        jv.obj?.get("status").toString().toBoolean(),
                        Klaxon().parseFromJsonObject<Schedule>(jv.obj?.get("schedule") as JsonObject),
                        Klaxon().parseFromJsonObject<User>(jv.obj?.get("user") as JsonObject)
                    )
            }

            override fun toJson(value: Any): String {
                return ""
            }
        }


    }


}