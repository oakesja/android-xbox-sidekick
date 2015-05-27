package com.example.joakes.xbox_sidekick;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by joakes on 4/28/15.
 */
public class XboxApiRequest extends JsonObjectRequest {
    public XboxApiRequest(String url,
                          Response.Listener<JSONObject> listener,
                          Response.ErrorListener errorListener) {
        super(Method.GET, url, listener, errorListener);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<>(1);
        headers.put("X-AUTH", "59e87e6243cbe26381ddd07d4b51025be66265b9");
        return headers;
    }
}
