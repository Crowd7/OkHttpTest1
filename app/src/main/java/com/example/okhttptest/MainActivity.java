package com.example.okhttptest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.reactivestreams.Subscriber;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Button btnGet, btnJson, btnRetrofit, btnRx;
    private TextView tvResult;

    //private ExecutorService executor = Executors.newSingleThreadExecutor();

    private String data =
            "[\n" +
                    "{\n" +
                    "        \"channel\":\"東森電影台\",\n" +
                    "        \"name\":\"哈利波特\",\n" +
                    "        \"time\":\"09:00\",\n" +
                    "        \"timeinmillis\":\"1500\"\n" +
                    "    },\n" +
                    "{\n" +
                    "        \"channel\":\"HBO\",\n" +
                    "        \"name\":\"鐵達尼號\",\n" +
                    "        \"time\":\"11:30\",\n" +
                    "        \"timeinmillis\":\"10000\"\n" +
                    "    }\n" +
                    "]";

    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findUId();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://true-music-142601.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final GaeApi repo = retrofit.create(GaeApi.class);

        btnJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gson gson = new Gson();
                Type listType = new TypeToken<ArrayList<MyJsonObj>>(){

                }.getType();

                List<MyJsonObj> jsonArr = gson.fromJson(data, listType);
                StringBuffer sb = new StringBuffer();
                for(MyJsonObj obj : jsonArr){
                    sb.append("chanel:" + obj.getChannel() + ", ");
                    sb.append("name:" + obj.getName() + ", ");
                    sb.append("time:" + obj.getTime() + ", ");
                    sb.append("millis:" + obj.getTimeinmillis() + "\n");
                }
                tvResult.setText(sb.toString());
            }
        });

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://true-music-142601.appspot.com/content?type=westmovie")
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String result = response.body().string();

                        final List<MyMovieObj> movieObjList = new Gson().fromJson(result, new TypeToken<List<MyMovieObj>>(){}.getType());
                        final StringBuffer sb = new StringBuffer();

                        for(MyMovieObj obj : movieObjList) {
                            sb.append("channel:");
                            sb.append(obj.getChannel()+"\n");

                            for(MyMovieObj.InnerObj innerObj: obj.getInnerObjList()){
                                sb.append(innerObj.getName()+" "+innerObj.getTime()+"\n");
                            }
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvResult.setText(sb.toString());
                            }
                        });

                    }
                });
            }
        });

        btnRetrofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call對象只能被執行一次
                retrofit2.Call<List<TodayMovie>> call = repo.getMovieList("westmovie");
                call.enqueue(new retrofit2.Callback<List<TodayMovie>>() {
                    @Override
                    public void onResponse(retrofit2.Call<List<TodayMovie>> call, retrofit2.Response<List<TodayMovie>> response) {
                        List<TodayMovie> movieList = response.body();
                        final StringBuffer sb = new StringBuffer();

                        for(TodayMovie obj : movieList) {
                            sb.append("channel:");
                            sb.append(obj.getChannel()+"\n");

                            for(TodayMovie.ScheduleBean innerObj: obj.getSchedule()){
                                sb.append(innerObj.getName()+" "+innerObj.getTime()+"\n");
                            }
                        }

                        tvResult.setText(sb.toString());
                    }

                    @Override
                    public void onFailure(retrofit2.Call<List<TodayMovie>> call, Throwable t) {

                    }
                });
            }
        });

        final Observable observable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(@NonNull ObservableEmitter e) throws Exception {
                Log.d("android", "Observable subscribe");
                e.onNext("Hello");
                e.onComplete();
            }
        });

        final Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d("android", "observer onSubscribe()");
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.d("android", "observer onNext()");
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d("android", "observer onComplete()");
            }
        };
        btnRx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //observable.subscribe(observer);

                Flowable.just(1,2,3,4,5,6,1)
                        .subscribeOn(Schedulers.newThread())
                        .map(new Function<Integer, String>(){
                            @Override
                            public String apply(@NonNull Integer integer) throws Exception {
                                if(integer==1){
                                    return "match";
                                }else
                                    return "not match";
                            }
                        })
                        .filter(new Predicate<String>() {
                            @Override
                            public boolean test(@NonNull String s) throws Exception {
                                if(s.equals("match"))
                                    return true;
                                else
                                    return false;
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Exception {
                                Log.d("android", "msg= "+s+", thread= "+Thread.currentThread().getName());
                            }
                        });


                Observable.fromArray("one","two","three")
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Exception {

                            }
                        });
            }
        });

    }

    private void findUId() {
        btnGet = (Button) findViewById(R.id.btnGet);
        btnJson = (Button) findViewById(R.id.btnJson);
        tvResult = (TextView) findViewById(R.id.tvResult);
        btnRetrofit = (Button) findViewById(R.id.btnRetrofit);
        btnRx = (Button) findViewById(R.id.btnRx);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.newActivity:
                startActivity(new Intent(this, RandomActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
