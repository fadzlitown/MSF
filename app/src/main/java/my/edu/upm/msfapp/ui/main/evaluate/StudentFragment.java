package my.edu.upm.msfapp.ui.main.evaluate;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import my.edu.upm.msfapp.R;
import my.edu.upm.msfapp.event.UserEvent;
import my.edu.upm.msfapp.model.Member;
import my.edu.upm.msfapp.model.User;
import my.edu.upm.msfapp.ui.main.evaluate.evaluation.EvaluationActivity;
import my.edu.upm.msfapp.util.Constant;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentFragment extends Fragment {

    private static final String MEMBER_STR = "member";
    @BindView(R.id.studentSpinner)
    Spinner studentSpinner;
    @BindView(R.id.start_btn)
    Button startBtn;
    Unbinder unbinder;
    @BindView(R.id.no_data_img)
    ImageView noDataImg;
    @BindView(R.id.no_data_title)
    TextView noDataTitle;
    @BindView(R.id.no_data)
    FrameLayout noData;
    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.layout_selection)
    LinearLayout layoutSelection;

    private ArrayAdapter<String> adapter;
    private ArrayList<User> userList;
    private long evalId = 0;
    private String evaluateeUsername;


    public StudentFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance(Member member) {
        // Required empty public constructor
        Bundle bundle = new Bundle();
        bundle.putString(MEMBER_STR, member.toJson());
        StudentFragment fragment = new StudentFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_evaluate, container, false);
        unbinder = ButterKnife.bind(this, view);
        userList = new ArrayList<>();
        if (getArguments().containsKey(MEMBER_STR)) {
            Member m = Member.fromJson(getArguments().getString(MEMBER_STR));
            evalId = m.getGroup().getEvaluation().getEvaluationId();
            onHandle(m);
        }
        studentSpinner.setOnItemSelectedListener(new OnItemSelectedListener());
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onHandle(UserEvent.MemberUpdateList event) {
        userList = new ArrayList<>();
        if (event.getMemberList() != null) {
            onHandle(event.getMemberList());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHandle(UserEvent.MemberUpdateListFailed event) {

    }

    public void onHandle(Member m) {
        for (int i = 0; i < m.getMember().size(); i++) {
            //add unevaluated student only included student themselves (applied for student role only)
            if (!m.getMember().get(i).isEvaluated() && m.getMember().get(i).getRole() == Constant.RoleType.STUDENT)
                userList.add(m.getMember().get(i));
        }

        if (userList.size() > 0) {
            layoutSelection.setVisibility(View.VISIBLE);
            noData.setVisibility(View.GONE);

            String[] str = new String[userList.size()];
            for (int i = 0; i < userList.size(); i++) {
                str[i] = " " + userList.get(i).getFullName();
            }
            adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, str);
            studentSpinner.setAdapter(adapter);
        } else {
            layoutSelection.setVisibility(View.GONE);
            noData.setVisibility(View.VISIBLE);
            //if has a member but no unevaluated member then
            if (m.getMember().size() > 0) {
                noDataTitle.setText("Good job! all students has been evaluated");
                noDataImg.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.smile_a));
            } else {
                noDataTitle.setText("No member is added");
                noDataImg.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.smile_e));
            }
        }
    }


    @OnClick(R.id.start_btn)
    public void onEvaluate(View view) {
        if (!TextUtils.isEmpty(evaluateeUsername)) {
            EvaluationActivity.start(getActivity(), evalId, evaluateeUsername);
        } else {
            Toast.makeText(getActivity(), "Please select username", Toast.LENGTH_SHORT).show();
        }
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private class OnItemSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (userList != null && userList.size() > 0) {
                evaluateeUsername = userList.get(position).getUserName();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

}
