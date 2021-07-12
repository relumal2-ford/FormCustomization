package com.example.formcustomization.viewModel

import androidx.annotation.LayoutRes
import com.example.formcustomization.form.commonList.ItemViewModelType

open class CommonItemViewModel<T>(private val baseData: T,
                                  @LayoutRes private val layoutID: Int,
                                  private val onClickSelectedItem: (() -> Unit)? = null): ItemViewModelType<T> {

    override fun layoutId(): Int = layoutID

    override fun data(): T = baseData

    override fun itemOnClick() { onClickSelectedItem?.invoke() }

}