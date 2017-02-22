package io.chuumong.cleancodejava.data.api.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by JongHunLee on 2017-02-22.
 */

public class AppCallAdapter extends CallAdapter.Factory {

    private final Scheduler scheduler;

    private AppCallAdapter(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public static AppCallAdapter create() {
        // IScheduler에 Scheduler 할당
        return new AppCallAdapter(new Scheduler() {
            @Override
            public rx.Scheduler main() {
                return AndroidSchedulers.mainThread();
            }

            @Override
            public rx.Scheduler background() {
                return Schedulers.io();
            }
        });
    }

    @Override
    public CallAdapter<?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        // RxJava CallAdapter 초기화
        CallAdapter<?> adapter = RxJavaCallAdapterFactory.create().get(returnType, annotations, retrofit);
        return new ThreadCallAdapter(adapter, scheduler);
    }
}
