package my.edu.upm.msfapp.ui.main.userListing;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import my.edu.upm.msfapp.MyApplication;
import my.edu.upm.msfapp.R;
import my.edu.upm.msfapp.event.StudentEvent;
import my.edu.upm.msfapp.event.UserEvent;
import my.edu.upm.msfapp.job.GetMemberJob;
import my.edu.upm.msfapp.model.Member;
import my.edu.upm.msfapp.model.User;
import my.edu.upm.msfapp.ui.main.userListing.adapter.UserListingAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserListingFragment extends Fragment {

    private static final String MEMBER_STR = "member";
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;
    @BindView(R.id.no_data)
    FrameLayout noData;
    private UserListingAdapter mAdapter;
    private Unbinder unbinder;
    private ArrayList<User> memberList;

    public static Fragment newInstance(Member member) {
        // Required empty public constructor
        Bundle bundle = new Bundle();
        bundle.putString(MEMBER_STR, member.toJson());
        UserListingFragment fragment = new UserListingFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_listing, container, false);
        unbinder = ButterKnife.bind(this, view);
        setToolbar();
        if(mAdapter==null)
            init();
        else {
            if(mAdapter.getCount()>0)
                mAdapter.clear();
        }

        if (getArguments().containsKey(MEMBER_STR)) {
            Member m = Member.fromJson(getArguments().getString(MEMBER_STR));
            if (!TextUtils.isEmpty(m.getGroup().getName()))
                title.setText(m.getGroup().getName());
            swipeContainer.setRefreshing(true);
            onAddMember(m);
            getArguments().remove(MEMBER_STR);
        }
        return view;
    }

    private void init() {
        ArrayList<User> userArrays = new ArrayList<>();
        mAdapter = new UserListingAdapter(getActivity(), userArrays);
        lv.setAdapter(mAdapter);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                callMemberJob();
            }
        });
    }

    private void callMemberJob() {
        mAdapter.clear();
        mAdapter.notifyDataSetChanged();
        swipeContainer.setRefreshing(true);
        MyApplication.addJobInBackground(new GetMemberJob(true, 0, 20));
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onHandle(UserEvent.MemberUpdateList event) {
        if (event.getMemberList() != null) {
            if(mAdapter.getCount()>0) mAdapter.clear();
            onAddMember(event.getMemberList()); //always get updated list
        }
        EventBus.getDefault().removeStickyEvent(event);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onHandle(StudentEvent.OnUpdateEvaluation event) {
        callMemberJob();
        EventBus.getDefault().removeStickyEvent(event);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHandle(UserEvent.MemberUpdateListFailed event) {
        swipeContainer.setRefreshing(false);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }


    private void setToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolBar);
    }

    public void onAddMember(Member m) {
        swipeContainer.setRefreshing(false);
        memberList = new ArrayList<>();
        memberList = m.getMember();
        if (m != null) {
            if (!TextUtils.isEmpty(m.getGroup().getName())) {
                title.setText(m.getGroup().getName());
            }
            if (m.getMember().size() > 0) {
                noData.setVisibility(View.GONE);
                mAdapter.addAll(memberList);
                mAdapter.notifyDataSetChanged();
            } else {
                noData.setVisibility(View.VISIBLE);
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
