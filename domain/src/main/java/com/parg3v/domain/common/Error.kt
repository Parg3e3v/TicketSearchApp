package com.parg3v.domain.common

sealed interface Error

enum class OfferError: Error {
    BASIC
}

enum class TicketOfferError: Error {
    BASIC
}
enum class TicketError: Error {
    BASIC
}

enum class FieldFromError: Error {
    BASIC
}

enum class FieldToError: Error {
    BASIC
}