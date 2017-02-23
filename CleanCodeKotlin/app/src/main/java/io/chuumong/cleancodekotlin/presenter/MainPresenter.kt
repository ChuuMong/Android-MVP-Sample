package io.chuumong.cleancodekotlin.presenter

import io.chuumong.cleancodekotlin.data.model.CityList
import io.chuumong.cleancodekotlin.data.repository.CityRepository
import rx.subscriptions.CompositeSubscription

/**
 * Created by JongHunLee on 2017-02-23.
 */

interface MainView {
    fun showProgress()

    fun hiedProgress()

    fun onError(throwable: Throwable)

    fun resultCityList(citys: CityList)
}

interface MainPresenter {

    fun getCityList()

    fun detachView()
}

class MainPresenterImpl(val view: MainView, val repository: CityRepository) : MainPresenter {

    private val subscription = CompositeSubscription()

    override fun getCityList() {
        view.showProgress()
        
        subscription.add(repository.getCitys().doOnUnsubscribe { view.hiedProgress() }
                                 .subscribe({ view.resultCityList(it) },
                                            { throwable -> view.onError(throwable) }))
    }

    override fun detachView() {
        subscription.unsubscribe()
    }
}