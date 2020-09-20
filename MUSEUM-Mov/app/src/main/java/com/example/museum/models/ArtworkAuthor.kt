package com.example.museum.models

import com.beust.klaxon.Converter
import com.beust.klaxon.JsonObject
import com.beust.klaxon.JsonValue
import com.beust.klaxon.Klaxon

class ArtworkAuthor(
    val createdAt: Long,
    val updatedAt: Long,
    val id: Int,
    val artwork: Any?,
    val author: Any?
) {


    companion object {
        val conversorArtworkAuthor = object : Converter {
            override fun canConvert(cls: Class<*>) = cls == ArtworkAuthor::class.java


            override fun fromJson(jv: JsonValue): ArtworkAuthor {
                return if (jv.obj?.get("author") is Int || jv.obj?.get("artwork") is Int)
                    ArtworkAuthor(
                        jv.objInt("createdAt").toLong(),
                        jv.objInt("updatedAt").toLong(),
                        jv.objInt("id"),
                        jv.objInt("artwork"),
                        jv.objInt("author")
                    )
                else
                    ArtworkAuthor(
                        jv.objInt("createdAt").toLong(),
                        jv.objInt("updatedAt").toLong(),
                        jv.objInt("id"),
                        Klaxon().parseFromJsonObject<Artwork>(jv.obj?.get("artwork") as JsonObject),
                        Klaxon().parseFromJsonObject<Author>(jv.obj?.get("author") as JsonObject)
                    )
            }

            override fun toJson(value: Any): String {
                return ""
            }
        }


    }

}