package com.vsple.purchaseapp.Models

data class Size(
    val availability: Boolean,
    val prize: Int,
    val sizeType: String,
    var selectedSize: Boolean
)