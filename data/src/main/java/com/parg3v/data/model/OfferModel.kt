package com.parg3v.data.model

data class OfferModel(
    val id: Int,
    val title: String,
    val town: String,
    val price: PriceModel
)

data class PriceModel(
    val value: Int
)