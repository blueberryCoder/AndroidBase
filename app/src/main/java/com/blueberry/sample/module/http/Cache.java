package com.blueberry.sample.module.http;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;
import android.util.LruCache;

/**
 * Created by blueberry on 2016/8/16.
 */
public interface Cache<K, V extends Response> {

    V get(K key);

    void put(K key,V value);

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    class LruMemCache<K, V extends Response> implements Cache<K, V> {
        private static final String TAG = "LruMemCache";
        private LruCache<K, V> cache = new LruCache<K, V>((int) (Runtime.getRuntime()
                .freeMemory() / 10)) {
            @Override
            protected int sizeOf(K key, V value) {
                return value.getRawData().length;
            }
        };

        @Override
        public V get(K key) {

            Log.i(TAG, "get: ");
            return cache.get(key);
        }

        @Override
        public void put(K key, V value) {
            cache.put(key,value);
        }


    }
}
