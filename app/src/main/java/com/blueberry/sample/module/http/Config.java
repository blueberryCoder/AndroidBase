package com.blueberry.sample.module.http;

/**
 * Created by blueberry on 2016/8/16.
 */
public  class Config {
    int connTimeOut;
    int soTimeOut;

    private Config(Builder builder) {
        connTimeOut = builder.connTimeOut;
        soTimeOut = builder.soTimeOut;
    }

    public static final class Builder {

        private static final int DEFAULT_CONN_TIME_OUT =50000;
        private static final int DEFAULT_READ_TIME_OUT =30000;

        private int connTimeOut;
        private int soTimeOut;

        public Builder(){
            connTimeOut = DEFAULT_CONN_TIME_OUT;
            soTimeOut = DEFAULT_READ_TIME_OUT;
        }

        public void setConnTimeOut(int connTimeOut) {
            this.connTimeOut = connTimeOut;
        }

        public void setSoTimeOut(int soTimeOut) {
            this.soTimeOut = soTimeOut;
        }

        public Config build() {
            return new Config(this);
        }
    }
}
