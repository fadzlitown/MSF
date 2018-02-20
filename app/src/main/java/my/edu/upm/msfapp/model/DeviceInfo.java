package my.edu.upm.msfapp.model;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;

public class DeviceInfo {
    private static String sID = null;
    private static final String INSTALLATION = "INSTALLATION";

    public synchronized static String id(Context context) {
        if (sID == null) {
            File installation = new File(context.getFilesDir(), INSTALLATION);
            try {
                if (!installation.exists()) writeInstallationFile(context, installation);
                sID = readInstallationFile(installation);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        Log.d("Installation", "ID = " + sID);
        return sID;
    }

    private static String readInstallationFile(File installation) throws IOException {
        RandomAccessFile f = null;
        String s;
        try {
            f = new RandomAccessFile(installation, "r");
            byte[] bytes = new byte[(int) f.length()];
            f.readFully(bytes);
            s = new String(bytes);

        } finally {
            if (f != null) {
                try {
                    f.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return s;
    }

    private static void writeInstallationFile(Context context, File installation) throws IOException {
        FileOutputStream out = new FileOutputStream(installation);

        // INFO:
        // http://stackoverflow.com/questions/2785485/is-there-a-unique-android-device-id
        // http://android-developers.blogspot.com/2011/03/identifying-app-installations.html

        // Prefer ANDROID_ID
        String id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        Log.d("Installation", "Write ID_AND = " + id);

        if (TextUtils.isEmpty(id) || "9774d56d682e549c".equals(id)) {
            // try IMEI
            // note: require
            // <uses-permission android:name="android.permission.READ_PHONE_STATE" android:required="false" />
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_WIFI_STATE) == PackageManager.PERMISSION_GRANTED) {
                try {
                    id = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    id = "";
                }
                Log.d("Installation", "Write ID_IM = " + id);
            }
        }

        /**
         * https://developer.android.com/about/versions/marshmallow/android-6.0-changes.html#behavior-hardware-id
         *
         * To provide users with greater data protection, starting in this release,
         * Android removes programmatic access to the device’s local hardware identifier
         * for apps using the Wi-Fi and Bluetooth APIs.
         * The WifiInfo.getMacAddress() and the BluetoothAdapter.getAddress() methods
         * now return a constant value of 02:00:00:00:00:00.
         *
         * To access the hardware identifiers of nearby external devices
         * via Bluetooth and Wi-Fi scans, your app must now have the
         * ACCESS_FINE_LOCATION or ACCESS_COARSE_LOCATION permissions.
         */
        if (TextUtils.isEmpty(id) && ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_WIFI_STATE) == PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                // try MAC address
                try {
                    WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                    id = wm.getConnectionInfo().getMacAddress();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                Log.d("Installation", "Write ID_MA = " + id);
            } else if ((ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
                // try MAC address
                try {
                    WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                    id = wm.getConnectionInfo().getMacAddress();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                Log.d("Installation", "Write ID_MA = " + id);
            }
        }

        if (TextUtils.isEmpty(id)) {
            // use UUID only as last option as reinstallation app in same device
            // will cause it differ
            id = UUID.randomUUID().toString();
            Log.d("Installation", "Write ID_UU = " + id);
        }

        out.write(id.getBytes());
        out.close();
    }
}
