package my.edu.upm.msfapp.job;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import my.edu.upm.msfapp.event.StudentEvent;
import my.edu.upm.msfapp.model.Question;
import my.edu.upm.msfapp.util.UserUtil;

public class GetQuestionsJob extends BaseJob {

    private final int  skip, take;
    private final long sectionId;
    private ArrayList<Question> list;

    public GetQuestionsJob(int skip, int take, long id) {
        super(new Params(Priority.HIGH).requireNetwork());
        this.skip = skip;
        this.take = take;
        this.sectionId=id;
    }

    @Override
    public void onAdded() {
        /* no-op */
    }

    @Override
    public void onRun() throws Throwable {
        Context context = getApplicationContext();
        String token = UserUtil.getToken(context);
        String url = UrlConst.getQuestionList(token,sectionId, take, skip);

        Response<String> response = Ion.with(context)
                .load(url)
                .addHeader(UrlConst.HTTP_CONTENT_TYPE, "application/json")
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
        list = Question.toList(json);
        EventBus.getDefault().post(new StudentEvent.QuestionList(list,sectionId));
    }

    @Override
    protected void onCancel(int cancelReason, @Nullable Throwable throwable) {
        super.onCancel(cancelReason, throwable);
        EventBus.getDefault().post(new StudentEvent.QuestionListFailed());
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