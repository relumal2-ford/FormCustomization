package com.example.formcustomization.form.commonList

import com.example.formcustomization.R
import com.example.formcustomization.form.ButtonType
import com.example.formcustomization.viewModel.*

sealed class Row<T>(val data: T, val onRowSelected: (() -> Unit)? = null) {
    class FirstName(textFieldData: TextFieldData = TextFieldRowData.FirstName().textFieldData): Row<TextFieldData>(textFieldData) {
        override fun getItemViewModel(): CommonItemViewModel<TextFieldData> = TextFieldItemViewModel(data, R.layout.item_text_field)
    }

    class LastName(textFieldData: TextFieldData = TextFieldRowData.LastName().textFieldData): Row<TextFieldData>(textFieldData) {
        override fun getItemViewModel(): CommonItemViewModel<TextFieldData> = TextFieldItemViewModel(data, R.layout.item_text_field)
    }

    class PhoneNumber(textFieldData: TextFieldData = TextFieldRowData.PhoneNumber().textFieldData): Row<TextFieldData>(textFieldData) {
        override fun getItemViewModel(): CommonItemViewModel<TextFieldData> = TextFieldItemViewModel(data, R.layout.item_text_field)
    }

    class Email(textFieldData: TextFieldData = TextFieldRowData.Email().textFieldData): Row<TextFieldData>(textFieldData) {
        override fun getItemViewModel(): CommonItemViewModel<TextFieldData> = TextFieldItemViewModel(data, R.layout.item_text_field)
    }

    class Button(buttonData: ButtonData, private val buttonType: ButtonType = ButtonType.Primary): Row<ButtonData>(buttonData) {
        override fun getItemViewModel(): CommonItemViewModel<ButtonData> = ButtonItemViewModel(data, buttonType.layoutID)
    }

    class Custom(customData: CustomData): Row<CustomData>(customData) {
        override fun getItemViewModel(): CommonItemViewModel<CustomData> = CustomItemViewModel(data, R.layout.item_custom_type)
    }

    class Header(headerData: HeaderData): Row<HeaderData>(headerData) {
        override fun getItemViewModel(): CommonItemViewModel<HeaderData> = HeaderItemViewModel(data, R.layout.item_header_type)
    }

    abstract fun getItemViewModel(): CommonItemViewModel<T>
}


fun List<Row<*>>.toItemViewModelList(): List<CommonItemViewModel<*>> {
    return this.map { it.getItemViewModel() }
}