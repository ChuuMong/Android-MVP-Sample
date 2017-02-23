package io.chuumong.cleancodekotlin.data.api

import io.chuumong.cleancodekotlin.data.model.CityList
import retrofit2.http.GET
import rx.Observable

/**
 * Created by JongHunLee on 2017-02-23.
 */
interface CityService {
    @GET("v1/city")
    fun getCitys(): Observable<CityList>
}