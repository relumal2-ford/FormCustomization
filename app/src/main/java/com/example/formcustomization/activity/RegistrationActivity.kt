package com.example.formcustomization.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.formcustomization.R
import com.example.formcustomization.databinding.ActivityRegisterationBinding
import com.example.formcustomization.viewModel.RegistrationViewModel
import javax.inject.Inject

class RegistrationActivity: BaseActivity() {
    @Inject
    lateinit var registrationViewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityRegisterationBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_registeration)
        binding.viewModel = registrationViewModel
        registrationViewModel.viewCallbackEmitter = viewCallbackEmitter
    }
}