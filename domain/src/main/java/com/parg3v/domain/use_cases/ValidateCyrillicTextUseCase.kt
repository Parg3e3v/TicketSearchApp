package com.parg3v.domain.use_cases

class ValidateCyrillicTextUseCase {
    operator fun invoke(value: String): Boolean =
        Regex("""^(?!\s)(?:[А-Яа-я]+\s?)*\s?${'$'}""").matches(value)
}