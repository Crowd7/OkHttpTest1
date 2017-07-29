package com.example.okhttptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private Button btnGet, btnJson;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findUId();

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

    }

    private void findUId() {
        btnGet = (Button) findViewById(R.id.btnGet);
        btnJson = (Button) findViewById(R.id.btnJson);
        tvResult = (TextView) findViewById(R.id.tvResult);
    }
}
