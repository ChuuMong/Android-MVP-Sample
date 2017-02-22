package io.chuumong.cleancodejava.data.api.util;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by JongHunLee on 2017-02-22.
 */

public class NetworkErrorInterceptor implements Interceptor {

    private static final String TAG = NetworkErrorInterceptor.class.getSimpleName();

    private static final int RETRY_COUNT = 10;
    private static final int RETRY_DELAY_TIME = 3 * 1000;

    @Override
    public Response intercept(Interceptor.Chain chain) {
        Request request = chain.request();
        // Request 시도
        Response response = null;
        IOException exception = new IOException();

        try {
            response = chain.proceed(request);
        }
        catch (IOException e) {
            exception = e;
            e.printStackTrace();
        }

        int tryCount = 0;
        // Response == null 이거나 Fail이며 tryCount가 30이 안될 때
        while ((response == null || !response.isSuccessful()) && tryCount < RETRY_COUNT) {
            Log.d(TAG, "intercept#request fail tryCount : " + tryCount);

            tryCount++;

            try {
                Thread.sleep(RETRY_DELAY_TIME);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Request 재 시도
            try {
                response = chain.proceed(request);
            }
            catch (IOException e) {
                if (e.getMessage().equals("Canceled")) {
                    break;
                }

                exception = e;
                e.printStackTrace();
            }
        }

        if (response == null) {
            throw new RuntimeException(exception.getMessage());
        }

        return response;
    }
}
