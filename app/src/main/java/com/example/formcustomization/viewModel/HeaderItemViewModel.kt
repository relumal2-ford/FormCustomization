package com.example.formcustomization.viewModel

import androidx.annotation.LayoutRes

open class HeaderItemViewModel(private val baseData: HeaderData,
                               @LayoutRes private val layoutID: Int,
                               private val onClickSelectedItem: (() -> Unit)? = null): CommonItemViewModel<HeaderData>(baseData, layoutID, onClickSelectedItem) {

    override fun layoutId(): Int = layoutID
    override fun data(): HeaderData = baseData
    override fun itemOnClick() { onClickSelectedItem?.invoke() }
}

class HeaderData(val headerText: String)