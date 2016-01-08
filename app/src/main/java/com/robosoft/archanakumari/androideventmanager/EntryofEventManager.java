package com.robosoft.archanakumari.androideventmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class EntryofEventManager extends AppCompatActivity {

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entryof_event_manager);
       toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
    }

    public void toGoHomepage(View view){
     Intent intent = new Intent(this,MainActivity.class);
       startActivity(intent);

    }
    public void toDelete(View view){
        Intent intent = new Intent(this,DisplayActivity.class);
        startActivity(intent);

    }
    public void toDisplay(View view){
        Intent intent = new Intent(this,DisplayActivity.class);
        startActivity(intent);
    }
}
