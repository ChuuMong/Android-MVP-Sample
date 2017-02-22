package io.chuumong.cleancodejava.data.api;

import io.chuumong.cleancodejava.data.model.CityList;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by JongHunLee on 2017-02-22.
 */

public interface CityService {

    @GET("v1/city")
    Observable<CityList> getCitys();

}
