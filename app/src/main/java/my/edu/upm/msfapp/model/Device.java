package my.edu.upm.msfapp.model;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import my.edu.upm.msfapp.BuildConfig;
import my.edu.upm.msfapp.fcm.RegistrationIntentService;
import my.edu.upm.msfapp.util.Util;

public class Device {
    @SerializedName("DeviceType")
    private final int DeviceType;
    @SerializedName("DeviceId")
    private final String DeviceId;
    @SerializedName("RegistrationId")
    private final String RegistrationId;
    @SerializedName("GuiVersion")
    private final String GuiVersion;

    public Device(Context context) {
        DeviceId = DeviceInfo.id(context);
        GuiVersion = BuildConfig.VERSION_NAME;
        DeviceType = 2; // 2 = Android
        RegistrationId = RegistrationIntentService.getRegistrationId();
    }

    @Override
    public String toString() {
        return Util.getGson().toJson(this);
    }
}