package me.bamtoll.lee.loginserver.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SettingsService {
    @GET("/users/settings")
    Call<Settings> getSettings();
}
