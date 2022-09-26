package com.example.main9.ui.repository

import com.example.main9.utils.vo.Resource
import retrofit2.Response
import timber.log.Timber

abstract class ResponseHelper {

    protected suspend fun <T> getResponseResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            Timber.e("Api Request")
            val response = call()
            if (response.isSuccessful) {
                Timber.e(response.body().toString())
                val body = response.body()
                if (body != null) return Resource.success(body)
            }
            Timber.e(response.code().toString())
            Timber.e(response.message())
            return throwError("${response.code()}: ${response.message()}")
        } catch (e: Exception) {
            return throwError(e.message ?: "No internet connection")
        }
    }

    private fun <T> throwError(message: String): Resource<T> =
        Resource.error("Network call has failed for a following reason: $message")

}