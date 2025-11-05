package id.my.mufidz.apicalling.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<State : ViewState, Action : ViewAction, Effect : ViewSideEffect>(
    initState: State
) : ViewModel() {

    private val _viewState = MutableStateFlow(initState)
    val viewState: StateFlow<State> = _viewState.asStateFlow()

    protected val currentState: State
        get() = _viewState.value

    private val _sideEffect = Channel<Effect>()
    val sideEffect = _sideEffect.receiveAsFlow()

    fun execute(action: Action) {

        viewModelScope.launch {
            handleAction(action)
        }
    }

    protected abstract suspend fun handleAction(action: Action)

    protected fun updateState(newState: (State) -> State) {
        _viewState.value = newState(_viewState.value)
    }

    protected fun sendEffect(effect: Effect) {
        viewModelScope.launch {
            _sideEffect.send(effect)
        }
    }
}