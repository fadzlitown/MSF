package my.edu.upm.msfapp.ui.main.evaluate;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import my.edu.upm.msfapp.R;
import my.edu.upm.msfapp.ui.main.evaluate.adapter.TabPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class LecturerPatientMainFragment extends Fragment {

    int[] iconsUnSelected = new int[]{R.drawable.nurse_i,
            R.drawable.patient_i};
    int[] iconsSelected = new int[]{R.drawable.nurse_a,
            R.drawable.patient_a};
    private final String[] title = {"List", "Evaluate"};

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private TabPagerAdapter mAdapter;

    public LecturerPatientMainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lecturer_nurse_evaluate, container, false);
        ButterKnife.bind(this, view);
        init();
        setupTabIcons();
        return view;
    }

    private void init() {
        mAdapter = new TabPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(mAdapter);
        tablayout.setupWithViewPager(viewPager);
        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tablayout.getTabAt(tab.getPosition()).setIcon(iconsSelected[tab.getPosition()]);
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tablayout.getTabAt(tab.getPosition()).setIcon(iconsUnSelected[tab.getPosition()]);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setupTabIcons() {
        tablayout.getTabAt(0).setIcon(iconsSelected[0]);
        tablayout.getTabAt(1).setIcon(iconsUnSelected[1]);
    }

}
