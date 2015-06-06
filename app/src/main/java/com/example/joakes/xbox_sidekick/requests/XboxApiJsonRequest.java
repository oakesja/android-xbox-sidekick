package com.example.joakes.xbox_sidekick.requests;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by joakes on 4/28/15.
 */
public class XboxApiJsonRequest extends JsonObjectRequest {

    public XboxApiJsonRequest(String url,
                              Response.Listener<JSONObject> listener,
                              Response.ErrorListener errorListener) {
        super(Method.GET, url, listener, errorListener);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return XboxApiHeaders.getHeaders();
    }
}
