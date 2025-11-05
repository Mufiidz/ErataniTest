package id.my.mufidz.apicalling.data

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import id.my.mufidz.apicalling.model.User

interface ApiServices {
    @POST("users")
    suspend fun registerUser(@Body user: User) : DataResult<User>

    @GET("users")
    suspend fun getAllUsers() : DataResult<List<User>>
}