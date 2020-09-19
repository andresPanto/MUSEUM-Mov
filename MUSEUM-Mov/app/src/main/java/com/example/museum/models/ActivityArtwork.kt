package com.example.museum.models

import com.beust.klaxon.Converter
import com.beust.klaxon.JsonObject
import com.beust.klaxon.JsonValue
import com.beust.klaxon.Klaxon

class ActivityArtwork(
    val createdAt: Long,
    val updatedAt: Long,
    val id: Int,
    val artwork: Any?,
    val activity: Any?
) {

    companion object{
        val conversorActivityArtwork = object : Converter {
            override fun canConvert(cls: Class<*>) = cls == ActivityArtwork::class.java


            override fun fromJson(jv: JsonValue): ActivityArtwork {
                return if (jv.obj?.get("activity") is Int || jv.obj?.get("artwork") is Int)
                    ActivityArtwork(
                        jv.objInt("createdAt").toLong(),
                        jv.objInt("updatedAt").toLong(),
                        jv.objInt("id"),
                        jv.objInt("artwork"),
                        jv.objInt("author")
                    )
                else
                    ActivityArtwork(
                        jv.objInt("createdAt").toLong(),
                        jv.objInt("updatedAt").toLong(),
                        jv.objInt("id"),
                        Klaxon().parseFromJsonObject<Artwork>(jv.obj?.get("artwork") as JsonObject),
                        Klaxon().parseFromJsonObject<Activity>(jv.obj?.get("activity") as JsonObject)
                    )
            }

            override fun toJson(value: Any): String {
                return ""
            }
        }

    }

}