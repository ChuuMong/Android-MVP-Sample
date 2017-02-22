package io.chuumong.cleancodejava.presenter;

import io.chuumong.cleancodejava.data.model.CityList;

/**
 * Created by JongHunLee on 2017-02-22.
 */

public interface MainView {

    void showProgress();

    void hiedProgress();

    void onError(Throwable throwable);

    void resultCityList(CityList citys);
}
