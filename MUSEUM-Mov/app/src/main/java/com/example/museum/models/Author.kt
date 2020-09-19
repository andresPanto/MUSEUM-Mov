package com.example.museum.models

import com.beust.klaxon.Converter
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonValue
import com.beust.klaxon.Klaxon

class Author(
    val createdAt: Long,
    val updatedAt: Long,
    val id: Int,
    val fullName: String,
    val country: String,
    val description: String,
    val imagePath: String,
    val status: Boolean,
    val artworkAuthors: ArrayList<ArtworkAuthor>? = null
) {
    companion object{
        val conversorAuthor = object : Converter{
            override fun canConvert(cls: Class<*>) = cls == Author::class.java

            override fun fromJson(jv: JsonValue): Any? {
                return Author(
                    jv.objInt("createdAt").toLong(),
                    jv.objInt("updatedAt").toLong(),
                    jv.objInt("id"),
                    jv.objString("fullName"),
                    jv.objString("country"),
                    jv.objString("description"),
                    jv.objString("imagePath"),
                    jv.obj?.get("status").toString().toBoolean(),
                    Klaxon().parseFromJsonArray<ArtworkAuthor>(
                        jv.obj?.get("artworkAuthors") as JsonArray<*>
                    ) as ArrayList<ArtworkAuthor>
                )
            }

            override fun toJson(value: Any): String {
               return  ""
            }
        }
    }

}