package com.example.joakes.xbox_sidekick.requests;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by joakes on 6/7/15.
 */
public abstract class JSONObjectRequester {
    private String mUrl;
    private String mTag;
    private Context mContext;
    private Map<String, String> mHeaders;

    public JSONObjectRequester(Context context, String url, String tag) {
        mContext = context;
        mUrl = url;
        mTag = tag;
    }

    public JSONObjectRequester(Context context, String url, String tag, Map<String, String> headers) {
        mContext = context;
        mUrl = url;
        mTag = tag;
        mHeaders = headers;
    }

    public void makeRequest() {
        JSONObjectRequestWithHeaders request = new JSONObjectRequestWithHeaders(
                mUrl,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
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

    public abstract void handleSuccess(JSONObject response);

    public abstract void handleError(VolleyError error);
}
