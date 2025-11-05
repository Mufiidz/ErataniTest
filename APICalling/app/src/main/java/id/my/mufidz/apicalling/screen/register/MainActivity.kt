package id.my.mufidz.apicalling.screen.register

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import id.my.mufidz.apicalling.base.BaseActivity
import id.my.mufidz.apicalling.databinding.ActivityMainBinding
import id.my.mufidz.apicalling.model.User
import id.my.mufidz.apicalling.screen.home.HomeScreen
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {

            edtName.setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {

                    nameContainer.error = validName()
                    nameContainer.isErrorEnabled = validName() != null
                }
            }
            edtGender.setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {

                    genderContainer.error = validGender()
                    genderContainer.isErrorEnabled = validGender() != null
                }
            }
            edtEmail.setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {

                    emailContainer.error = validEmail()
                    emailContainer.isErrorEnabled = validEmail() != null
                }
            }
            edtStatus.setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {

                    statusContainer.error = validStatus()
                    statusContainer.isErrorEnabled = validStatus() != null
                }
            }

            btnRegister.setOnClickListener { onSubmitForm() }
        }

        lifecycleScope.launch {
            viewModel.viewState.collectLatest {
                Timber.d("State $it")
                when (it) {
                    RegisterState.Loading -> {
                        binding.btnRegister.isEnabled = false
                    }
                    else -> {}
                }
            }
        }
        lifecycleScope.launch {
            viewModel.sideEffect.collect {
                Timber.d("SideEffect $it")
                when (it) {
                    RegisterEffect.NavigateToHome -> {
                        Intent(this@MainActivity, HomeScreen::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        }.also { intent -> startActivity(intent) }
                    }

                    is RegisterEffect.ShowToast -> Toast.makeText(
                        this@MainActivity,
                        it.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }


    private fun validName(): String? {
        val name = binding.edtName.text.toString().trim()
        if (name.isBlank()) return "Cant be Empty"
        return null
    }

    private fun validGender(): String? {
        val gender = binding.edtGender.text.toString().trim()
        if (gender.isBlank()) return "Cant be Empty"

        if (!(gender.equals("male", true) || gender.equals("female", ignoreCase = true))) {
            return "Gender only \"Male\" or \"Female\""
        }
        return null
    }

    private fun validEmail(): String? {
        val email = binding.edtEmail.text.toString().trim()
        if (email.isBlank()) return "Cant be Empty"
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return "Invalid Email Address"
        }
        return null
    }

    private fun validStatus(): String? {
        val status = binding.edtStatus.text.toString().trim()
        if (status.isBlank()) return "Cant be Empty"

        if (!(status.equals("active", true) || status.equals("inactive", ignoreCase = true))) {
            return "Status only \"Active\" or \"Inactive\""
        }
        return null
    }

    private fun onSubmitForm() {
        var isValidForm = true

        val nameError = validName()
        binding.nameContainer.error = nameError
        binding.nameContainer.isErrorEnabled = nameError != null
        if (nameError != null) isValidForm = false

        val genderError = validGender()
        binding.genderContainer.error = genderError
        binding.genderContainer.isErrorEnabled = genderError != null
        if (genderError != null) isValidForm = false

        val emailError = validEmail()
        binding.emailContainer.error = emailError
        binding.emailContainer.isErrorEnabled = emailError != null
        if (emailError != null) isValidForm = false

        val statusError = validStatus()
        binding.statusContainer.error = statusError
        binding.statusContainer.isErrorEnabled = statusError != null
        if (statusError != null) isValidForm = false

        if (!isValidForm) {
            return
        }

        binding.apply {
            val name = edtName.text?.trim().toString()
            val gender = edtGender.text?.trim().toString()
            val email = edtEmail.text?.trim().toString()
            val status = edtStatus.text?.trim().toString()
            val user = User(null, name = name, gender = gender, email = email, status = status)
            Timber.d("onCreate: $user")

            viewModel.execute(RegisterAction.Register(user))
        }
    }
}