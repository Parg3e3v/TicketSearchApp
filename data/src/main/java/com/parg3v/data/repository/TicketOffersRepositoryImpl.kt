package com.parg3v.data.repository

import com.parg3v.data.mappers.toTicket
import com.parg3v.data.remote.Api
import com.parg3v.domain.model.TicketOffer
import com.parg3v.domain.repository.TicketOffersRepository
import javax.inject.Inject

class TicketOffersRepositoryImpl @Inject constructor(
    private val api: Api
) : TicketOffersRepository {

    override suspend fun getTicketsOffers(): List<TicketOffer> =
        api.getAllTicketOffers().tickets_offers.map { it.toTicket() }

}