package io.chuumong.cleancodejava.di.main;

import dagger.Module;
import dagger.Provides;
import io.chuumong.cleancodejava.data.api.CityService;
import io.chuumong.cleancodejava.data.repository.MainRepository;
import io.chuumong.cleancodejava.di.ActivityScope;
import io.chuumong.cleancodejava.presenter.MainPresenter;
import io.chuumong.cleancodejava.presenter.MainPresenterImpl;
import io.chuumong.cleancodejava.presenter.MainView;

/**
 * Created by JongHunLee on 2017-02-22.
 */

@Module
public class MainModule {

    private final MainView view;

    public MainModule(MainView view) {
        this.view = view;
    }

    @Provides
    @ActivityScope
    MainRepository provideMainRepository(CityService service) {
        return new MainRepository(service);
    }

    @Provides
    @ActivityScope
    MainPresenter provideMainPresenter(MainRepository repository) {
        return new MainPresenterImpl(view, repository);
    }


}
