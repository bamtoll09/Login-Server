package me.bamtoll.lee.loginserver.retrofit.interceptor;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ReceiveCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Sek-Cookie").isEmpty()) {

            HashSet<String> cookies = new HashSet<>(originalResponse.headers("Sek-Cookie"));

            // Put cookies to preference
            CookiePreference.putCookies(cookies);
        }

        return originalResponse;
    }
}
