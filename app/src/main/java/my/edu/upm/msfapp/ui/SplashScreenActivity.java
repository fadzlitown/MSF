package my.edu.upm.msfapp.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import my.edu.upm.msfapp.R;
import my.edu.upm.msfapp.util.UserUtil;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!TextUtils.isEmpty(UserUtil.getToken(SplashScreenActivity.this))){
                    MainActivity.start(SplashScreenActivity.this);
                }else{
                    LoginActivity.start(SplashScreenActivity.this);
                }
//                if(TextUtils.isEmpty(SharedPreferenceUtil.getAccessToken())){
//                    LoginActivity.start(SplashScreenActivity.this);
//                } else {
//                    MainActivity.start(SplashScreenActivity.this);
//                }
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        }, 1000);
    }
}
