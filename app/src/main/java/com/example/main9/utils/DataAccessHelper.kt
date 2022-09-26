package com.example.main9.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData

import com.example.main9.utils.vo.Resource
import com.example.main9.utils.vo.Status
import kotlinx.coroutines.Dispatchers

fun <T> performGetOperation(networkCall: suspend () -> Resource<T>): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Status.SUCCESS) {
            emit(responseStatus)
        } else if (responseStatus.status == Status.ERROR) {
            emit(Resource.error(responseStatus.message!!))
        }
    }