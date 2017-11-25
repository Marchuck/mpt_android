package com.example.notkink.mpt_android.upload;

import android.graphics.Bitmap;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BillPleaseApiClient {

    private final BillPleaseApi api;

    public BillPleaseApiClient(String endpoint) {

        api = new Retrofit.Builder()
                .baseUrl(endpoint)
                .addConverterFactory(GsonConverterFactory.create())
                .client(buildClient())
                .build()
                .create(BillPleaseApi.class);
    }

    private OkHttpClient buildClient() {
        int timeout = 10;

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(timeout, TimeUnit.SECONDS)
                .connectTimeout(timeout, TimeUnit.SECONDS);

        builder.addNetworkInterceptor(loggingInterceptor);

        return builder.build();
    }

    public Call<ResponseBody> uploadPhoto(Bitmap file) {

        System.out.println("uploadPhoto");

//        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
//
//        MultipartBody.Part body = MultipartBody.Part.createFormData("image",
//                file.getName(), requestFile);
//
//        RequestBody description = RequestBody.create(okhttp3.MultipartBody.FORM, "image");
//
//
        byte[] data = FileUtils.toBinary(file);

        RequestBody requestBody = RequestBody
                .create(MediaType.parse("application/octet-stream"), data);

        return api.uploadPhoto(requestBody);
    }


    public Call<ResponseBody> uploadFile(File image) {

        RequestBody requestBody = RequestBody
                .create(MediaType.parse("image/*"), image);

        return api.uploadPhoto(requestBody);

    }
}
