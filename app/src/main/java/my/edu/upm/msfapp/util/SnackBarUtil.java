package my.edu.upm.msfapp.util;

import android.content.Context;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import my.edu.upm.msfapp.R;

public class SnackBarUtil {
    public static void displaySnackbarActivity(View view, Context context, String s) {
        Snackbar snack = Snackbar.make(view.findViewById(android.R.id.content), s, Snackbar.LENGTH_LONG);
        View subView = snack.getView();
        subView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        TextView textView = (TextView) subView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            textView.setCompoundDrawablesRelative(ContextCompat.getDrawable(context, android.R.drawable.ic_dialog_alert), null, null, null);
        }
        textView.setCompoundDrawablePadding(10);
        snack.show();
    }
}