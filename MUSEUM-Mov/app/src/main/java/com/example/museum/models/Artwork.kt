package com.example.museum.models

import com.beust.klaxon.Converter
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonValue
import com.beust.klaxon.Klaxon

class Artwork(
    val createdAt: Long,
    val updatedAt: Long,
    val id: Int,
    val name: String,
    val year: Int,
    val type: String,
    val description: String,
    val imagePath: String,
    val status: Boolean,
    val artworkAuthors: ArrayList<ArtworkAuthor>? = null,
    val activityArtworks: ArrayList<ActivityArtwork>? = null
) {
    companion object{
        val conversorArtwork = object : Converter {
            override fun canConvert(cls: Class<*>) = cls == Artwork::class.java

            override fun fromJson(jv: JsonValue): Any? {
                return Artwork(
                    jv.objInt("createdAt").toLong(),
                    jv.objInt("updatedAt").toLong(),
                    jv.objInt("id"),
                    jv.objString("name"),
                    jv.objInt("year"),
                    jv.objString("type"),
                    jv.objString("description"),
                    jv.objString("imagePath"),
                    jv.obj?.get("status").toString().toBoolean(),
                    Klaxon().parseFromJsonArray<ArtworkAuthor>(
                        jv.obj?.get("artworkAuthors") as JsonArray<*>
                    ) as ArrayList<ArtworkAuthor>,
                    Klaxon().parseFromJsonArray<ActivityArtwork>(
                        jv.obj?.get("activityArtworks") as JsonArray<*>
                    ) as ArrayList<ActivityArtwork>
                )
            }

            override fun toJson(value: Any): String {
                return  ""
            }
        }
    }


}