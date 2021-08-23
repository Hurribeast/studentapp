package be.leeroy.studentapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesUtils {

    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    public static String get(String key, Activity activity) {
        preferences = activity.getSharedPreferences("student", Context.MODE_PRIVATE);
        return preferences != null ? preferences.getString(key, null) : null;
    }

    public static void set(String key, String value, Activity activity) {
        preferences = activity.getSharedPreferences("student", Context.MODE_PRIVATE);

        editor = preferences.edit();
        editor.putString(key, value);

        editor.apply();
    }

    public static void remove(String key, Activity activity) {
        preferences = activity.getSharedPreferences("student", Context.MODE_PRIVATE);

        editor = preferences.edit();
        editor.remove(key);

        editor.apply();
    }
}
