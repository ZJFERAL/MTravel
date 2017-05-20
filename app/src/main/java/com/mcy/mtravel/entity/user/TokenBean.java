package com.mcy.mtravel.entity.user;

import com.mcy.mtravel.entity.other.BaseBean;

/**
 * Created by machengyuan on 2017/4/12.
 */

public class TokenBean extends BaseBean {


    private DataBean data;


    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private String token;
        private int time;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }
    }
}
