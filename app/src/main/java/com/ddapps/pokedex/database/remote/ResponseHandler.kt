package com.ddapps.pokedex.database.remote

import retrofit2.HttpException
import java.net.SocketTimeoutException

open class ResponseHandler {
    fun <T : Any> handleSuccess(data: T): Resource<T> {
        return Resource.success(data)
    }

    fun <T : Any> handleException(e: Exception): Resource<T> {
        return when (e) {
            is HttpException -> Resource.error(getErrorMessage(e.code()), null)
            is SocketTimeoutException -> Resource.error(getErrorMessage(ErrorCodes.SocketTimeOut.code), null)
            else -> Resource.error(getErrorMessage(Int.MAX_VALUE), null)
        }
    }

    private fun getErrorMessage(code: Int): String {
        return when (code) {
            -1 -> "Timeout"
            401 -> "Não autorizado"
            404 -> "Não encontrado"
            else -> "Error"
        }
    }
}

enum class ErrorCodes(val code: Int) {
    SocketTimeOut(-1),
    NotFound(404)
}