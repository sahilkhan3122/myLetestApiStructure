package com.app.myapistructure.ui.login

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.app.myapistructure.base.BaseActivity
import com.app.myapistructure.network.utility.ResponseData
import com.example.myapistructure.R
import com.example.myapistructure.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    private lateinit var loginViewModel: LoginViewModel
    override val layoutId: Int
        get() = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding.viewModel = loginViewModel

        lifecycleScope.launch {
            loginViewModel.loginStateFlow.collect {
                when (it) {
                    is ResponseData.Empty -> {}
                    is ResponseData.Error -> {
                        Toast.makeText(this@LoginActivity, "${it.error}", Toast.LENGTH_SHORT).show()
                    }

                    is ResponseData.InternetConnection -> {
                    }

                    is ResponseData.Loading -> {
                    }

                    is ResponseData.Success -> {
                        if (it.data is LoginResponse) {
                            if (it.data.status) {
                                Toast.makeText(
                                    this@LoginActivity,
                                    "${it.data.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(
                                    this@LoginActivity,
                                    "${it.data.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                    }

                    is ResponseData.Exception -> {}
                }

            }
        }
    }
}
