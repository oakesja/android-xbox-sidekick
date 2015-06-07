package com.example.joakes.xbox_sidekick.requests;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import java.util.Map;

/**
 * Created by joakes on 4/28/15.
 */
public class JSONArrayRequestWithHeaders extends JsonArrayRequest {
    private Map<String, String> mHeaders;

    public JSONArrayRequestWithHeaders(String url,
                                       Response.Listener<JSONArray> listener,
                                       Response.ErrorListener errorListener) {
        super(Method.GET, url, listener, errorListener);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return mHeaders;
    }

    public void setHeaders(Map<String, String> headers) {
        mHeaders = headers;
    }
}
