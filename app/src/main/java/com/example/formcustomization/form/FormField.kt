package com.example.formcustomization.form

import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.example.formcustomization.viewModel.Validation

interface ValidationField {
    val textWatcher: ObservableField<String>
    val errorEmitter: ObservableInt
}

class FormField {
    companion object {
        const val EMPTY_STRING = ""
        const val NO_ERROR_MESSAGE = 0
    }

    private val validationRules = mutableListOf<ValidationRule>()

    val textWatcher = ObservableField(EMPTY_STRING)
    val errorEmitter = ObservableInt()

    fun onFocusChange(view: View, hasFocus: Boolean) {
        if (!hasFocus) { validateField() }
    }

    fun validateField() {
       validationRules.firstOrNull { validationRule -> !validationRule.validator.isValid(textWatcher.get()) }
           ?.let { validationRule -> errorEmitter.set(validationRule.errorMessageID) }
           ?: errorEmitter.set(NO_ERROR_MESSAGE)
    }

    fun addValidation(validation: Validation): FormField {
        validationRules.add(validation.validationRule)
        return this
    }

    fun addValidation(validationRule: ValidationRule): FormField {
        validationRules.add(validationRule)
        return this
    }

    fun getText() = textWatcher.get()

}

data class ValidationRule(val validator: InputValidator,
                          val errorMessageID: Int)

interface InputValidator {
    fun isValid(text: String?): Boolean
}