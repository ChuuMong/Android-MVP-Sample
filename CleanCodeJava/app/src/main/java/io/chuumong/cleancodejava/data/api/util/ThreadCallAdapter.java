package io.chuumong.cleancodejava.data.api.util;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import rx.Observable;

/**
 * Created by JongHunLee on 2017-02-22.
 */

class ThreadCallAdapter implements CallAdapter<Observable<?>> {

    private final CallAdapter<?> adapter;
    private final Scheduler scheduler;

    ThreadCallAdapter(CallAdapter<?> adapter, Scheduler scheduler) {
        this.adapter = adapter;
        this.scheduler = scheduler;
    }

    @Override
    public Type responseType() {
        return adapter.responseType();
    }

    @Override
    public <R> Observable<?> adapt(Call<R> call) {
        if (adapter == null) {
            return null;
        }

        Object adapt = adapter.adapt(call);

        // subscribeOn 및 observeOn 할당
        if (adapt instanceof Observable<?>) {
            return ((Observable) adapt).subscribeOn(scheduler.background()).observeOn(scheduler.main());
        }

        return null;
    }
}
