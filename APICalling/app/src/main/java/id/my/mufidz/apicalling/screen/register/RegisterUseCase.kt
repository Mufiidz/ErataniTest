package id.my.mufidz.apicalling.screen.register

import id.my.mufidz.apicalling.base.BaseUseCase
import id.my.mufidz.apicalling.data.DataResult
import id.my.mufidz.apicalling.data.UserRepository
import id.my.mufidz.apicalling.di.IODispatcher
import id.my.mufidz.apicalling.model.User
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    @IODispatcher dispatcher: CoroutineDispatcher,
    private val userRepository: UserRepository
) : BaseUseCase<User, User, User>(dispatcher) {

    override suspend fun execute(param: User): DataResult<User> = userRepository.register(param)

    override suspend fun User.transformToUseCaseResult(): User = this

}