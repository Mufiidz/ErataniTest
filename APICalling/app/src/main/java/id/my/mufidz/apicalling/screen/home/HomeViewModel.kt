package id.my.mufidz.apicalling.screen.home

import dagger.hilt.android.lifecycle.HiltViewModel
import id.my.mufidz.apicalling.base.BaseViewModel
import id.my.mufidz.apicalling.base.UseCaseResult
import id.my.mufidz.apicalling.base.ViewSideEffect
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: HomeUseCase
) : BaseViewModel<HomeState, HomeAction, ViewSideEffect>(HomeState.Initial) {

    override suspend fun handleAction(action: HomeAction) = when (action) {
        is HomeAction.ShowUser -> {
            updateState { HomeState.Loading }
            when (val result = useCase.getResult(Unit)) {
                is UseCaseResult.Error -> {
                    updateState { HomeState.Error(result.message) }
                }
                is UseCaseResult.Success -> {
                    updateState { HomeState.Success(result.data) }
                }
            }
        }
    }
}