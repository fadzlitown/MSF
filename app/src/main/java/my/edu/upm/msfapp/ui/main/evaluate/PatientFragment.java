package my.edu.upm.msfapp.ui.main.evaluate;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import my.edu.upm.msfapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PatientFragment extends Fragment {


    public PatientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_patient_evaluate, container, false);
    }

}
