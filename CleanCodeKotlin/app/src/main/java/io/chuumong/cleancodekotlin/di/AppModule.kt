package io.chuumong.cleancodekotlin.di

import dagger.Module
import dagger.Provides
import io.chuumong.cleancodekotlin.App
import io.chuumong.cleancodekotlin.data.api.CityService
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by JongHunLee on 2017-02-23.
 */

@Module
class AppModule(val app: App) {

    @Provides
    @Singleton
    fun provideApp() = app

    @Provides
    @Singleton
    fun provideCityService(retrofit: Retrofit) = retrofit.create(CityService::class.java)
}
