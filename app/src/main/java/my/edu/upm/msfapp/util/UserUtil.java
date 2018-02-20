package my.edu.upm.msfapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import my.edu.upm.msfapp.model.User;

public class UserUtil {

    private static final String USER_DATA = "user";
    private static final String TOKEN = "token";
    private static final String USER_ID = "user_id";
    private static final String USER_GROUP_ID = "user_group_id";
    private static final String BASIC_PROFILE = "profile";
    private static final String USER_GROUP_NAME = "user_group_name";


    private static User mUser;

    public static User getInstance(Context context) {
        if (mUser == null) {
            try {
                mUser = getUser(context);
            } catch (Exception e) {
                e.printStackTrace();
                resetUser(context);
            }
        }

        return mUser;
    }

    public static void setUser(Context context, User user) {
        storeUser(context, user);
        mUser = user;
    }

    public static boolean setToken(Context context, String token) {
        return getSecurePreference(context)
                .edit()
                .putString(TOKEN, token)
                .commit();
    }

    @NonNull
    public static String getToken(Context context) {
        return getSecurePreference(context)
                .getString(TOKEN, "");
    }

    public static boolean isLogin(Context context) {
        return getInstance(context) != null && !TextUtils.isEmpty(getToken(context));
    }

    private static void storeUser(Context context, User user) {
        getSecurePreference(context)
                .edit()
                .putString(USER_DATA, user.toJson())
                .apply();
    }

    public static void resetUser(Context context) {
        mUser = null;
        getSecurePreference(context)
                .edit()
                .remove(USER_DATA)
                .remove(TOKEN)
                .remove(USER_ID)
                .apply();
    }

    @Nullable
    private static User getUser(Context context) {
        String json = getSecurePreference(context)
                .getString(USER_DATA, null);

        if (!TextUtils.isEmpty(json)) {
            return User.fromJson(json);
        }

        return null;
    }

    public static void setUserId(Context context, String userId) {
        getSecurePreference(context)
                .edit()
                .putString(USER_ID, userId)
                .apply();
    }

    public static String getUserId(Context context) {
        return getSecurePreference(context)
                .getString(USER_ID, null);
    }

    public static void setGroupId(Context context, String userId) {
        getSecurePreference(context)
                .edit()
                .putString(USER_GROUP_ID, userId)
                .apply();
    }

    public static String getGroupId(Context context) {
        return getSecurePreference(context)
                .getString(USER_GROUP_ID, null);
    }

    public static void setGroupName(Context context, String userId) {
        getSecurePreference(context)
                .edit()
                .putString(USER_GROUP_NAME, userId)
                .apply();
    }

    public static String getGroupName(Context context) {
        return getSecurePreference(context)
                .getString(USER_GROUP_NAME, null);
    }

    //    private static boolean useSecurePref = true;

    private static SharedPreferences getSecurePreference(Context context) {
//        if (useSecurePref) {
//            try {
//                return new SecurePreferences(context);
//            } catch (IllegalStateException e) {
//                e.printStackTrace();
//                Crashlytics.logException(e);
//                useSecurePref = false;
//            }
//        }
        return context.getSharedPreferences("my_prefs.xml", Context.MODE_PRIVATE);
    }

}
