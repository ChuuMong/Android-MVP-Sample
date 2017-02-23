package io.chuumong.cleancodekotlin.data.api.util

import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.lang.reflect.Type

/**
 * Created by JongHunLee on 2017-02-23.
 */
class ApiCallAdapter(val scheduler: Scheduler) : CallAdapter.Factory() {

    companion object {
        fun create(): ApiCallAdapter {
            return ApiCallAdapter(object : Scheduler {
                override fun background(): rx.Scheduler {
                    return Schedulers.io()
                }

                override fun main(): rx.Scheduler {
                    return AndroidSchedulers.mainThread()
                }
            })
        }
    }

    override fun get(returnType: Type?, annotations: Array<out Annotation>?, retrofit: Retrofit?): CallAdapter<*> {
        val adapter = RxJavaCallAdapterFactory.create().get(returnType, annotations, retrofit)
        return ThreadCallAdapter(adapter, scheduler)
    }
}