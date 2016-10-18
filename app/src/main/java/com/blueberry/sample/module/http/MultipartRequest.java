package com.blueberry.sample.module.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by blueberry on 2016/8/16.
 */
public class MultipartRequest extends Request<String> {
    MultipartEntity multipartEntity = new MultipartEntity();


    public MultipartRequest(HttpMethod httpMethod, String url, RequestListener<String> listener) {
        super(HttpMethod.POST, url, listener);
    }

    public MultipartEntity getMultipartEntity() {
        return multipartEntity;
    }

    @Override
    public String parseResponse(Response response) {
        if(response!=null && response.getRawData()!=null)
            return new String (response.getRawData());
        return null;
    }

    @Override
    public byte[] getBody() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            multipartEntity.writeTo(bos);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bos.toByteArray();
    }

    @Override
    public String getBodyContentType() {
        return multipartEntity.getContentType().getValue();
    }
}
