package me.bamtoll.lee.loginserver.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PostService {
    @GET("/posts/all")
    Call<List<Post>> getAll();

    @POST("/posts/write")
    @FormUrlEncoded
    Call<Void> write(@Field("title") String title, @Field("contents") String contents, @Field("type") int type);
}
