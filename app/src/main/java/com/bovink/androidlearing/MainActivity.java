package com.bovink.androidlearing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;

public class MainActivity extends AppCompatActivity {

    CompositeDisposable disposable = new CompositeDisposable();

    @BindView(R.id.textview)
    TextView textView;

    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                test();
            }
        });
    }

    private void test() {

        final String[] strings = {"one,a", "two,b", "three,c", "four,d", "five,e"};

        disposable.add(Observable.fromArray(strings)
                .map(new Function<String, Character[]>() {
                    @Override
                    public Character[] apply(@NonNull String s) throws Exception {
                        Character[] c = new Character[s.length()];
                        for (int i = 0; i < s.length(); i++) {
                            c[i] = s.charAt(i);
                        }
                        return c;
                    }
                })
                .concatMap(new Function<Character[], ObservableSource<Character>>() {
                    @Override
                    public ObservableSource<Character> apply(@NonNull Character[] characters) throws Exception {
                        return Observable.fromArray(characters);
                    }
                })
                .subscribeWith(new DisposableObserver<Character>() {
                    @Override
                    public void onNext(@NonNull Character character) {
                        System.out.println("character = " + character);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}
