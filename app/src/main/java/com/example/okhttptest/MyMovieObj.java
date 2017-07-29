package com.example.okhttptest;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by crowd on 2017/7/29.
 */

public class MyMovieObj {
    @SerializedName("schedule")
    private List<InnerObj> innerObjList;

    @SerializedName("channel")
    private String channel;

    public List<InnerObj> getInnerObjList() {
        return innerObjList;
    }

    public void setInnerObjList(List<InnerObj> innerObjList) {
        this.innerObjList = innerObjList;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public class InnerObj{
        @SerializedName("time")
        private String time;

        @SerializedName("status")
        private String status;

        @SerializedName("name")
        private String name;

        @SerializedName("calendar")
        private long calendar;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getCalendar() {
            return calendar;
        }

        public void setCalendar(long calendar) {
            this.calendar = calendar;
        }
    }

}
