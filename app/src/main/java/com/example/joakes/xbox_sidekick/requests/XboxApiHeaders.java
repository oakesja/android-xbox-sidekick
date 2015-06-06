package com.example.joakes.xbox_sidekick.requests;

import java.util.HashMap;
import java.util.Map;

public class XboxApiHeaders {

    public static Map<String, String> getHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>(1);
        headers.put("X-AUTH", "59e87e6243cbe26381ddd07d4b51025be66265b9");
        return headers;
    }
}