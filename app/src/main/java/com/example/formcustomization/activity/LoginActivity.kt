package com.example.formcustomization.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.formcustomization.R
import com.example.formcustomization.databinding.ActivityMainBinding
import com.example.formcustomization.viewModel.LoginViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class LoginActivity: BaseActivity() {
    @Inject
    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = loginViewModel
        loginViewModel.viewCallbackEmitter = viewCallbackEmitter
    }
}