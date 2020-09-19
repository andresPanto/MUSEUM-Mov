package com.example.museum.models

import com.beust.klaxon.Converter
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonValue
import com.beust.klaxon.Klaxon
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class Activity(
    val createdAt: Long,
    val updatedAt: Long,
    val id: Int,
    val name: String,
    val type: String,
    val location: String,
    val initialDate: String,
    val finalDate: String,
    val duration: String,
    val pmName: String,
    val pmPhoneNumber: String,
    val price: Float,
    val description: String,
    val imagePath: String,
    val status: Boolean,
    val activityArtworks: ArrayList<ActivityArtwork>? = null,
    val schedules: ArrayList<Schedule>? = null
) {

    var dateInitialDate: LocalDate
    var dateFinalDate: LocalDate
    init {
        dateInitialDate = LocalDate.parse(initialDate, DateTimeFormatter.ISO_DATE_TIME)
        dateFinalDate = LocalDate.parse(finalDate, DateTimeFormatter.ISO_DATE_TIME)
    }

    companion object{

        val conversorActivity = object : Converter {
            override fun canConvert(cls: Class<*>) = cls == Activity::class.java

            override fun fromJson(jv: JsonValue): Activity? {
                return Activity(
                    jv.objInt("createdAt").toLong(),
                    jv.objInt("updatedAt").toLong(),
                    jv.objInt("id"),
                    jv.objString("name"),
                    jv.objString("type"),
                    jv.objString("location"),
                    jv.objString("initialDate"),
                    jv.objString("finalDate"),
                    jv.objString("duration"),
                    jv.objString("pmName"),
                    jv.objString("pmPhoneNumber"),
                    jv.obj?.get("price").toString().toFloat(),
                    jv.objString("description"),
                    jv.objString("imagePath"),
                    jv.obj?.get("status").toString().toBoolean(),
                    Klaxon().parseFromJsonArray<ActivityArtwork>(
                        jv.obj?.get("activityArtworks") as JsonArray<*>
                    ) as ArrayList<ActivityArtwork>,
                    Klaxon().parseFromJsonArray<Schedule>(
                        jv.obj?.get("schedules") as JsonArray<*>
                    ) as ArrayList<Schedule>
                )
            }

            override fun toJson(value: Any): String {
                return  ""
            }
        }
    }

}