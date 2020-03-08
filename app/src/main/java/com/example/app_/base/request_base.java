package com.example.app_.base;

import com.example.app_.entity.info;
import com.example.app_.entity.information;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface request_base {
    @POST("/api/v1/users/login/")
    @FormUrlEncoded
    @Headers("Content-Type: application/json")
    Call<information> accept_account(@Body RequestBody requestBody);

    @POST("/api/v1/users/register/")
    @Headers("Content-Type: application/json")
    Call<info> register(@Body RequestBody requestBody);

//    @POST("/api/v1/users/register/")
//    @Headers("Content-Type: application/json")
//    Call<ResponseBody>register(
//            @Field("username") String username ,
//            @Field("first_name") String first_name,
//            @Field("last_name") String last_name ,
//            @Field("email") String email ,
//            @Field("password") String password
//    );
}
