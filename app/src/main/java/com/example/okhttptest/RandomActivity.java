package com.example.okhttptest;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.okhttptest.databinding.ActivityRandomBinding;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RandomActivity extends AppCompatActivity {

    private Retrofit retrofit;

    ActivityRandomBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://true-music-142601.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_random);

        binding.setUser(new User("YunChang", "Lee", 29));

        binding.setUser2(new User("Cindy", "Ke", 29));

        binding.setHandler(new MyHandler(this));

        binding.btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("android", "binding onClick() called");
                retrofit.create(GaeApi.class)
                        .getRxMovieList("westmovie")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<List<TodayMovie>>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull List<TodayMovie> todayMovies) {

                                final StringBuffer sb = new StringBuffer();

                                for(TodayMovie obj : todayMovies) {
                                    sb.append("channel:");
                                    sb.append(obj.getChannel()+"\n");

                                    for(TodayMovie.ScheduleBean innerObj: obj.getSchedule()){
                                        sb.append(innerObj.getName()+" "+innerObj.getTime()+"\n");
                                    }
                                }

                                binding.tvShow.setText(sb.toString());
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Toast.makeText(RandomActivity.this, "錯誤!!!!!", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onComplete() {
                                Toast.makeText(RandomActivity.this, "已完成載入", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    public void clickShowTag(View view){
        User u = (User) view.getTag();

        Toast.makeText(this, "user= "+u.getLastName()+" "+u.getFirstName(), Toast.LENGTH_SHORT).show();
    }
}
