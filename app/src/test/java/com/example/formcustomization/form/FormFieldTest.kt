package com.example.formcustomization.form

import com.example.formcustomization.KotlinBaseTest
import com.example.formcustomization.form.FormField.Companion.NO_ERROR_MESSAGE
import io.mockk.every
import junit.framework.Assert.assertEquals
import org.junit.Test

class FormFieldTest : KotlinBaseTest() {

    private val validationField = FormField()

    private val inputValidator = mockk<InputValidator>()

    private val inputString = "inputString"

    @Test
    fun `when input is valid should set errorEmitter with no message`() {

        validationField.textWatcher.set(inputString)
        every { inputValidator.isValid(inputString) } returns true
        val validationRule = ValidationRule(inputValidator, 5)
        validationField.addValidation(validationRule)

        validationField.validateField()

        assertEquals(NO_ERROR_MESSAGE, validationField.errorEmitter.get())
    }

    @Test
    fun `when input is not valid should set correct error message`() {

        validationField.textWatcher.set(inputString)
        every { inputValidator.isValid(inputString) } returns false
        val validationRule = ValidationRule(inputValidator, 5)
        validationField.addValidation(validationRule)

        val inputValidator1 = mockk<InputValidator>()
        every { inputValidator1.isValid(inputString) } returns true
        val validationRule1 = ValidationRule(inputValidator1, 10)
        validationField.addValidation(validationRule1)

        validationField.validateField()

        assertEquals(5, validationField.errorEmitter.get())
    }


}