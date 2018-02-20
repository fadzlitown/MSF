package my.edu.upm.msfapp.ui.main.evaluate.evaluation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import my.edu.upm.msfapp.model.Submit;

public class TabSectionAdapter extends FragmentStatePagerAdapter {

    private String [] name = new String[]{"Lecturer/Nurse","Patient"};
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<Submit> answerArrayList;

    public TabSectionAdapter(FragmentManager supportFragmentManager, ArrayList<Fragment> fragmentList) {
        super(supportFragmentManager);
        //1. get , Intent intent and  intent.getExtras() .. To get Bundle at fragment
        fragments.addAll(fragmentList);
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}