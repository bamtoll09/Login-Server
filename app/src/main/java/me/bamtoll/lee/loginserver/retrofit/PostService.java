package me.bamtoll.lee.loginserver.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostService {
    @GET("/posts/all")
    Call<List<Post>> getAll();
}
