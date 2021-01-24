
package com.softxpert.cars.data.entity;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class Datum {

    @SerializedName("brand")
    private String mBrand;
    @SerializedName("constractionYear")
    private String mConstractionYear;
    @SerializedName("id")
    private Long mId;
    @SerializedName("imageUrl")
    private String mImageUrl;
    @SerializedName("isUsed")
    private Boolean mIsUsed;


    public String getBrand() {
        return mBrand;
    }

    public void setBrand(String brand) {
        mBrand = brand;
    }

    public String getConstractionYear() {
        return mConstractionYear;
    }

    public void setConstractionYear(String constractionYear) {
        mConstractionYear = constractionYear;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public Boolean getIsUsed() {
        return mIsUsed;
    }

    public void setIsUsed(Boolean isUsed) {
        mIsUsed = isUsed;
    }

}
