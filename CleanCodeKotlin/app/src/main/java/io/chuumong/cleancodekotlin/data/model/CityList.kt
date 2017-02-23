package io.chuumong.cleancodekotlin.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by JongHunLee on 2017-02-23.
 */
data class CityList(
        @SerializedName("data")
        @Expose
        val data: List<CityData>,

        @SerializedName("message")
        @Expose
        val message: String,

        @SerializedName("status")
        @Expose
        val status: Int)