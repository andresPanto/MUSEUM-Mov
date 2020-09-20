package com.example.museum.models

import com.beust.klaxon.Converter
import com.beust.klaxon.JsonObject
import com.beust.klaxon.JsonValue
import com.beust.klaxon.Klaxon

class Schedule(
    val createdAt: Long,
    val updatedAt: Long,
    val id: Int,
    val schedule: String,
    val capacity: Int,
    val status: Boolean,
    val activity: Any?
) {
    companion object {
        val conversorSchedule = object : Converter {
            override fun canConvert(cls: Class<*>) = cls == Schedule::class.java


            override fun fromJson(jv: JsonValue): Schedule {
                return if (jv.obj?.get("activity") is Int )
                    Schedule(
                        jv.objInt("createdAt").toLong(),
                        jv.objInt("updatedAt").toLong(),
                        jv.objInt("id"),
                        jv.objString("schedule"),
                        jv.objInt("capacity"),
                        jv.obj?.get("status").toString().toBoolean(),
                        jv.objInt("activity")
                    )
                else
                    Schedule(
                        jv.objInt("createdAt").toLong(),
                        jv.objInt("updatedAt").toLong(),
                        jv.objInt("id"),
                        jv.objString("schedule"),
                        jv.objInt("capacity"),
                        jv.obj?.get("status").toString().toBoolean(),
                        Klaxon().parseFromJsonObject<Activity>(jv.obj?.get("activity") as JsonObject)
                    )
            }

            override fun toJson(value: Any): String {
                return ""
            }
        }


    }



}