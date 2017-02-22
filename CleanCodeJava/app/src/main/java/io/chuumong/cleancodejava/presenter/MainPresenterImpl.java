package io.chuumong.cleancodejava.presenter;

import io.chuumong.cleancodejava.data.repository.MainRepository;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by JongHunLee on 2017-02-22.
 */

public class MainPresenterImpl implements MainPresenter {

    private final MainView view;
    private final MainRepository repository;
    private final CompositeSubscription subscription;

    public MainPresenterImpl(MainView view, MainRepository repository) {
        this.view = view;
        this.repository = repository;
        this.subscription = new CompositeSubscription();
    }

    @Override
    public void getCityList() {
        view.showProgress();

        subscription.add(repository.getCitys().doOnUnsubscribe(view::hiedProgress).subscribe(view::resultCityList, view::onError));
    }

    @Override
    public void detachView() {
        subscription.unsubscribe();
    }
}
