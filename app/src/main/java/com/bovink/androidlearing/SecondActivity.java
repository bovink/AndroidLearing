package com.bovink.androidlearing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bovink.androidlearing.model.City;
import com.bovink.androidlearing.model.Country;
import com.bovink.androidlearing.network.ApiUtils;

import org.reactivestreams.Publisher;

import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * com.bovink.androidlearing
 *
 * @author bovink
 * @since 2017/6/24
 */

public class SecondActivity extends AppCompatActivity {

    CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_query_country)
    void queryCountry() {
        test();
    }

    private void test() {
        HashMap<String, String> params = new HashMap<>();
        params.put("population", "100000000");

        disposable.add(ApiUtils.getCityApi()
                .getCountry(params)
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<List<Country>, Publisher<Country>>() {
                    @Override
                    public Publisher<Country> apply(@NonNull List<Country> countries) throws Exception {
                        return Flowable.fromIterable(countries);
                    }
                })
                .flatMap(new Function<Country, Publisher<List<City>>>() {
                    @Override
                    public Publisher<List<City>> apply(@NonNull Country country) throws Exception {
                        return test2(country);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<List<City>>() {

                    @Override
                    public void onNext(List<City> cities) {

                        for (City city : cities) {
                            System.out.println("city.getName() = " + city.getName());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    private Publisher<List<City>> test2(Country country) {
        HashMap<String, String> params = new HashMap<>();
        params.put("code", country.getCode());

        return ApiUtils.getCityApi()
                .getCity(params);
    }


    private void getCountry() {
        HashMap<String, String> params = new HashMap<>();
        params.put("population", "100000000");

        disposable.add(ApiUtils.getCityApi()
                .getCountry(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<List<Country>>() {
                    @Override
                    public void onNext(List<Country> countries) {
                        for (Country country : countries) {
                            System.out.println("country.getCode() = " + country.getCode());
                        }

                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();

                    }

                    @Override
                    public void onComplete() {

                    }
                }));

    }

    private void getCity() {

        HashMap<String, String> params = new HashMap<>();
        params.put("code", "chn");

        disposable.add(ApiUtils.getCityApi()
                .getCity(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<List<City>>() {
                    @Override
                    public void onNext(List<City> cities) {
                        for (City city : cities) {
                            System.out.println("city.getName() = " + city.getName());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
