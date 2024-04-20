package com.parg3v.domain.common

sealed interface Error

enum class OfferError: Error {
    BASIC
}