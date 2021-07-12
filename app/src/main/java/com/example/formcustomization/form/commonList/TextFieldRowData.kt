package com.example.formcustomization.form.commonList

import android.text.InputType
import com.example.formcustomization.form.FormField
import com.example.formcustomization.viewModel.TextFieldData
import com.example.formcustomization.viewModel.Validation

sealed class TextFieldRowData(val textFieldData: TextFieldData) {
    class FirstName(textFieldData: TextFieldData = TextFieldData(FormField().addValidation(Validation.FirstNameValidation), "firstName", InputType.TYPE_CLASS_TEXT)): TextFieldRowData(textFieldData)
    class LastName(textFieldData: TextFieldData = TextFieldData(FormField().addValidation(Validation.LastNameValidation), "lastName", InputType.TYPE_CLASS_TEXT)): TextFieldRowData(textFieldData)
    class PhoneNumber(textFieldData: TextFieldData = TextFieldData(FormField().addValidation(Validation.PhoneNumberValidation), "phoneNumber", InputType.TYPE_CLASS_NUMBER)): TextFieldRowData(textFieldData)
    class Email(textFieldData: TextFieldData = TextFieldData(FormField().addValidation(Validation.EmailValidation), "email", InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)): TextFieldRowData(textFieldData)
}