package io.chuumong.cleancodekotlin.di.main

import dagger.Module
import dagger.Provides
import io.chuumong.cleancodekotlin.ui.activity.MainActivity
import io.chuumong.cleancodekotlin.data.api.CityService
import io.chuumong.cleancodekotlin.data.repository.CityRepository
import io.chuumong.cleancodekotlin.di.ActivityScope
import io.chuumong.cleancodekotlin.presenter.MainPresenter
import io.chuumong.cleancodekotlin.presenter.MainPresenterImpl

/**
 * Created by JongHunLee on 2017-02-23.
 */
@Module
class MainModule(val activity: MainActivity) {


    @Provides
    @ActivityScope
    fun provideCityRepository(service: CityService) = CityRepository(service)

    @Provides
    @ActivityScope
    fun provideMainPresenter(repository: CityRepository): MainPresenter = MainPresenterImpl(activity, repository)
}

