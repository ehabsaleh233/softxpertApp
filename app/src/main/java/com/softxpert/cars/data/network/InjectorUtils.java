package com.softxpert.cars.data.network;

public class InjectorUtils {

    public static ApiService provideWinchWebService() {
        ApiService webServiceClient = WebServiceClient.getClient().create(ApiService.class);
        return webServiceClient;
    }

}