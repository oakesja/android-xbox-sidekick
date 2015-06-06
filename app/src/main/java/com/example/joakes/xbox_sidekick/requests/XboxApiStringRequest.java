package com.example.joakes.xbox_sidekick.requests;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by joakes on 4/28/15.
 */
public class XboxApiStringRequest extends StringRequest {

    public XboxApiStringRequest(String url,
                                Response.Listener<String> listener,
                                Response.ErrorListener errorListener) {
        super(Method.GET, url, listener, errorListener);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return XboxApiHeaders.getHeaders();
    }
}
