package com.parg3v.domain.repository

import com.parg3v.domain.model.TicketOffer

interface TicketOffersRepository {
    suspend fun getTicketsOffers(): List<TicketOffer>
}