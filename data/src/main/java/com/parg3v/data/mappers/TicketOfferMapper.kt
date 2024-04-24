package com.parg3v.data.mappers

import com.parg3v.data.model.TicketOfferModel
import com.parg3v.domain.model.TicketOffer

fun TicketOfferModel.toTicket(): TicketOffer = TicketOffer(
    id = id,
    title = title,
    time_range = time_range,
    price = price.toPrice()
)