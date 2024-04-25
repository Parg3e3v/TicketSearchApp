package com.parg3v.data.repository

import com.parg3v.data.mappers.toTicket
import com.parg3v.data.remote.Api
import com.parg3v.domain.model.Ticket
import com.parg3v.domain.repository.TicketsRepository
import javax.inject.Inject

class TicketsRepositoryImpl @Inject constructor(
    private val api: Api
) : TicketsRepository {

    override suspend fun getAllTickets(): List<Ticket> =
        api.getAllTickets().tickets.map { it.toTicket() }

}