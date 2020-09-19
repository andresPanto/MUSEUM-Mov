package com.example.museum.models

class Schedule(
    val createdAt: Long,
    val updatedAt: Long,
    val id: Int,
    val schedule: String,
    val capacity: Int,
    val status: Boolean
) {
}