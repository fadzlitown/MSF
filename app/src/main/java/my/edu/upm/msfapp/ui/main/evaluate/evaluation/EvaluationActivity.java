package my.edu.upm.msfapp.ui.main.evaluate.evaluation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import my.edu.upm.msfapp.MyApplication;
import my.edu.upm.msfapp.R;
import my.edu.upm.msfapp.event.EvaluationEvent;
import my.edu.upm.msfapp.job.StartEvaluationJob;
import my.edu.upm.msfapp.model.Response;
import my.edu.upm.msfapp.model.Section;
import my.edu.upm.msfapp.model.Submit;
import my.edu.upm.msfapp.ui.BaseActivity;
import my.edu.upm.msfapp.util.UserUtil;
import my.edu.upm.msfapp.util.Util;
import my.edu.upm.msfapp.view.DeactivatedViewPager;

public class EvaluationActivity extends BaseActivity {

    private static final String EVAL_ID = "id";
    private static final String EVALUATEE_USERNAME = "evaluatee";
    @BindView(R.id.view_pager)
    public DeactivatedViewPager viewPager;
    public TabSectionAdapter mAdapter;
    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolBar;
    @BindView(R.id.progress)
    FrameLayout progress;
    private ArrayList<Section> list;
    private long evalId;
    private String evaluateeUsername;
    private ArrayList<Section> sectionList;
    private ArrayList<Submit> answerArrayList;
    private String[] titleStr;

    public static void start(Context context, long evaluationID, String evaluateeUsername) {
        Intent intent = new Intent(context, EvaluationActivity.class);
        intent.putExtra(EVAL_ID, evaluationID);
        intent.putExtra(EVALUATEE_USERNAME, evaluateeUsername);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);
        ButterKnife.bind(this);
        setToolbar();
        if (getIntent().hasExtra(EVAL_ID) && getIntent().hasExtra(EVALUATEE_USERNAME)) {
            evalId = getIntent().getLongExtra(EVAL_ID, 0);
            evaluateeUsername = getIntent().getStringExtra(EVALUATEE_USERNAME);
            setListShown(false);
            MyApplication.addJobInBackground(new StartEvaluationJob(UserUtil.getGroupId(this), String.valueOf(evalId), evaluateeUsername));
        }
    }

    private void setToolbar() {
        if (toolBar != null) {
            setSupportActionBar(toolBar);
            getSupportActionBar().setTitle("");
            toolBar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (viewPager.getCurrentItem() <= 0) {
                        onBackPressed();
                    } else  {
                        viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
                    }
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        if(viewPager!=null){
            if (viewPager.getCurrentItem() <= 0) {
                super.onBackPressed();
            } else  {
                viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
            }
        }
    }

    private void setListShown(boolean shown) {
        Util.setListShown(viewPager, progress, shown, true);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHandle(EvaluationEvent.StartResponse event) {
        setListShown(true);
        if (event.getStartResponse() != null) {
            Response r = event.getStartResponse();
            if (r.getGroup().getEvaluation().getSectionList().size() > 0) {
                sectionList = r.getGroup().getEvaluation().getSectionList();
                titleStr = new String[sectionList.size()];
                ArrayList<Fragment> fragmentList = new ArrayList<>(sectionList.size());

                for (int i = 0; i < sectionList.size(); i++) {
                    titleStr[i] = " " + sectionList.get(i).getName();
                    fragmentList.add(SectionFragment.newInstance(evalId, sectionList.get(i).getSectionId(), r.getResponseId(), i + 1 == sectionList.size()));
                }

                if(getSupportActionBar() != null && !TextUtils.isEmpty(titleStr[0])) getSupportActionBar().setTitle(titleStr[0]);
                mAdapter = new TabSectionAdapter(getSupportFragmentManager(), fragmentList);
                viewPager.setAdapter(mAdapter);
                viewPager.setOffscreenPageLimit(sectionList.size());
                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        if (getSupportActionBar() != null) {
                            int no = position + 1;
                            if(!TextUtils.isEmpty(titleStr[position])) getSupportActionBar().setTitle(titleStr[position]);
                            else getSupportActionBar().setTitle("Section " + no);
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
            } else {
                Toast.makeText(this, "No section & question on this page", Toast.LENGTH_SHORT).show();
            }
        }

    }

    HashMap<Integer, Submit> answerMap = new HashMap<>();

    public void addSubmitMap(int position, Submit submit) {
        answerMap.put(position, submit);
    }

    public void updateSubmitMap(int position, Submit submit) {
        if (answerMap.size() > 0) {
            answerMap.put(position, submit);
        }
    }

    public HashMap<Integer, Submit> getSubmitHashMap() {
        return answerMap;
    }

    public ArrayList<Submit> convertSubmitMapToArrayList() {
        ArrayList<Submit> arr;
        if (getSubmitHashMap().size() > 0) {
            arr = new ArrayList<Submit>(getSubmitHashMap().values());
        } else {
            arr = new ArrayList<Submit>();
        }
        return arr;
    }

}
