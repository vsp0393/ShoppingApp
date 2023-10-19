package com.vsple.purchaseapp.Models

data class ItemDetailModel(
    val cartItemCount: Int,
    val discount: Int,
    val itemDescription: String,
    val storeName: String,
    val imageList: List<Int>,
    val price: Int,
    val productName: String,
    val size: List<Size>,
    val colorDetails: List<String>,
    val watchedUser: Int
)