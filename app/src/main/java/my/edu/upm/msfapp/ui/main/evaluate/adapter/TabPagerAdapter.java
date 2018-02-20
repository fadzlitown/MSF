package my.edu.upm.msfapp.ui.main.evaluate.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import my.edu.upm.msfapp.ui.main.evaluate.LecturerNurseFragment;
import my.edu.upm.msfapp.ui.main.evaluate.PatientFragment;

/**
 * Created by fadzlirazali on 18/07/2016.
 */
public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private String [] name = new String[]{"Lecturer/Nurse","Patient"};
    private ArrayList<Fragment> fragments = new ArrayList<>();

    public TabPagerAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
        //1. get , Intent intent and  intent.getExtras() .. To get Bundle at fragment
        fragments.add(new LecturerNurseFragment());
        fragments.add(new PatientFragment());
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
