package id.my.mufidz.apicalling.data

sealed class DataResult<out T> {
    data class Success<T>(val data: T) : DataResult<T>()
    data class Failure(val error: Throwable) : DataResult<Nothing>()

    companion object {
        fun <T> success(data: T) = Success(data)
        fun error(e: Throwable) = Failure(e)
    }
}