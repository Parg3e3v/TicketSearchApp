package com.parg3v.data.remote

import com.parg3v.data.config.NetworkConfig
import com.parg3v.data.model.OffersListResponse
import com.parg3v.data.model.TicketOffersResponse
import com.parg3v.data.model.TicketsResponse
import retrofit2.http.GET

interface Api {
    @GET(NetworkConfig.PATH_OFFERS)
    suspend fun getAllOffers(): OffersListResponse
    @GET(NetworkConfig.PATH_OFFERS_TICKET)
    suspend fun getAllTicketOffers(): TicketOffersResponse
    @GET(NetworkConfig.PATH_TICKETS)
    suspend fun getAllTickets(): TicketsResponse
}