package my.edu.upm.msfapp.job;

import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.Params;
import com.koushikdutta.ion.Response;

import org.greenrobot.eventbus.EventBus;

import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import my.edu.upm.msfapp.MyApplication;
import my.edu.upm.msfapp.R;
import my.edu.upm.msfapp.model.ExceptionEvent;
import my.edu.upm.msfapp.model.MyError;
import my.edu.upm.msfapp.model.MyException;

abstract class BaseJob extends Job {

    public BaseJob(Params params) {
        super(params);
    }

    boolean shouldRetry(Throwable throwable) {
        return throwable instanceof UnknownHostException
                || throwable instanceof TimeoutException
                || (throwable instanceof MyException && ((MyException) throwable).getError().getErrorCode() == Integer.MIN_VALUE);
    }

    protected static void checkServerResponse(Response<String> response) throws MyException {
        int code = response.getHeaders().code();
        if (code < 400) {
            // all ok
        } else if (code < 500) {
            // client error
            MyException ex = MyException.parse(response.getResult());
            throw ex;
        } else {
            // server error
            MyException ex = null;
            try {
                ex = MyException.parse(response.getResult());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (ex == null || TextUtils.isEmpty(ex.getMessage())) {
                MyError error = new MyError();
                error.setErrorCode(Integer.MIN_VALUE);
                error.setErrorMessage(MyApplication.getInstance().getString(R.string.error_500));
                ex = new MyException(error);
            }
            throw ex;
        }
    }

    @CallSuper
    @Override
    protected void onCancel(int cancelReason, @Nullable Throwable throwable) {
        EventBus.getDefault().post(new ExceptionEvent(throwable));
    }

}