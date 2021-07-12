package com.example.formcustomization.viewModel

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.example.formcustomization.R
import com.example.formcustomization.form.*
import com.example.formcustomization.form.validators.EmailValidator
import com.example.formcustomization.form.validators.NonEmptyValidator
import com.example.formcustomization.form.validators.PhoneNumberValidator
import javax.inject.Inject

class LoginViewModel @Inject constructor() : BaseLifecycleViewModel() {

    private val requiredNonEmptyValidator = NonEmptyValidator()
    private val phoneNumberValidator = PhoneNumberValidator()
    private val emailValidator = EmailValidator()

    val firstName =  getValidationField(requiredNonEmptyValidator, R.string.first_name_empty_error)
    val lastName = getValidationField(requiredNonEmptyValidator, R.string.last_name_empty_error)
    val phoneNumber = getValidationField(phoneNumberValidator, R.string.phone_number_empty_error)
    val email = getValidationField(emailValidator, R.string.email_number_empty_error)

    private fun getValidationField(inputValidator: InputValidator, message: Int) =
        FormField().addValidation(ValidationRule(inputValidator, message))

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Log.d("LOGIN", "oncreate called")
    }

    fun finishActivity() {
        Log.d("LOGIN", "Finish activity called")
    }

    fun onNextClicked() {
        Log.d("LOGIN", "On next tapped")
    }

    fun onCancelClicked() { }

    fun textChanged() {
        Log.d("LOGIN", "On next changed method called")
    }

}