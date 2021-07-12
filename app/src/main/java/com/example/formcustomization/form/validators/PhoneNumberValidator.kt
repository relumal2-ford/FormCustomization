package com.example.formcustomization.form.validators

import com.example.formcustomization.form.InputValidator
import javax.inject.Inject

class PhoneNumberValidator @Inject constructor() : InputValidator {
    companion object {
        private const val MAX_PHONE_LENGTH = 10
        private const val MIN_PHONE_LENGTH = 10
    }

    override fun isValid(input: String?): Boolean =
        input?.let { (it.length in MIN_PHONE_LENGTH..MAX_PHONE_LENGTH) && input.matches( Regex.fromLiteral("[0-9]+")) }
            ?: false
}