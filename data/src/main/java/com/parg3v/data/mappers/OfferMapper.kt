package com.parg3v.data.mappers

import com.parg3v.data.model.OfferModel
import com.parg3v.domain.model.Offer

fun OfferModel.toOffer(): Offer = Offer(
    id = id,
    title = title,
    town = town,
    price = price.toPrice()
)