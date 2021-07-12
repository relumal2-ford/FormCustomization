package com.example.formcustomization.form.validators

import com.example.formcustomization.form.InputValidator
import javax.inject.Inject

class NonEmptyValidator @Inject constructor() : InputValidator {
    override fun isValid(input: String?): Boolean = input?.isNotBlank() ?: false
}