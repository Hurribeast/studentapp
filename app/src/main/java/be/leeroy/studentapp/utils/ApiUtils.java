package be.leeroy.studentapp.utils;

import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.RequestBody;

public class ApiUtils {

    public static RequestBody ToRequestBody(HashMap<String, Object> bodyParams) {
        return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(bodyParams)).toString());
    }
}
