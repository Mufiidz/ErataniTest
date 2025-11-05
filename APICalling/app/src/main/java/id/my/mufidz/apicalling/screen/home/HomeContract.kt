package id.my.mufidz.apicalling.screen.home

import id.my.mufidz.apicalling.base.ViewAction
import id.my.mufidz.apicalling.base.ViewState
import id.my.mufidz.apicalling.model.User

sealed class HomeState : ViewState {
    data object Initial : HomeState()
    data object Loading : HomeState()
    data class Success(val users: List<User>) : HomeState()
    data class Error(val message: String) : HomeState()
}

sealed interface HomeAction : ViewAction {
    object ShowUser : HomeAction
}