package me.bamtoll.lee.loginserver.retrofit.interceptor;

import android.util.Log;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ReceiveCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {

            HashSet<String> cookies = new HashSet<>(originalResponse.headers("Set-Cookie"));
            Log.d("COOC-2", cookies.toString());

            // Put cookies to preference
            CookiePreference.putCookies(cookies);
        }

        return originalResponse;
    }
}
