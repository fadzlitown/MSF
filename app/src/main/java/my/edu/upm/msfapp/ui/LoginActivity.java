package my.edu.upm.msfapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import my.edu.upm.msfapp.MyApplication;
import my.edu.upm.msfapp.R;
import my.edu.upm.msfapp.event.LoginEvent;
import my.edu.upm.msfapp.job.LoginJob;
import my.edu.upm.msfapp.model.Login;
import my.edu.upm.msfapp.util.Constant;
import my.edu.upm.msfapp.util.SharedPrefsHelper;
import my.edu.upm.msfapp.util.Util;

import static my.edu.upm.msfapp.util.Constant.Role.R_ROLE;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.roleRG)
    RadioGroup roleRG;
    @BindView(R.id.userNo)
    EditText userNo;
    @BindView(R.id.userNoLayout)
    TextInputLayout userNoLayout;
    @BindView(R.id.activity_login)
    LinearLayout activityLogin;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.progress)
    FrameLayout progress;


    public static void start(Context context) {
        Intent starter = new Intent(context, LoginActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        roleRG.setOnCheckedChangeListener(roleGBListener);
    }

    @OnClick(R.id.login_btn)
    public void onLogin(View v) {
        if (!TextUtils.isEmpty(userNo.getText().toString()) && !TextUtils.isEmpty(password.getText().toString())) {
            Login login = new Login(userNo.getText().toString(), password.getText().toString());
            setListShown(false);
            MyApplication.addJobInBackground(new LoginJob(login));
        } else {
            Toast.makeText(this, "Please fill in the required field", Toast.LENGTH_SHORT).show();
        }
    }

    RadioGroup.OnCheckedChangeListener roleGBListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.studentRole:
                    SharedPrefsHelper.getInstance().save(R_ROLE, Constant.RoleType.STUDENT);
                    setRoleName("Matric No");
                    break;
                case R.id.lecturerRole:
                    SharedPrefsHelper.getInstance().save(R_ROLE, Constant.RoleType.LECTURER);
                    setRoleName("Staff No");
                    break;
                case R.id.staffRole:
                    SharedPrefsHelper.getInstance().save(R_ROLE, Constant.RoleType.STAFF);
                    setRoleName("Staff No");
                    break;
            }
        }
    };

    public void setRoleName(String name) {
        userNo.setHint(name);
        userNoLayout.setHint(name);
    }

    private void setListShown(boolean shown) {
        Util.setListShown(activityLogin, progress, shown, true);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHandle(LoginEvent.OnLogin event) {
        setListShown(true);
        if (event.getUser() != null) {
            finish();
            MainActivity.start(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHandle(LoginEvent.OnFail event) {
        setListShown(true);
        Toast.makeText(this, "Incorrect username / password", Toast.LENGTH_SHORT).show();
    }
}
