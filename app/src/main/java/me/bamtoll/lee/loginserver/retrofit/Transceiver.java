package me.bamtoll.lee.loginserver.retrofit;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import me.bamtoll.lee.loginserver.DATA;
import me.bamtoll.lee.loginserver.retrofit.interceptor.AddCookiesInterceptor;
import me.bamtoll.lee.loginserver.retrofit.interceptor.CookiePreference;
import me.bamtoll.lee.loginserver.retrofit.interceptor.ReceiveCookiesInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Transceiver {
    private static Transceiver instance = null;

    private OkHttpClient client;
    public Retrofit retrofit;
    private Gson gson;

    private Transceiver() {
    };

    public static void init(Context context) {
        CookiePreference.create(context.getApplicationContext());
    }

    public static Transceiver getInstance() {
        if (instance == null) {
            instance = new Transceiver();
            instance.client = new OkHttpClient.Builder()
                    .addInterceptor(new AddCookiesInterceptor())
                    .addInterceptor(new ReceiveCookiesInterceptor())
                    .build();
            instance.gson = new GsonBuilder().setLenient().create();
            instance.retrofit = new Retrofit.Builder()
                    .baseUrl("http://" + DATA.URL + "/")
                    .addConverterFactory(GsonConverterFactory.create(instance.gson))
                    .client(instance.client)
                    .build();
        }
        return instance;
    }
}
