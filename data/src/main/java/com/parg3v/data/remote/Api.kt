package com.parg3v.data.remote

import com.parg3v.data.config.NetworkConfig
import com.parg3v.data.model.OffersListResponse
import retrofit2.http.GET

interface Api {
    @GET(NetworkConfig.PATH_OFFERS)
    suspend fun getAllOffers(): OffersListResponse
}