package com.robosoft.archanakumari.androideventmanager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.robosoft.archanakumari.androideventmanager.adapter.PagerAdapter;
import com.robosoft.archanakumari.androideventmanager.tabs.SlidingTabLayout;

public class SecondPage extends AppCompatActivity {


     Toolbar toolbar;
    private ViewPager mViewPager;
    private SlidingTabLayout mSlidingTabLayout;
    SQLiteDatabase sqLiteDatabase;
    DatabaseAdapter databaseAdapter;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);
        toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(),this));
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.slidetab);
        mSlidingTabLayout.setViewPager(mViewPager);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id==android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
