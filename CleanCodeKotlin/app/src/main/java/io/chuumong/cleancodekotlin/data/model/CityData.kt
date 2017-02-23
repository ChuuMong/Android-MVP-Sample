package io.chuumong.cleancodekotlin.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by JongHunLee on 2017-02-23.
 */
data class CityData(
        @SerializedName("id")
        @Expose
        val id: String,

        @SerializedName("name")
        @Expose
        val name: String,

        @SerializedName("description")
        @Expose
        val description: String,

        @SerializedName("background")
        @Expose
        val background: String
)