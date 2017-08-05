package com.example.okhttptest;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.okhttptest.databinding.ActivityRandomBinding;

public class RandomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        ActivityRandomBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_random);

        binding.setUser(new User("YunChang", "Lee", 29));


    }
}
