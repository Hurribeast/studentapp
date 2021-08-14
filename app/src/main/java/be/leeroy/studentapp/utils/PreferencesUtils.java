package be.leeroy.studentapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesUtils {

    public static String getToken(Activity activity) {
        SharedPreferences preferences = activity.getSharedPreferences("login", Context.MODE_PRIVATE);
        return preferences != null ? preferences.getString("token", null) : null;
    }

    public static void setToken(String token, Activity activity) {
        SharedPreferences preferences = activity.getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("token", token);

        editor.apply();
    }
}
