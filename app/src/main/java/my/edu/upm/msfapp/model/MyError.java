package my.edu.upm.msfapp.model;

import com.google.gson.annotations.SerializedName;

import my.edu.upm.msfapp.util.Util;

public class MyError {
    @SerializedName("ErrorCode")
    private int ErrorCode;
    @SerializedName("ErrorCodeDescription")
    private String ErrorCodeDescription;
    @SerializedName("ErrorMessage")
    private String ErrorMessage;

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public int getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(int errorCode) {
        ErrorCode = errorCode;
    }

    public String getErrorCodeDescription() {
        return ErrorCodeDescription;
    }

    public void setErrorCodeDescription(String errorCodeDescription) {
        ErrorCodeDescription = errorCodeDescription;
    }

    public static MyError fromJson(String json) {
        return Util.getGson().fromJson(json, MyError.class);
    }
}