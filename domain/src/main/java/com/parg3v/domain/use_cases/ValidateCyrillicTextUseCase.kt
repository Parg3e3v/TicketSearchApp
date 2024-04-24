package com.parg3v.domain.use_cases

class ValidateCyrillicTextUseCase {
    operator fun invoke(value: String): Boolean = Regex("[\\p{IsCyrillic} ]*").matches(value)
}