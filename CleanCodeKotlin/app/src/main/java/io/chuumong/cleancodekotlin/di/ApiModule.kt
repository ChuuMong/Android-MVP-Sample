package io.chuumong.cleancodekotlin.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.chuumong.cleancodekotlin.BuildConfig
import io.chuumong.cleancodekotlin.data.api.util.ApiCallAdapter
import io.chuumong.cleancodekotlin.data.api.util.NetworkErrorInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by JongHunLee on 2017-02-23.
 */

@Module
class ApiModule {
    private val DATE_FORMAT = "yyyy/MM/dd HH:mm:ss Z"

    @Provides
    @Singleton
    fun providerOkHttpClient(interceptor: NetworkErrorInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(logging)
        }

        builder.addInterceptor(interceptor)

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideNetworkErrorInterceptor() = NetworkErrorInterceptor()

    @Provides
    @Singleton
    fun provideRestAdapter(client: OkHttpClient) = Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setDateFormat(DATE_FORMAT).create()))
            .addCallAdapterFactory(ApiCallAdapter.create()).build()
}
