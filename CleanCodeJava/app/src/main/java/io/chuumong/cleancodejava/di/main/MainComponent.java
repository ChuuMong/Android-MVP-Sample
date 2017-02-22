package io.chuumong.cleancodejava.di.main;

import dagger.Component;
import dagger.Subcomponent;
import io.chuumong.cleancodejava.di.ActivityScope;
import io.chuumong.cleancodejava.di.ApiModule;
import io.chuumong.cleancodejava.ui.activity.MainActivity;

/**
 * Created by JongHunLee on 2017-02-22.
 */

@ActivityScope
@Subcomponent(modules = MainModule.class)
public interface MainComponent {

    void inject(MainActivity activity);
}
