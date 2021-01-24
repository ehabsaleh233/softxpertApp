package com.softxpert.cars.ui.activity.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.softxpert.cars.data.entity.CarsModel;
import com.softxpert.cars.data.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private ApiService mWebService;
    private MutableLiveData<CarsModel> carsList;
    private MutableLiveData<String> mHandleError;

    public void setWebService(ApiService mWebService) {
        this.mWebService = mWebService;
    }
    public MutableLiveData<String> getmHandleError() {
        return mHandleError;
    }
    public MutableLiveData<CarsModel> getmList() {
        return carsList;
    }
    public MainViewModel() {
        carsList = new MutableLiveData<>();
        mHandleError = new MutableLiveData<>();
    }

    public void loadCarsList(int page) {
         mWebService.getCarsList(page).enqueue(new Callback<CarsModel>() {
            @Override
            public void onResponse(Call<CarsModel> call, Response<CarsModel> response) {
                 if (response.isSuccessful()) {
                     if (response.body().getData().size()!=0) {
                         carsList.setValue(response.body());
                     }
                }
            }

            @Override
            public void onFailure(Call<CarsModel> call, Throwable t) {
                 mHandleError.setValue("fail");
                Log.e("ListFil", "" + t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
