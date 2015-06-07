package com.example.joakes.xbox_sidekick.requests.utils;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;

import java.util.Map;

/**
 * Created by joakes on 6/7/15.
 */
public abstract class JSONArrayRequester {
    private String mUrl;
    private String mTag;
    private Context mContext;
    private Map<String, String> mHeaders;

    public JSONArrayRequester(Context context, String url, String tag) {
        mContext = context;
        mUrl = url;
        mTag = tag;
    }

    public JSONArrayRequester(Context context, String url, String tag, Map<String, String> headers) {
        mContext = context;
        mUrl = url;
        mTag = tag;
        mHeaders = headers;
    }

    public void makeRequest() {
        JSONArrayRequestWithHeaders request = new JSONArrayRequestWithHeaders(
                mUrl,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        handleSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        handleError(error);
                    }
                }
        );
        request.setHeaders(mHeaders);
        WebRequestQueue.getInstance(mContext).addToQueue(request, mTag);
    }

    public abstract void handleSuccess(JSONArray response);

    public abstract void handleError(VolleyError error);
}
