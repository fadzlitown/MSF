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

import my.edu.upm.msfapp.event.UserEvent;
import my.edu.upm.msfapp.model.Member;
import my.edu.upm.msfapp.model.User;
import my.edu.upm.msfapp.util.UserUtil;

public class GetMemberJob extends BaseJob {

    private final int skip, take;
    private final boolean isUpdate;
    private ArrayList<User> users;

    public GetMemberJob(boolean isUpdated, int skip, int take) {
        super(new Params(Priority.HIGH).requireNetwork());
        this.skip = skip;
        this.take = take;
        this.isUpdate=isUpdated;
    }

    @Override
    public void onAdded() {
        /* no-op */
    }

    @Override
    public void onRun() throws Throwable {

        Context context = getApplicationContext();
        String token = UserUtil.getToken(context);
        String url = UrlConst.getMemberList(token, take, skip);

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
        Member member = Member.fromJson(json);
        if(isUpdate) EventBus.getDefault().postSticky(new UserEvent.MemberUpdateList(member));
        else EventBus.getDefault().post(new UserEvent.MemberList(member));
    }

    @Override
    protected void onCancel(int cancelReason, @Nullable Throwable throwable) {
        super.onCancel(cancelReason, throwable);

        if(isUpdate) EventBus.getDefault().postSticky(new UserEvent.MemberUpdateListFailed());
        else EventBus.getDefault().postSticky(new UserEvent.MemberListFailed());
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