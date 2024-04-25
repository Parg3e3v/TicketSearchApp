package com.parg3v.data.mappers

import com.parg3v.data.model.FlightInfoModel
import com.parg3v.data.model.HandLuggageModel
import com.parg3v.data.model.LuggageModel
import com.parg3v.data.model.OfferModel
import com.parg3v.data.model.PriceModel
import com.parg3v.data.model.TicketModel
import com.parg3v.data.model.TicketOfferModel
import com.parg3v.domain.model.FlightInfo
import com.parg3v.domain.model.HandLuggage
import com.parg3v.domain.model.Luggage
import com.parg3v.domain.model.Offer
import com.parg3v.domain.model.Price
import com.parg3v.domain.model.Ticket
import com.parg3v.domain.model.TicketOffer

fun PriceModel.toPrice(): Price = Price(
    value = value
)

fun OfferModel.toOffer(): Offer = Offer(
    id = id,
    title = title,
    town = town,
    price = price.toPrice()
)

fun TicketOfferModel.toTicket(): TicketOffer = TicketOffer(
    id = id,
    title = title,
    time_range = time_range,
    price = price.toPrice()
)
fun TicketModel.toTicket(): Ticket = Ticket(
    id = id,
    badge = badge,
    price = price.toPrice(),
    providerName = provider_name,
    company = company,
    departure = departure.toFlightInfo(),
    arrival = arrival.toFlightInfo(),
    hasTransfer = has_transfer,
    hasVisaTransfer = has_visa_transfer,
    luggage = luggage.toLuggage(),
    handLuggage = hand_luggage.toHandLuggage(),
    isReturnable = is_returnable,
    isExchangable = is_exchangable
)

fun FlightInfoModel.toFlightInfo(): FlightInfo = FlightInfo(
    town = town,
    date = date,
    airport = airport
)

fun LuggageModel.toLuggage(): Luggage = Luggage(
    hasLuggage = hasLuggage,
    price = price?.toPrice()
)

fun HandLuggageModel.toHandLuggage(): HandLuggage = HandLuggage(
    hasHandLuggage = hasHandLuggage,
    size = size
)