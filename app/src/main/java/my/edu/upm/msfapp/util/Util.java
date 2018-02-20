package my.edu.upm.msfapp.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.threeten.bp.Instant;

/**
 * Created by mohdfadzli on 29/5/2017.
 */

public class Util {
    private static Gson gson;

    public static Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .registerTypeAdapter(Instant.class, new JSR310Converter())
                    .create();
        }

        return gson;
    }

//    public static Gson getGsonExcludeExpose() {
//        return new GsonBuilder()
//                .registerTypeAdapter(DateTime.class, new DateTimeConverter())
//                .excludeFieldsWithoutExposeAnnotation()
//                .create();
//    }

    public static boolean hasInternet(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

    public static int dpToPx(Context context, float dip) {
        return (int) (dip * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    public static void hideKeyboard(@Nullable View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void setListShown(View mListContainer, View mProgressContainer, boolean shown, boolean animate) {

        if ((mListContainer.getVisibility() == View.VISIBLE) == shown) {
            return;
        }
        Context context = mListContainer.getContext();
        if (shown) {
            if (animate) {
                mProgressContainer.startAnimation(AnimationUtils.loadAnimation(
                        context, android.R.anim.fade_out));
                mListContainer.startAnimation(AnimationUtils.loadAnimation(
                        context, android.R.anim.fade_in));
            } else {
                mProgressContainer.clearAnimation();
                mListContainer.clearAnimation();
            }
            mProgressContainer.setVisibility(View.GONE);
            mListContainer.setVisibility(View.VISIBLE);
        } else {
            if (animate) {
                mProgressContainer.startAnimation(AnimationUtils.loadAnimation(
                        context, android.R.anim.fade_in));
                mListContainer.startAnimation(AnimationUtils.loadAnimation(
                        context, android.R.anim.fade_out));
            } else {
                mProgressContainer.clearAnimation();
                mListContainer.clearAnimation();
            }
            mProgressContainer.setVisibility(View.VISIBLE);
            mListContainer.setVisibility(View.GONE);
        }

    }
}
