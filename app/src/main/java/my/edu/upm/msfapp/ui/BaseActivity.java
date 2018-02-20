package my.edu.upm.msfapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;
import android.widget.Toast;

import com.birbit.android.jobqueue.TagConstraint;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Locale;
import java.util.UUID;

import my.edu.upm.msfapp.MyApplication;
import my.edu.upm.msfapp.R;
import my.edu.upm.msfapp.model.ExceptionEvent;
import my.edu.upm.msfapp.model.MyException;

/**
 * Created by mohdfadzli on 1/6/2017.
 */

public class BaseActivity extends AppCompatActivity {

    private boolean isRegisteredOnStart=false;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private String sessionId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionId = UUID.randomUUID().toString();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
            isRegisteredOnStart = true;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isRegisteredOnStart && EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ExceptionEvent event) {
        Exception exception = event.getException();
        if (exception != null) {
            if (exception instanceof MyException) {
                int errorCode = ((MyException) exception).getError().getErrorCode();
                switch (errorCode) {
                    case 5:
                        if (((MyException) exception).getError().getErrorMessage().toLowerCase(Locale.ENGLISH).contains("token")) {

//                            UserUtil.resetUser(this);
//                            PrefUtil.reset(this);
//                            LoginActivity.makeRestartActivityTask(this);
                            return;
                        }
                }
            }
            String msg = exception.getMessage();
            if (TextUtils.isEmpty(msg)) {
                msg = getString(R.string.unexpected_error);
            }
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        // this MUST be async to avoid blocking the main thread
        MyApplication.getInstance().getJobManager().cancelJobsInBackground(null, TagConstraint.ANY, sessionId);
        super.onDestroy();
    }

}
