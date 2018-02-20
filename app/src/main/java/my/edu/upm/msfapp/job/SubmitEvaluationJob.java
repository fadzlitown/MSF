package my.edu.upm.msfapp.job;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;
import com.google.gson.Gson;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import my.edu.upm.msfapp.event.EvaluationEvent;
import my.edu.upm.msfapp.model.Submit;
import my.edu.upm.msfapp.util.UserUtil;

public class SubmitEvaluationJob extends BaseJob {
    private final int id;
    private static final AtomicInteger jobCounter = new AtomicInteger(0);
    private final long secId;
    private  ArrayList<Submit> arrayList;

    public SubmitEvaluationJob(ArrayList<Submit> arrayList, long secId) {
        super(new Params(Priority.HIGH).requireNetwork());
        id = jobCounter.incrementAndGet();
        this.arrayList=arrayList;
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
        String url = UrlConst.submitEvaluation(token);

        Response<String> response = Ion.with(context)
                .load("POST",url)
                .addHeader(UrlConst.HTTP_CONTENT_TYPE, "application/json")
                .setStringBody(new Gson().toJson(arrayList))
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
        JSONObject jsonObject = new JSONObject(json);
        if(jsonObject.has("Result"))
            EventBus.getDefault().postSticky(new EvaluationEvent.SubmitResponse(jsonObject.getBoolean("Result"),secId));
        else
            EventBus.getDefault().post(new EvaluationEvent.SubmitResponseFailed());
    }

    @Override
    protected void onCancel(int cancelReason, @Nullable Throwable throwable) {
        super.onCancel(cancelReason, throwable);

        EventBus.getDefault().postSticky(new EvaluationEvent.SubmitResponseFailed());
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