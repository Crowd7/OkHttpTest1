package com.example.okhttptest;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * Created by crowd on 2017/8/8.
 */

public class MyHandler {
    private Context mContext;

    public MyHandler(Context context){
        mContext = context;
    }

    public void onClickFriend(View v){
        Toast.makeText(mContext, "onClickFriend is trigger.", Toast.LENGTH_SHORT).show();
    }

    public void userHandler(User user){
        Toast.makeText(mContext, "User is now handling.", Toast.LENGTH_SHORT).show();
    }

}
