package my.edu.upm.msfapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import my.edu.upm.msfapp.util.Util;

/**
 * Created by mohdfadzli on 29/5/2017.
 *     "Device":
 {
 "DeviceType":"1",
 "DeviceID":"1234",
 "RegistrationID":"123"
 },
 "UserName":"admin@admin.com",
 "Password":"12345678"
 */

public class Login {
    @SerializedName("Device")
    @Expose
    private Device Device;
    @SerializedName("UserName")
    @Expose
    private String UserName;
    @SerializedName("Password")
    @Expose
    private String Password;

    public Login(String username, String pass) {
        this.UserName=username;
        this.Password=pass;
    }

    public void setDevice(Device device) {
        Device = device;
    }

    public String toJson() {
        return Util.getGson().toJson(this);
    }

}
