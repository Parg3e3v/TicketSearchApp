package com.parg3v.domain.use_cases

import com.parg3v.domain.common.Result
import com.parg3v.domain.common.TicketError
import com.parg3v.domain.model.Ticket
import com.parg3v.domain.repository.TicketsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetAllTicketsUseCase @Inject constructor(
    private val ticketsRepository: TicketsRepository
) {
    operator fun invoke(): Flow<Result<List<Ticket>, TicketError>> = flow {
        try {
            emit(Result.Loading())
            val ticketOffers = ticketsRepository.getAllTickets()
            emit(Result.Success(ticketOffers))

        } catch (e: IOException) {
            emit(Result.Error(TicketError.BASIC))
        }
    }
}