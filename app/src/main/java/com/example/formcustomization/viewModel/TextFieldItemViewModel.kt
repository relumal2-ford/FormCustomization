package com.example.formcustomization.viewModel

import android.text.InputType
import androidx.annotation.LayoutRes
import com.example.formcustomization.form.FormField

open class TextFieldItemViewModel(private val baseData: TextFieldData,
                                  @LayoutRes private val layoutID: Int,
                                  private val onClickSelectedItem: (() -> Unit)? = null,
                                  val inputType: Int = InputType.TYPE_CLASS_TEXT,
                                  val hint: String = ""): CommonItemViewModel<TextFieldData>(baseData, layoutID, onClickSelectedItem) {

    override fun layoutId(): Int = layoutID
    override fun data(): TextFieldData = baseData
    override fun itemOnClick() { onClickSelectedItem?.invoke() }
}

data class TextFieldData(val formField: FormField,
                         val hint: String,
                         val inputType: Int)