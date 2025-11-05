package id.my.mufidz.apicalling.data

import id.my.mufidz.apicalling.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface UserRepository {
    suspend fun register(user: User): DataResult<User>
    suspend fun getUsers(): DataResult<List<User>>
}

class UserRepositoryImpl @Inject constructor(private val apiServices: ApiServices) :
    UserRepository {
    override suspend fun register(user: User): DataResult<User> = withContext(Dispatchers.IO) {
        apiServices.registerUser(user)
    }

    override suspend fun getUsers(): DataResult<List<User>> = withContext(Dispatchers.IO) {
        apiServices.getAllUsers()
    }
}