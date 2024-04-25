package com.parg3v.domain.repository

import com.parg3v.domain.model.Ticket

interface TicketsRepository {
    suspend fun getAllTickets(): List<Ticket>
}