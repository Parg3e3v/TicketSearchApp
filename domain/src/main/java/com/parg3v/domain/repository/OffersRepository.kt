package com.parg3v.domain.repository

import com.parg3v.domain.model.Offer

interface OffersRepository {
    suspend fun getOffers():List<Offer>
}