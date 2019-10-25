package com.coppermobile.android.egowall.net

/**
 * Created by Rupesh on 18, February, 2019
 */

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(msg: String, data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, msg)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }

        fun <T> idle(data: T?): Resource<T> {
            return Resource(Status.IDLE, data, null)
        }
    }
}