package com.example.museum.models

import com.beust.klaxon.Converter
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonValue
import com.beust.klaxon.Klaxon

class User(
    val createdAt: Long,
    val updatedAt: Long,
    val id: Int,
    val username: String,
    val password: String,
    val email: String,
    val fullName: String,
    val phoneNumber: String,
    val imagePath: String,
    val status: Boolean,
    val purchases: ArrayList<Purchase>? = null
) {

    companion object{
        val conversorUser = object : Converter {
            override fun canConvert(cls: Class<*>) = cls == User::class.java

            override fun fromJson(jv: JsonValue): User {
                return User(
                    jv.objInt("createdAt").toLong(),
                    jv.objInt("updatedAt").toLong(),
                    jv.objInt("id"),
                    jv.objString("username"),
                    jv.objString("password"),
                    jv.objString("email"),
                    jv.objString("fullName"),
                    jv.objString("phoneNumber"),
                    jv.objString("imagePath"),
                    jv.obj?.get("status").toString().toBoolean(),
                    Klaxon().parseFromJsonArray<Purchase>(
                        jv.obj?.get("purchases") as JsonArray<*>
                    ) as ArrayList<Purchase>
                )
            }

            override fun toJson(value: Any): String {
                return  ""
            }
        }
    }


}