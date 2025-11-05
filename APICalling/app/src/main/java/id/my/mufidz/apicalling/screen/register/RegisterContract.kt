package id.my.mufidz.apicalling.screen.register

import id.my.mufidz.apicalling.base.ViewAction
import id.my.mufidz.apicalling.base.ViewSideEffect
import id.my.mufidz.apicalling.base.ViewState
import id.my.mufidz.apicalling.model.User

sealed class RegisterState : ViewState {
    data object Initial : RegisterState()
    data object Loading : RegisterState()
    data class Success(val user: User) : RegisterState()
    data class Error(val message: String) : RegisterState()
}

sealed interface RegisterAction : ViewAction {
    data class Register(val user: User) : RegisterAction
}

sealed interface RegisterEffect : ViewSideEffect {
    data object NavigateToHome : RegisterEffect
    data class ShowToast(val message: String) : RegisterEffect
}