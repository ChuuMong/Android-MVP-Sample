package io.chuumong.cleancodekotlin.data.api.util

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by JongHunLee on 2017-02-23.
 */

class NetworkErrorInterceptor : Interceptor {

    private val TAG = NetworkErrorInterceptor::class.java.simpleName

    private val RETRY_COUNT = 3

    private val RETRY_DELAY_TIME = 3 * 1000

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var response: Response? = null
        var exception: IOException? = null

        try {
            response = chain.proceed(request)
        }
        catch (e: IOException) {
            exception = e
            e.printStackTrace()
        }

        var tryCount = 0

        while (response == null || !response.isSuccessful && tryCount < RETRY_COUNT) {
            Log.d(TAG, "intercept#request fail tryCount : $tryCount")
            tryCount++

            try {
                Thread.sleep(RETRY_DELAY_TIME.toLong())
            }
            catch (e: InterruptedException) {
                e.printStackTrace()
            }

            // Request 재 시도
            try {
                response = chain.proceed(request)
            }
            catch (e: IOException) {
                if (e.message == "Canceled") {
                    break
                }

                exception = e
                e.printStackTrace()
            }

        }

        if (response == null) {
            throw RuntimeException(exception?.message)
        }

        return response
    }
}