package my.edu.upm.msfapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import my.edu.upm.msfapp.MyApplication;
import my.edu.upm.msfapp.R;
import my.edu.upm.msfapp.event.UserEvent;
import my.edu.upm.msfapp.job.GetMemberJob;
import my.edu.upm.msfapp.model.Member;
import my.edu.upm.msfapp.ui.adapter.MainViewPagerAdapter;
import my.edu.upm.msfapp.ui.main.evaluate.StudentFragment;
import my.edu.upm.msfapp.ui.main.profile.ProfileFragment;
import my.edu.upm.msfapp.ui.main.userListing.UserListingFragment;
import my.edu.upm.msfapp.util.UserUtil;
import my.edu.upm.msfapp.view.CustomTabView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    int[] iconsUnSelected = new int[]{R.drawable.list_i,
            R.drawable.add_i,
            R.drawable.home_i};
    int[] iconsSelected = new int[]{R.drawable.list_a,
            R.drawable.add_a,
            R.drawable.home_a};
    private final String[] title = {"List", "Evaluate", "Profile"};

    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        MyApplication.addJobInBackground(new GetMemberJob(false, 0, 20));
    }

    private void setupViewPager(Member member) {
        MainViewPagerAdapter mAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mAdapter.addFragment(UserListingFragment.newInstance(member), "List");
        mAdapter.addFragment(StudentFragment.newInstance(member), "Evaluate");
        mAdapter.addFragment(new ProfileFragment(), "Profile");
        viewPager.setAdapter(mAdapter);
        viewPager.setOffscreenPageLimit(mAdapter.getCount());
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(onTabSelectedListener);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHandle(UserEvent.MemberList event) {
        if (event.getMemberList() != null) {
            if(event.getMemberList().getGroup()!=null){
                UserUtil.setGroupId(this, String.valueOf(event.getMemberList().getGroup().getGroupId()));
                if(!TextUtils.isEmpty(event.getMemberList().getGroup().getName()))
                    UserUtil.setGroupName(this, String.valueOf(event.getMemberList().getGroup().getName()));
                else
                    UserUtil.setGroupName(this,"No Group");
            }
            setupViewPager(event.getMemberList());
            setupTabIconsMembers();
        }
    }

    private void setupTabIconsMembers() {
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            CustomTabView tabView = new CustomTabView(this);
            tabView.setText(title[i]);

            if (i == 0) tabView.updateTextColor(R.color.colorPrimary);
            else tabView.updateTextColor(R.color.black);

            if (i == 0) tabView.updateIcon(iconsSelected[i]);
            else tabView.updateIcon(iconsUnSelected[i]);
            tabLayout.getTabAt(i).setCustomView(tabView);
        }
    }

    private TabLayout.OnTabSelectedListener onTabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            if(tab.getCustomView()!=null) {
                ((CustomTabView) tab.getCustomView()).updateIcon(iconsSelected[tab.getPosition()]);
                ((CustomTabView) tab.getCustomView()).updateTextColor(R.color.colorPrimary);
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            if(tab.getCustomView()!=null){
                ((CustomTabView) tab.getCustomView()).updateIcon(iconsUnSelected[tab.getPosition()]);
                ((CustomTabView) tab.getCustomView()).updateTextColor(R.color.black);
            }
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
            if(tab.getCustomView()!=null) {
                ((CustomTabView) tab.getCustomView()).updateIcon(iconsSelected[tab.getPosition()]);
                ((CustomTabView) tab.getCustomView()).updateTextColor(R.color.colorPrimary);
            }
        }
    };


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
}
