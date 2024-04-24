package com.parg3v.data.model

class TicketOfferModel(
    val id: Int,
    val title: String,
    val time_range: List<String>,
    val price: PriceModel
)