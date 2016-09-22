package com.blueberry.sample.module.http;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.blueberry.sample.R;
import com.blueberry.sample.common.BaseActivity;
import com.orhanobut.logger.Logger;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by blueberry on 2016/8/16.
 */
public class HttpActivity extends BaseActivity {
public static ArrayList<Activity> data = new ArrayList();
    @BindView(R.id.btn_send)
    Button btnSend;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_http);
        ButterKnife.bind(this);

        data.add(this);
    }

    @OnClick({R.id.btn_send, R.id.btn_send_multipart})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                setGetRequest();
                break;
            case R.id.btn_send_multipart:
                sendMultiRequest();
                break;
        }

    }

    private void sendMultiRequest() {
        RequestQueue mQueue = NetManager.newRequestQueue();
        MultipartRequest multipartRequest = new MultipartRequest(Request.HttpMethod.POST,
                "http://192.168.10.142:8080/WebTest/hello", new Request.RequestListener<String>() {
            @Override
            public void onComplete(int stCode, String response, String errMsg) {
                Logger.i("code = %d, response= %s, errMsg= %s", stCode, response, errMsg);
            }
        });
        multipartRequest.setShouldCache(false);
        MultipartEntity multipartEntity = multipartRequest.getMultipartEntity();
        //文本参数
        multipartEntity.addStringPart("location","模拟地理位置");
        multipartEntity.addStringPart("type","0");
        Bitmap  bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.girl);
        //直接上传 Bitmap
        multipartEntity.addByteArrayPart("images",bitmapToBytes(bitmap));
        // 上传文件
        InputStream inputStream = getResources().openRawResource(R.raw.girl);
        FileOutputStream fos=null;

        try {
            fos= new FileOutputStream(new File(getExternalCacheDir().getPath(),"test.jpg"));
            int len =0;
            byte[] bytes = new byte[8096];
            while ((len=inputStream.read(bytes))>0){
                fos.write(bytes,0,len);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeSilently(inputStream, fos);
        }
        multipartEntity.addFilePart("imgFile",new File(getExternalCacheDir().getPath(),"test.jpg"));
        // 4.将请求添加到队列中
        mQueue.addRequest(multipartRequest);
        mQueue.start();
    }

    private void closeSilently(InputStream inputStream, FileOutputStream fos) {
        if (fos!=null) try {
            inputStream.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] bitmapToBytes(Bitmap bitmap) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG,100,bos);
        byte[] bytes = new byte[bitmap.getRowBytes()];
        try {
            bos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bos.toByteArray();
    }


    private void setGetRequest() {
        StringRequest stringRequest = new StringRequest(Request.HttpMethod.GET,
                "http://www.baidu.com",
                new Request.RequestListener<String>() {
                    @Override
                    public void onComplete(int stCode, String response, String errMsg) {
                        Logger.i("code = %d, response= %s, errMsg= %s", stCode, response, errMsg);
                    }
                });
        stringRequest.setShouldCache(true);
        RequestQueue mQueue = NetManager.newRequestQueue();
        mQueue.addRequest(stringRequest);
        mQueue.start();
    }
}
