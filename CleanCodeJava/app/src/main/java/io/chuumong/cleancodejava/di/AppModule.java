package io.chuumong.cleancodejava.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.chuumong.cleancodejava.App;
import io.chuumong.cleancodejava.data.api.CityService;
import retrofit2.Retrofit;
import retrofit2.http.POST;

/**
 * Created by JongHunLee on 2017-02-22.
 */
@Module
public class AppModule {

    private final App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    App provideApp() {
        return app;
    }

    @Provides
    @Singleton
    CityService provideCityService(Retrofit retrofit) {
        return retrofit.create(CityService.class);
    }
}
