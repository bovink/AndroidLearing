package com.bovink.androidlearing.network.api;

import com.bovink.androidlearing.model.City;
import com.bovink.androidlearing.model.Country;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * com.bovink.androidlearing.network.api
 *
 * @author bovink
 * @since 2017/6/24
 */

public interface CityApi {

    @GET("city/get_country")
    Flowable<List<Country>> getCountry(@QueryMap Map<String, String> params);

    @GET("city/get_city")
    Flowable<List<City>> getCity(@QueryMap Map<String, String> params);
}
