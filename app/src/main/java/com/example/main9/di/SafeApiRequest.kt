package com.example.main9.di

import retrofit2.Response

//abstract class SafeApiRequest {
//
//    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
//
//        Timber.e("Api Request")
//        val response = call.invoke()
//
//        if (response.isSuccessful && response.body() != null) {
//            Timber.e(response.body().toString())
//            return response.body()!!
//
//        } else {
//            Timber.e(response.code().toString())
//            Timber.e(response.message())
//            throw Exception(response.code().toString())
//        }
//
//    }
//
//}

//abstract class SafeApiRequest {
//
//    protected suspend fun <T> apiRequest(call: suspend () -> Response<T>): Resource<T> {
//        try {
//            val response = call()
//            if (response.isSuccessful) {
//                val body = response.body()
//                if (body != null) return Resource.success(body)
//            }
//            return throwError("${response.code()}: ${response.message()}")
//        } catch (e: Exception) {
//            return throwError(e.message ?: "No internet connection")
//        }
//    }
//
//    private fun <T> throwError(message: String): Resource<T> =
//        Resource.error("Network call has failed for a following reason: $message")
//
//}