package com.parg3v.data.model

data class TicketModel(
    val id: Int,
    val badge: String?,
    val price: PriceModel,
    val provider_name: String,
    val company: String,
    val departure: FlightInfoModel,
    val arrival: FlightInfoModel,
    val has_transfer: Boolean,
    val has_visa_transfer: Boolean,
    val luggage: LuggageModel,
    val hand_luggage: HandLuggageModel,
    val is_returnable: Boolean,
    val is_exchangable: Boolean
)

data class FlightInfoModel(
    val town: String,
    val date: String,
    val airport: String
)

data class LuggageModel(
    val hasLuggage: Boolean,
    val price: PriceModel?
)

data class HandLuggageModel(
    val hasHandLuggage: Boolean,
    val size: String?
)