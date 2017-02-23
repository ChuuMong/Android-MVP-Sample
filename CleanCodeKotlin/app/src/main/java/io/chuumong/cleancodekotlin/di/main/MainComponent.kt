package io.chuumong.cleancodekotlin.di.main

import dagger.Subcomponent
import io.chuumong.cleancodekotlin.ui.activity.MainActivity
import io.chuumong.cleancodekotlin.di.ActivityScope

/**
 * Created by JongHunLee on 2017-02-23.
 */

@ActivityScope
@Subcomponent(modules = arrayOf(MainModule::class))
interface MainComponent {
    fun inject(activity: MainActivity)
}