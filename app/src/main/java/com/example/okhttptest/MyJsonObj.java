package com.example.okhttptest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by crowd on 2017/7/29.
 */

public class MyJsonObj {

    @SerializedName("channel")
    private String channel;

    @SerializedName("name")
    private String name;

    @SerializedName("time")
    private String time;

    @SerializedName("timeinmillis")
    private long timeinmillis;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getTimeinmillis() {
        return timeinmillis;
    }

    public void setTimeinmillis(long timeinmillis) {
        this.timeinmillis = timeinmillis;
    }
}
