package com.example.formcustomization.viewModel

import androidx.annotation.LayoutRes
import com.example.formcustomization.R
import com.example.formcustomization.form.commonList.ItemViewModelType

open class ButtonItemViewModel(private val baseData: ButtonData,
                               @LayoutRes private val layoutID: Int,
                               private val onClickSelectedItem: (() -> Unit)? = null): CommonItemViewModel<ButtonData>(baseData, layoutID, onClickSelectedItem) {

    override fun layoutId(): Int = layoutID
    override fun data(): ButtonData = baseData
    override fun itemOnClick() { onClickSelectedItem?.invoke() }
}

data class ButtonData(val text: String, val style: Int = R.style.BrandButton, val topMargin: Int = R.dimen.standard_margin_top, val bottomMargin: Int = R.dimen.two_dp, val onClickSelectedItem: (() -> Unit)? = null) {
    fun buttonTapped() {
        onClickSelectedItem?.invoke()
    }
}