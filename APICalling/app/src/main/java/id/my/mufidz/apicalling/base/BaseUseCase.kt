package id.my.mufidz.apicalling.base

import id.my.mufidz.apicalling.data.DataResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

sealed interface UseCaseResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : UseCaseResult<T>
    data class Error(val message: String) : UseCaseResult<Nothing>
}

abstract class BaseUseCase<RequestParam, ResponseObject : Any, FinalResult : Any>
    (private val dispatcher: CoroutineDispatcher) {

    protected abstract suspend fun execute(param: RequestParam): DataResult<ResponseObject>

    protected abstract suspend fun ResponseObject.transformToUseCaseResult(): FinalResult

    suspend fun getResult(param: RequestParam): UseCaseResult<FinalResult> {
        return try {
            val executionResult = withContext(dispatcher) {
                execute(param)
            }

            when (executionResult) {
                is DataResult.Failure -> UseCaseResult.Error(executionResult.error.message.toString())
                is DataResult.Success -> {
                    val data = executionResult.data.transformToUseCaseResult()
                    UseCaseResult.Success(data)
                }
            }

        } catch (e: Exception) {
            
            UseCaseResult.Error(e.message.toString())
        }
    }
}