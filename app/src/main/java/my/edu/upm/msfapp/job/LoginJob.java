package my.edu.upm.msfapp.job;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import org.greenrobot.eventbus.EventBus;

import my.edu.upm.msfapp.event.LoginEvent;
import my.edu.upm.msfapp.model.Device;
import my.edu.upm.msfapp.model.Login;
import my.edu.upm.msfapp.model.MyException;
import my.edu.upm.msfapp.model.User;
import my.edu.upm.msfapp.util.UserUtil;

public class LoginJob extends BaseJob {

    private Login login;

    public LoginJob(Login login) {
        super(new Params(Priority.HIGH).requireNetwork());
        this.login = login;
    }

    @Override
    public void onAdded() {
        /* no-op */
    }

    @Override
    public void onRun() throws Throwable {

        Context context = getApplicationContext();
        String url = UrlConst.login();
        login.setDevice(new Device(context));

        Response<String> response = Ion.with(context)
                .load(url)
                .addHeader(UrlConst.HTTP_CONTENT_TYPE, "application/json")
                .setStringBody(login.toJson())
                .asString()
                .withResponse()
                .get();

        if (isCancelled()) {
            // this job has been cancelled, just treat it as completed successfully
            // or else just throw an exception to treat as cancelled job
            return;
        }

        String json = response.getResult();
        checkServerResponse(response);

        User user = User.fromJson(json);
        UserUtil.setUser(context, user);
        UserUtil.setToken(context, user.getAccessToken());
        UserUtil.setUserId(context, String.valueOf(user.getUserId()));
        EventBus.getDefault().post(new LoginEvent.OnLogin(user));
    }

    @Override
    protected void onCancel(int cancelReason, @Nullable Throwable throwable) {
        super.onCancel(cancelReason, throwable);
        if (throwable instanceof MyException) {
            if (((MyException) throwable).getError().getErrorCode() == 1005) {
//                EventBus.getDefault().postSticky(new AccountEvent.OnUnverifiedUser());
            }
        }
        Bundle bundle = new Bundle(2);
        if (throwable != null) bundle.putString("error", throwable.getMessage());

        EventBus.getDefault().post(new LoginEvent.OnFail());
    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(@NonNull Throwable throwable, int runCount, int maxRunCount) {
        if (shouldRetry(throwable)) {
            return RetryConstraint.createExponentialBackoff(runCount, 1000);
        }
        return RetryConstraint.CANCEL;
    }

    @Override
    protected int getRetryLimit() {
        return 2;
    }
}