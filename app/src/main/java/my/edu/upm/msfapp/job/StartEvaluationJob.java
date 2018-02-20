package my.edu.upm.msfapp.job;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import org.greenrobot.eventbus.EventBus;

import my.edu.upm.msfapp.event.EvaluationEvent;
import my.edu.upm.msfapp.util.UserUtil;

public class StartEvaluationJob extends BaseJob {

    private final String groupId, evalId, username;

    public StartEvaluationJob(String groupId, String evalId, String username) {
        super(new Params(Priority.HIGH).requireNetwork());
        this.groupId=groupId;
        this.evalId=evalId;
        this.username=username;
    }

    @Override
    public void onAdded() {
        /* no-op */
    }

    @Override
    public void onRun() throws Throwable {

        Context context = getApplicationContext();
        String token = UserUtil.getToken(context);
        String url = UrlConst.startEvaluation(token,groupId, evalId, username);

        Response<String> response = Ion.with(context)
                .load("POST",url)
                .addHeader(UrlConst.HTTP_CONTENT_TYPE, "application/json")
                .setStringBody("")
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
        my.edu.upm.msfapp.model.Response r = my.edu.upm.msfapp.model.Response.fromJson(json);
        EventBus.getDefault().post(new EvaluationEvent.StartResponse(r));
    }

    @Override
    protected void onCancel(int cancelReason, @Nullable Throwable throwable) {
        super.onCancel(cancelReason, throwable);

        EventBus.getDefault().postSticky(new EvaluationEvent.StartResponseFailed());
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