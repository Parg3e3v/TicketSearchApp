package com.parg3v.data.mappers

import com.parg3v.data.model.PriceModel
import com.parg3v.domain.model.Price

fun PriceModel.toPrice(): Price = Price(
    value = value
)