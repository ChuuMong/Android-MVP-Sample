package io.chuumong.cleancodejava;

import android.app.Application;
import android.content.Context;

import io.chuumong.cleancodejava.di.AppComponent;
import io.chuumong.cleancodejava.di.AppModule;
import io.chuumong.cleancodejava.di.DaggerAppComponent;

/**
 * Created by JongHunLee on 2017-02-22.
 */
public class App extends Application {

    private AppComponent appComponent;

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
