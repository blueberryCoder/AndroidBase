package com.blueberry.sample.module.http;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by blueberry on 2016/8/16.
 */
public class JsonRequest extends Request<JSONObject> {

    public JsonRequest(HttpMethod httpMethod, String url, RequestListener<JSONObject> listener) {
        super(httpMethod, url, listener);
    }

    @Override
    public JSONObject parseResponse(Response response) {
        String jsonString = new String(response.getRawData());
        try {
            return new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
