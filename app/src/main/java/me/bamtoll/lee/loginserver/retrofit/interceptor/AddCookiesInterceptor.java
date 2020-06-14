package me.bamtoll.lee.loginserver.retrofit.interceptor;

import android.content.Context;

import java.io.IOException;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

        // Get cookies on preference
        Set<String> cookies = CookiePreference.getCookies();

        for (String cookie: cookies) {
            builder.addHeader("Cookie", cookie);
        }

        // Set User-Agent for separating Web, Android, iOS
        builder.removeHeader("User-Agent").addHeader("User-Agent", "Android");

        return chain.proceed(builder.build());
    }
}
