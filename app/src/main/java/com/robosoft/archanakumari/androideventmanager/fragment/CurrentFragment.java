package com.robosoft.archanakumari.androideventmanager.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robosoft.archanakumari.androideventmanager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentFragment extends Fragment {



    TextView textView;

    public static CurrentFragment getInstance(int position){
        CurrentFragment currentFragment = new CurrentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("Position",position);
        currentFragment.setArguments(bundle);
        return  currentFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_current,container,false);
        //textView = (TextView) view.findViewById(R.id.text);
        Bundle bundle = getArguments();
        if(bundle!=null){
            Log.i("hELLO", "I AM IN cURRENT FRAGMENT ONCREATEvIEW");
            Log.i("hELLO", "getInt(Position)" + bundle.getInt("Position"));
          //  textView.setText("The page is currently selected" + bundle.getInt("Position"));
        }
        return  view;
    }


}
