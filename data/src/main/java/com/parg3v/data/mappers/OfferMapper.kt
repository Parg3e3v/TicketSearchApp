package com.parg3v.data.mappers

import com.parg3v.data.model.OfferModel
import com.parg3v.data.model.PriceModel
import com.parg3v.domain.model.Offer
import com.parg3v.domain.model.Price

fun OfferModel.toOffer(): Offer = Offer(
    id = id,
    title = title,
    town = town,
    price = price.toPrice()
)

fun PriceModel.toPrice(): Price = Price(
    value = value
)