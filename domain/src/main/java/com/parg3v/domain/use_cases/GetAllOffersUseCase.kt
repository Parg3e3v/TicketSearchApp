package com.parg3v.domain.use_cases

import com.parg3v.domain.common.OfferError
import com.parg3v.domain.common.Result
import com.parg3v.domain.model.Offer
import com.parg3v.domain.repository.OffersRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetAllOffersUseCase @Inject constructor(
    private val offersRepository: OffersRepository
) {
    operator fun invoke(): Flow<Result<List<Offer>, OfferError>> = flow {
        try {
            emit(Result.Loading())
            delay(3000) //for shimmer
            val offers = offersRepository.getOffers()
            emit(Result.Success(offers))

        } catch (e: IOException) {
            emit(Result.Error(OfferError.BASIC))
        }
    }
}