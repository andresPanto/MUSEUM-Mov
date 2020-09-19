package com.example.museum.models

class Purchase(
    val createdAt: Long,
    val updatedAt: Long,
    val id: Int,
    val attendanceDate: String,
    val purchaseTime: String,
    val quantity: Int,
    val total: Float,
    val imagePath: String,
    val status: Boolean
) {
}