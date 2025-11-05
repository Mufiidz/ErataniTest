package id.my.mufidz.apicalling.screen.home

import id.my.mufidz.apicalling.base.BaseUseCase
import id.my.mufidz.apicalling.data.DataResult
import id.my.mufidz.apicalling.data.UserRepository
import id.my.mufidz.apicalling.di.IODispatcher
import id.my.mufidz.apicalling.model.User
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    @IODispatcher dispatcher: CoroutineDispatcher,
    private val userRepository: UserRepository,
) : BaseUseCase<Unit, List<User>, List<User>>(dispatcher) {
    override suspend fun execute(param: Unit): DataResult<List<User>> = userRepository.getUsers()

    override suspend fun List<User>.transformToUseCaseResult(): List<User> = this
}