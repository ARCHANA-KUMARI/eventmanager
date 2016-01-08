package com.robosoft.archanakumari.androideventmanager.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.robosoft.archanakumari.androideventmanager.R;
import com.robosoft.archanakumari.androideventmanager.fragment.CurrentFragment;
import com.robosoft.archanakumari.androideventmanager.fragment.ViewDetailsFragment;

/**
 * Created by archanakumari on 28/12/15.
 */
public class PagerAdapter extends FragmentPagerAdapter {

    private String mTabName[];
    public PagerAdapter(FragmentManager fm,Context context) {
        super(fm);
        mTabName = context.getResources().getStringArray(R.array.name);
    }
    ViewDetailsFragment viewDetailsFragment;

    @Override
    public Fragment getItem(int position) {

        if(position == 0)
        {
            Log.i("Hello", "Hi i am in position 0");
           viewDetailsFragment = new ViewDetailsFragment();
            return viewDetailsFragment;

        }
       else {
            Log.i("Hello", "Hi i am in position 1 or else part ");
               CurrentFragment currentFragment = CurrentFragment.getInstance(position);
               return currentFragment;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabName[position];
    }
}
