package io.chuumong.cleancodejava.data.repository;

import io.chuumong.cleancodejava.data.api.CityService;
import io.chuumong.cleancodejava.data.model.CityList;
import rx.Observable;

/**
 * Created by JongHunLee on 2017-02-22.
 */

public class MainRepository {

    private final CityService service;

    public MainRepository(CityService service) {
        this.service = service;
    }

    public Observable<CityList> getCitys() {
        return service.getCitys();
    }
}
