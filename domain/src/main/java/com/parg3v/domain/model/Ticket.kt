package com.parg3v.domain.model

data class Ticket(
    val id: Int,
    val badge: String?,
    val price: Price,
    val providerName: String,
    val company: String,
    val departure: FlightInfo,
    val arrival: FlightInfo,
    val hasTransfer: Boolean,
    val hasVisaTransfer: Boolean,
    val luggage: Luggage,
    val handLuggage: HandLuggage,
    val isReturnable: Boolean,
    val isExchangable: Boolean
)

data class FlightInfo(
    val town: String,
    val date: String,
    val airport: String
)

data class Luggage(
    val hasLuggage: Boolean,
    val price: Price?
)

data class HandLuggage(
    val hasHandLuggage: Boolean,
    val size: String?
)