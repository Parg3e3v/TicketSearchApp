package com.parg3v.data.repository

import com.parg3v.data.mappers.toOffer
import com.parg3v.data.remote.Api
import com.parg3v.domain.model.Offer
import com.parg3v.domain.repository.OffersRepository
import javax.inject.Inject

class OffersRepositoryImpl @Inject constructor(
    private val api: Api
): OffersRepository {
    override suspend fun getOffers(): List<Offer> =
        api.getAllOffers().items.map { it.toOffer() }

}