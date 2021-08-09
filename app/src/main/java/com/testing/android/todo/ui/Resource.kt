package com.testing.android.todo.ui

class Resource<T>() {

    var data: T? = null
    var message: String? = null
    var status: Status = Status.LOADING

    constructor(data: T?, message: String?, status: Status) : this() {
        this.data = data
        this.message = message
        this.status = status
    }

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