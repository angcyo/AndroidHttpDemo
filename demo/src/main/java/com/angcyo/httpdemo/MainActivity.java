package com.angcyo.httpdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getTest(View view) {
        api().getTest()
                .compose(MainActivity.<ResponseBody>defaultTransformer())
                .subscribe(defaultSubscribe());
    }


    public void getTestParam1(View view) {
        HashMap<String, String> map = new HashMap();
        map.put("key1", "value1");
        map.put("key2", "value2");
        api().getTestParam(map)
                .compose(MainActivity.<ResponseBody>defaultTransformer())
                .subscribe(defaultSubscribe());
    }

    public void getTestParam2(View view) {
        api().getTestParam2("p1", "p2")
                .compose(MainActivity.<ResponseBody>defaultTransformer())
                .subscribe(defaultSubscribe());
    }

    public void getTestParam3(View view) {
        api().getTestParam3("p1", "p2")
                .compose(MainActivity.<ResponseBody>defaultTransformer())
                .subscribe(defaultSubscribe());
    }

    public void postTest(View view) {
        api().postTest()
                .compose(MainActivity.<ResponseBody>defaultTransformer())
                .subscribe(defaultSubscribe());
    }

    public void postTestParam1(View view) {
        HashMap<String, String> map = new HashMap();
        map.put("key1", "value1");
        map.put("key2", "value2");
        api().postTestParam(map)
                .compose(MainActivity.<ResponseBody>defaultTransformer())
                .subscribe(defaultSubscribe());
    }

    public void postTestParam2(View view) {
        String json = "{\n" +
                "\n" +
                "    \"key1\": \"value1\",\n" +
                "    \"key2\": \"value2\"\n" +
                "}";
        RequestBody requestBody = RequestBody.create(MultipartBody.FORM, json);

        api().postTestParam2(requestBody)
                .compose(MainActivity.<ResponseBody>defaultTransformer())
                .subscribe(defaultSubscribe());
    }

    public Api api() {
        return retrofit().create(Api.class);
    }

    public Retrofit retrofit() {
        return new Retrofit.Builder()
                .client(new OkHttpClient.Builder().build())
                .baseUrl(Api.url)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static <T> Observable.Transformer<T, T> defaultTransformer() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.unsubscribeOn(Schedulers.io())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public Subscriber<ResponseBody> defaultSubscribe() {
        return new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                TextView textView = findViewById(R.id.text_view);
                try {
                    textView.setText(responseBody.string());
                } catch (IOException e) {
                    e.printStackTrace();
                    textView.setText(e.getMessage());
                }
            }
        };
    }
}
