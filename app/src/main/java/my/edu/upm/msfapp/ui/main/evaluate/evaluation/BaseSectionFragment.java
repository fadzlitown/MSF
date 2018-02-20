package my.edu.upm.msfapp.ui.main.evaluate.evaluation;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.birbit.android.jobqueue.TagConstraint;

import org.greenrobot.eventbus.EventBus;

import java.util.UUID;

import my.edu.upm.msfapp.MyApplication;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseSectionFragment extends Fragment {

    private boolean isRegisteredOnStart=false;
    private String sessionId;

    public BaseSectionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionId = UUID.randomUUID().toString();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
            isRegisteredOnStart = true;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (isRegisteredOnStart && EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onDestroy() {
        // this MUST be async to avoid blocking the main thread
        MyApplication.getInstance().getJobManager().cancelJobsInBackground(null, TagConstraint.ANY, sessionId);
        super.onDestroy();
    }


}


