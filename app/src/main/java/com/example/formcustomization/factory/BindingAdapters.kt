package com.example.formcustomization.factory

import android.view.View
import android.widget.Button
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.formcustomization.customViews.SkipDividerItemDecoration

@BindingAdapter("onFocusChange")
fun onFocusChange(view: View, listener: View.OnFocusChangeListener?) {
    view.onFocusChangeListener = listener
}

@BindingAdapter("itemDecoration")
fun setItemDecoration(view: RecyclerView, decoration: ItemDecoration?) {
    view.addItemDecoration(decoration!!)
}

@BindingAdapter("dividerItemDecorationWithExceptions")
fun setDividerItemDecorationWithException(
    view: RecyclerView,
    decorationItemExceptions: ObservableArrayList<Int>
) {
    val customDividerItemDecoration =
        SkipDividerItemDecoration(view.context, decorationItemExceptions)
    setItemDecoration(view, customDividerItemDecoration)
}

@BindingAdapter("setRecyclerViewScrollPosition")
fun setRecyclerViewScrollPosition(view: RecyclerView, position: Int) {
    view.scrollToPosition(position)
}