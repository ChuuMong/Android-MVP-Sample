package io.chuumong.cleancodejava.di;

import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.chuumong.cleancodejava.App;
import io.chuumong.cleancodejava.BuildConfig;
import io.chuumong.cleancodejava.data.api.util.AppCallAdapter;
import io.chuumong.cleancodejava.data.api.util.NetworkErrorInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JongHunLee on 2017-02-22.
 */
@Module
public class ApiModule {

    private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss Z";

    @Provides
    @Singleton
    OkHttpClient providerOkHttpClient(NetworkErrorInterceptor interceptor) {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();

        // 디버깅 시 Http Log 출력
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }

        //        Cache cache = new Cache(app.getCacheDir(), CACHE_SIZE);
        //        builder.cache(cache);

        builder.addInterceptor(interceptor);

        return builder.build();
    }

    @Provides
    @Singleton
    Retrofit provideRestAdapter(OkHttpClient okHttpClient) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(okHttpClient);
        builder.baseUrl(BuildConfig.BASE_URL);
        builder.addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setDateFormat(DATE_FORMAT).create()));
        builder.addCallAdapterFactory(AppCallAdapter.create());

        return builder.build();
    }

    @Provides
    NetworkErrorInterceptor provideNetworkErrorInterceptor() {
        return new NetworkErrorInterceptor();
    }
}
