package com.parg3v.domain.use_cases

class ValidateCyrillicText {
    operator fun invoke(value: String): Boolean = Regex("\\p{IsCyrillic}*").matches(value)
}