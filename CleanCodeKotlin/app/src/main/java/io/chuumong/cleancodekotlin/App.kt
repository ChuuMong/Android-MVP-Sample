package io.chuumong.cleancodekotlin

import android.app.Application
import android.content.Context
import io.chuumong.cleancodekotlin.di.AppComponent
import io.chuumong.cleancodekotlin.di.AppModule
import io.chuumong.cleancodekotlin.di.DaggerAppComponent

/**
 * Created by JongHunLee on 2017-02-23.
 */

class App : Application() {
    companion object {
        fun get(context: Context) = context.applicationContext as App
    }

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    fun getComponent() = appComponent
}