package io.chuumong.cleancodejava.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.chuumong.cleancodejava.data.model.CityData;

/**
 * Created by JongHunLee on 2017-02-22.
 */

public class CityList {

    @SerializedName("data")
    @Expose
    private List<CityData> data;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("status")
    @Expose
    private int status;

    public List<CityData> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
