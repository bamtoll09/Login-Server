package me.bamtoll.lee.loginserver.retrofit.interceptor;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class CookiePreference {

    private final static String PREFERENCE_KEY = "PREFERENCE";
    private final static String STRING_SET_KEY = "COOKIE";
    private static SharedPreferences preferences;

    public static void create(Context context) {
        preferences = context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE);
    }

    public static Set<String> getCookies() {
        return preferences.getStringSet(STRING_SET_KEY, new HashSet<String>());
    }

    public static void putCookies(Set<String> strings) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet(STRING_SET_KEY, strings);
        editor.apply();
    }

    public static void clear() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }
}
