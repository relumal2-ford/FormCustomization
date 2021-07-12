package com.example.formcustomization.viewModel

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.example.formcustomization.R
import com.example.formcustomization.form.commonList.CommonListAdapter
import com.example.formcustomization.form.ValidationRule
import com.example.formcustomization.form.validators.EmailValidator
import com.example.formcustomization.form.validators.NonEmptyValidator
import com.example.formcustomization.form.validators.PhoneNumberValidator
import com.example.formcustomization.form.commonList.Row.*
import com.example.formcustomization.form.commonList.toItemViewModelList
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(val commonListAdapter: CommonListAdapter<CommonItemViewModel<*>>,
): BaseLifecycleViewModel() {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {

        val headerData = HeaderData("First Section")
        val headerData2 = HeaderData("Second Section")

        val primaryButtonData = ButtonData("Next", topMargin = R.dimen.standard_margin_top) {
            Log.d("LOGIN", "Primary button tapped")
        }
        val secondaryButtonData = ButtonData("Cancel", bottomMargin = R.dimen.padding_seven_dp) {
            Log.d("LOGIN", "Secondary button tapped")
        }

        val customData = CustomData("first label", "second label")

        val rows = listOf(Header(headerData),
                FirstName(),
                LastName(),
                PhoneNumber(),
                Email(),
                Header(headerData2),
                Custom(customData),
                Button(primaryButtonData),
                Button(secondaryButtonData)).toItemViewModelList()

        commonListAdapter.setData(rows)
    }
}

enum class Validation(val validationRule: ValidationRule) {
    FirstNameValidation(ValidationRule(NonEmptyValidator(), R.string.first_name_empty_error)),
    LastNameValidation(ValidationRule(NonEmptyValidator(), R.string.last_name_empty_error)),
    PhoneNumberValidation(ValidationRule(PhoneNumberValidator(), R.string.phone_number_empty_error)),
    EmailValidation(ValidationRule(EmailValidator(), R.string.email_number_empty_error))
}