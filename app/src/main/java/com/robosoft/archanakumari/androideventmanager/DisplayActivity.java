package com.robosoft.archanakumari.androideventmanager;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.robosoft.archanakumari.androideventmanager.fragment.ViewDetailsFragment;

public class DisplayActivity extends AppCompatActivity {

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tryactivity);
        toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        FragmentManager fragmentManager = getSupportFragmentManager();
        ViewDetailsFragment viewDetailsFragment = new ViewDetailsFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container,viewDetailsFragment);
        fragmentTransaction.commit();


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
