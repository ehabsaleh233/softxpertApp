
package com.softxpert.cars.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class CarsModel {

    @SerializedName("data")
    private List<Datum> mData;
    @SerializedName("status")
    private Long mStatus;

    public List<Datum> getData() {
        return mData;
    }

    public void setData(List<Datum> data) {
        mData = data;
    }

    public Long getStatus() {
        return mStatus;
    }

    public void setStatus(Long status) {
        mStatus = status;
    }

}
