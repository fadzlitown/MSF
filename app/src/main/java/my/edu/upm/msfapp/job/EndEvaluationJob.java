package my.edu.upm.msfapp.job;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.util.concurrent.atomic.AtomicInteger;

import my.edu.upm.msfapp.event.EvaluationEvent;
import my.edu.upm.msfapp.util.UserUtil;

public class EndEvaluationJob extends BaseJob {

    private static final AtomicInteger jobCounter = new AtomicInteger(0);
    private final int id;
    private final long secId;
    private int  responseId;

    public EndEvaluationJob(int responseId, long secId) {
        super(new Params(Priority.HIGH).requireNetwork());
        id = jobCounter.incrementAndGet();
        this.responseId=responseId;
        this.secId=secId;

    }

    @Override
    public void onAdded() {
        /* no-op */
    }

    @Override
    public void onRun() throws Throwable {
        if (id != jobCounter.get())
            return;

        Context context = getApplicationContext();
        String token = UserUtil.getToken(context);
        String url = UrlConst.endEvaluation(token,responseId);

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
//        my.edu.upm.msfapp.model.Response r = my.edu.upm.msfapp.model.Response.fromJson(json);
        JSONObject jsonObject = new JSONObject(json);
        if(jsonObject.has("Result"))
            EventBus.getDefault().post(new EvaluationEvent.EndResponse(jsonObject.getBoolean("Result"),secId));
        else
            EventBus.getDefault().post(new EvaluationEvent.EndResponseFailed());
    }

    @Override
    protected void onCancel(int cancelReason, @Nullable Throwable throwable) {
        super.onCancel(cancelReason, throwable);

        EventBus.getDefault().post(new EvaluationEvent.EndResponseFailed());
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