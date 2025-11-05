package id.my.mufidz.apicalling.screen.register

import dagger.hilt.android.lifecycle.HiltViewModel
import id.my.mufidz.apicalling.base.BaseViewModel
import id.my.mufidz.apicalling.base.UseCaseResult
import id.my.mufidz.apicalling.model.User
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : BaseViewModel<RegisterState, RegisterAction, RegisterEffect>(RegisterState.Initial) {

    override suspend fun handleAction(action: RegisterAction) {
        when (action) {
            is RegisterAction.Register -> submitRegister(action.user)
        }
    }

    private suspend fun submitRegister(user: User) {
        updateState { RegisterState.Loading }
        when (val result = registerUseCase.getResult(user)) {
            is UseCaseResult.Error -> {
                updateState { RegisterState.Error(result.message) }
                sendEffect(RegisterEffect.ShowToast("Login gagal, Coba lagi."))
            }
            is UseCaseResult.Success -> {
                updateState { RegisterState.Success(result.data) }
                sendEffect(RegisterEffect.NavigateToHome)
            }
        }
    }
}