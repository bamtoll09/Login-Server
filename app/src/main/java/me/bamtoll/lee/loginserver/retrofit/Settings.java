package me.bamtoll.lee.loginserver.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Settings {

    @SerializedName("isAuthorized")
    @Expose
    private boolean isAuthorized = false;

    public boolean isAuthorized() {
        return isAuthorized;
    }
}
