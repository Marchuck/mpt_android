package com.example.notkink.mpt_android.upload;

import android.graphics.Bitmap;

import com.example.notkink.mpt_android.App;
import com.example.notkink.mpt_android.toast.Toaster;

import java.io.File;
import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageUploader {

    private final Toaster toaster = new Toaster();

    public void uploadFile(File image) {
        if (image == null) {
            toaster.showToast("Please select photo first!");
            return;
        }
        System.out.println("upload file: " + image.getAbsolutePath());

        BillPleaseApiClient client = App.getApp().getBillPleaseApiClient();
        client.uploadFile(image).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                System.out.println("onResponse");
                UploadResponse uploadResponse = parseResponse(response);
                if (uploadResponse.isSuccessful()) {
                    toaster.showToast("Upload successful");
                } else {
                    toaster.showToast("Error: " + uploadResponse.error);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable error) {
                System.err.println("onFailure: " + error.getMessage());
                error.printStackTrace();
                toaster.showToast(String.valueOf(error));
            }
        });
    }

    public void uploadImage(Bitmap image) {

        if (image == null) {
            toaster.showToast("Please select photo first!");
            return;
        }

        BillPleaseApiClient client = App.getApp().getBillPleaseApiClient();
        client.uploadPhoto(image).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                System.out.println("onResponse");
                UploadResponse uploadResponse = parseResponse(response);
                if (uploadResponse.isSuccessful()) {
                    toaster.showToast("Upload successful");
                } else {
                    toaster.showToast("Error: " + uploadResponse.error);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable error) {
                System.err.println("onFailure: " + error.getMessage());
                error.printStackTrace();
                toaster.showToast(String.valueOf(error));
            }
        });
    }

    private UploadResponse parseResponse(Response<ResponseBody> response) {
        ResponseBody body = response.body();

        if (body == null) {
            body = response.errorBody();
        }
        try {
            String string = body.string();
            UploadResponse uploadResponse = new UploadResponse(null, string);
            return uploadResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new UploadResponse();
    }
}
