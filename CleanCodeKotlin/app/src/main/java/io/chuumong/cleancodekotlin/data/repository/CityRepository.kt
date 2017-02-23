package io.chuumong.cleancodekotlin.data.repository

import io.chuumong.cleancodekotlin.data.api.CityService

/**
 * Created by JongHunLee on 2017-02-23.
 */

class CityRepository(val service: CityService) {
    fun getCitys() = service.getCitys()
}