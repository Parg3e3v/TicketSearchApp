package com.parg3v.domain.use_cases

import com.parg3v.domain.common.Result
import com.parg3v.domain.common.TicketOfferError
import com.parg3v.domain.model.TicketOffer
import com.parg3v.domain.repository.TicketOffersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetAllTicketOffersUseCase @Inject constructor(
    private val ticketOffersRepository: TicketOffersRepository
) {
    operator fun invoke(): Flow<Result<List<TicketOffer>, TicketOfferError>> = flow {
        try {
            emit(Result.Loading())
            val ticketOffers = ticketOffersRepository.getTicketsOffers()
            emit(Result.Success(ticketOffers))

        } catch (e: IOException) {
            emit(Result.Error(TicketOfferError.BASIC))
        }
    }
}