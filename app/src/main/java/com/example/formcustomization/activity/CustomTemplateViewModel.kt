package com.example.formcustomization.activity

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.example.formcustomization.form.commonList.CommonListAdapter
import com.example.formcustomization.form.commonList.Row.*
import com.example.formcustomization.form.commonList.toItemViewModelList
import com.example.formcustomization.viewModel.*
import javax.inject.Inject

class CustomTemplateViewModel@Inject constructor(val commonListAdapter: CommonListAdapter<CommonItemViewModel<*>>,
): BaseLifecycleViewModel() {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        val headerData = HeaderData("First Section")
        val headerData2 = HeaderData("Second Section")

        val primaryButtonData = ButtonData("Next") {
            Log.d("LOGIN", "Primary button tapped")
        }
        val secondaryButtonData = ButtonData("Next") {
            Log.d("LOGIN", "Primary button tapped")
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
}}