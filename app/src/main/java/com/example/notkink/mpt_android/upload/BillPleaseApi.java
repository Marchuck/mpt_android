package com.example.notkink.mpt_android.upload;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface BillPleaseApi {

    @Multipart
    @POST("image/")
    Call<ResponseBody> uploadImage(@Part("image") RequestBody description,
                                   @Part MultipartBody.Part body);

    @POST("image/")
    Call<ResponseBody> uploadPhoto(@Body RequestBody photo);

}
