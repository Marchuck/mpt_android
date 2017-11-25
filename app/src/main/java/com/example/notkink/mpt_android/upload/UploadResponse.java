package com.example.notkink.mpt_android.upload;

import com.google.gson.annotations.SerializedName;

public class UploadResponse {

    @SerializedName("error")
    public String error;

    @SerializedName("url")
    public String url;

    public UploadResponse() {
    }

    public UploadResponse(String url, String error) {
        this.url = url;
        this.error = error;
    }

    public boolean isSuccessful() {
        return url != null;
    }
}
