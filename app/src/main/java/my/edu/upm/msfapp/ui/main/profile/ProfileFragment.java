package my.edu.upm.msfapp.ui.main.profile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import my.edu.upm.msfapp.MyApplication;
import my.edu.upm.msfapp.R;
import my.edu.upm.msfapp.event.LoginEvent;
import my.edu.upm.msfapp.job.LogoutJob;
import my.edu.upm.msfapp.model.User;
import my.edu.upm.msfapp.ui.LoginActivity;
import my.edu.upm.msfapp.util.Constant;
import my.edu.upm.msfapp.util.UserUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    @BindView(R.id.welcomeName)
    TextView welcomeName;
    @BindView(R.id.matricNo)
    TextView matricNo;
    @BindView(R.id.roleType)
    TextView roleType;
    @BindView(R.id.groupType)
    TextView groupType;
    @BindView(R.id.phoneNo)
    TextView phoneNo;
    Unbinder unbinder;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        unbinder = ButterKnife.bind(this, view);
        if(UserUtil.getInstance(getActivity())!=null){
            User user = UserUtil.getInstance(getActivity());

            welcomeName.setText("Hi "+user.getFullName()+", I hope you're doing well.");
            matricNo.setText(user.getUserName());
            phoneNo.setText(user.getContactNumber());
            groupType.setText(UserUtil.getGroupName(getContext()));

            switch (user.getRole()){
                case Constant.RoleType.STUDENT:
                    roleType.setText("Student");
                    break;

                case Constant.RoleType.LECTURER:
                    roleType.setText("Lecturer");
                    break;

                case Constant.RoleType.STAFF:
                    roleType.setText("Staff");
                    break;

                case Constant.RoleType.PATIENT:
                    roleType.setText("Patient");
                    break;
            }
        }
        return view;
    }

    @OnClick(R.id.logout_btn)
    public void onLogout(View v){
        MyApplication.addJobInBackground(new LogoutJob());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHandle(LoginEvent.OnLogout event) {
        UserUtil.setUser(getActivity(), new User());
        UserUtil.setToken(getActivity(), "");
        UserUtil.setUserId(getActivity(),"");
        getActivity().finish();
        LoginActivity.start(getActivity());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHandle(LoginEvent.OnLogoutFail event) {
        Toast.makeText(getActivity(), "Can't logout", Toast.LENGTH_SHORT).show();
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
}
