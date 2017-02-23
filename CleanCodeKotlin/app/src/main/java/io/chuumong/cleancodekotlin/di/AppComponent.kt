package io.chuumong.cleancodekotlin.di

import dagger.Component
import io.chuumong.cleancodekotlin.di.main.MainComponent
import io.chuumong.cleancodekotlin.di.main.MainModule
import java.util.*
import javax.inject.Singleton

/**
 * Created by JongHunLee on 2017-02-23.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class, ApiModule::class))
interface AppComponent {
    fun plus(module: MainModule): MainComponent
}