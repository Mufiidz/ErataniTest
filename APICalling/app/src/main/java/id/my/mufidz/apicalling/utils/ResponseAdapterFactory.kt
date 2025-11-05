package id.my.mufidz.apicalling.utils

import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.converter.Converter
import de.jensklingenberg.ktorfit.converter.KtorfitResult
import de.jensklingenberg.ktorfit.converter.TypeData
import id.my.mufidz.apicalling.data.DataResult
import id.my.mufidz.apicalling.data.ErrorResponse
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import timber.log.Timber
import java.lang.Exception

class ResponseAdapterFactory : Converter.Factory {
    override fun suspendResponseConverter(
        typeData: TypeData,
        ktorfit: Ktorfit
    ): Converter.SuspendResponseConverter<HttpResponse, *>? {
        if (typeData.typeInfo.type != DataResult::class) return null
        return object : Converter.SuspendResponseConverter<HttpResponse, DataResult<Any>> {
            override suspend fun convert(result: KtorfitResult): DataResult<Any> {
                Timber.d(result.toString())
                return when (result) {
                    is KtorfitResult.Failure -> {
                        val throwable = result.throwable
                        Timber.tag("Ktorfit Status").e(throwable)
                        DataResult.error(result.throwable)
                    }

                    is KtorfitResult.Success -> {
                        val statusCode = result.response.status.value
                        Timber.tag("Ktorfit Status").d(statusCode.toString())

                        when (statusCode) {
                            422 -> {
                                val errors = result.response.body<List<ErrorResponse>>()

                                val (field, message) = errors.first()
                                return DataResult.error(Exception("$field $message"))
                            }
                            200, 201 -> {
                                val data = result.response.body<Any>(typeData.typeArgs.first().typeInfo)
                                Timber.tag("Ktorfit Status").d(data.toString())
                                return DataResult.success(data)
                            }
                            else -> {
                                return DataResult.error(Exception("ERROR $statusCode"))
                            }
                        }
                    }
                }
            }

        }
    }
}