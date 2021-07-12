package com.example.formcustomization.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.formcustomization.R
import com.example.formcustomization.databinding.ActivityCustomTemplateBinding
import javax.inject.Inject

class CustomTemplateActivity: BaseActivity() {
     @Inject
     lateinit var customTemplateViewModel: CustomTemplateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityCustomTemplateBinding = DataBindingUtil.setContentView(this, R.layout.activity_custom_template)
        binding.viewModel = customTemplateViewModel
       customTemplateViewModel.viewCallbackEmitter = viewCallbackEmitter 
    }
}