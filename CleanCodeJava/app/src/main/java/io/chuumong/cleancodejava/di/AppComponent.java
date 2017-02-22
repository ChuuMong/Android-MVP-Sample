package io.chuumong.cleancodejava.di;

import javax.inject.Singleton;

import dagger.Component;
import io.chuumong.cleancodejava.di.main.MainComponent;
import io.chuumong.cleancodejava.di.main.MainModule;

/**
 * Created by JongHunLee on 2017-02-22.
 */


@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {

    MainComponent plus(MainModule module);
}
