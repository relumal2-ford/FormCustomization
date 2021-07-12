package com.example.formcustomization.viewModel

import androidx.annotation.LayoutRes

open class CustomItemViewModel(private val baseData: CustomData,
                               @LayoutRes private val layoutID: Int,
                               private val onClickSelectedItem: (() -> Unit)? = null): CommonItemViewModel<CustomData>(baseData, layoutID, onClickSelectedItem) {

    override fun layoutId(): Int = layoutID
    override fun data(): CustomData = baseData
    override fun itemOnClick() { onClickSelectedItem?.invoke() }
}

data class CustomData(val firstText: String, val secondText: String)