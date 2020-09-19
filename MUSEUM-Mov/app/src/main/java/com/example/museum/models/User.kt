package com.example.museum.models

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
    val status: Boolean
) {
}