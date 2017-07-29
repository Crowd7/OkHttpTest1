package com.example.okhttptest;

import java.util.List;

/**
 * Created by crowd on 2017/7/29.
 */

public class TodayMovie {
    /**
     * schedule : [{"time":"22:50","status":"finished","name":"　★ 神奇大隊長","calendar":1501253400000},{"time":"00:50","status":"finished","name":"　 飆風特攻","calendar":1501260600000},{"time":"02:40","status":"finished","name":"　★ 靈異象限","calendar":1501267200000},{"time":"04:25","status":"finished","name":"　 天性使然","calendar":1501273500000},{"time":"06:00","status":"finished","name":"　 失落的帝國","calendar":1501279200000},{"time":"07:35","status":"finished","name":"　 倩影刺客","calendar":1501284900000},{"time":"09:05","status":"finished","name":"　★ 神奇大隊長","calendar":1501290300000},{"time":"11:05","status":"finished","name":"　★ 哈利波特：鳳凰會的密令","calendar":1501297500000},{"time":"13:20","status":"nowon","name":"　 飆風特攻","calendar":1501305600000},{"time":"15:15","status":"unfinished","name":"　 波特萊爾的冒險","calendar":1501312500000},{"time":"17:00","status":"unfinished","name":"　★ 鼠膽妙算","calendar":1501318800000},{"time":"18:30","status":"unfinished","name":"　★ 哈利波特：混血王子的背叛","calendar":1501324200000},{"time":"21:00","status":"unfinished","name":"　 國定殺戮日:大選之年","calendar":1501333200000},{"time":"22:50","status":"unfinished","name":"　 冰與火之歌:權力遊戲 第7季 02","calendar":1501339800000},{"time":"23:50","status":"unfinished","name":"　★ 紳士密令","calendar":1501343400000}]
     * channel : HBO電影台
     */

    private String channel;
    private List<ScheduleBean> schedule;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public List<ScheduleBean> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<ScheduleBean> schedule) {
        this.schedule = schedule;
    }

    public static class ScheduleBean {
        /**
         * time : 22:50
         * status : finished
         * name : 　★ 神奇大隊長
         * calendar : 1501253400000
         */

        private String time;
        private String status;
        private String name;
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
