package io.chuumong.cleancodekotlin.data.api.util

import retrofit2.Call
import retrofit2.CallAdapter
import rx.Observable
import java.lang.reflect.Type

/**
 * Created by JongHunLee on 2017-02-23.
 */
class ThreadCallAdapter(val adapter: CallAdapter<*>?, val scheduler: Scheduler) : CallAdapter<Observable<*>> {
    override fun <R : Any?> adapt(call: Call<R>?): Observable<*>? {
        if (adapter == null) {
            return null
        }

        val adapt = adapter.adapt(call)
        if (adapt is Observable<*>) {
            return adapt.subscribeOn(scheduler.background()).observeOn(scheduler.main())
        }

        return null
    }

    override fun responseType() = adapter?.responseType()
}