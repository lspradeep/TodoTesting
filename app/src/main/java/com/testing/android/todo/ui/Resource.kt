package com.testing.android.todo.ui

class Resource<T>(val data: T?, val message: String?, val status: Status) {

    companion object {
        fun <T> Loading(data: T?, message: String?, status: Status = Status.LOADING): Resource<T> {
            return Resource(data, message, status)
        }

        fun <T> Success(data: T?, message: String?, status: Status = Status.SUCCESS): Resource<T> {
            return Resource(data, message, status)
        }

        fun <T> Error(data: T?, message: String?, status: Status = Status.ERROR): Resource<T> {
            return Resource(data, message, status)
        }

        fun <T> Offline(
            data: T?,
            message: String?,
            status: Status = Status.OFFLINE_ERROR,
        ): Resource<T> {
            return Resource(data, message, status)
        }
    }

    enum class Status {
        LOADING,
        SUCCESS,
        ERROR,
        OFFLINE_ERROR
    }
}