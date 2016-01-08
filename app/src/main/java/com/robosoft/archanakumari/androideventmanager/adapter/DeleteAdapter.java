package com.robosoft.archanakumari.androideventmanager.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.robosoft.archanakumari.androideventmanager.Modal.DataProvider;
import com.robosoft.archanakumari.androideventmanager.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by archanakumari on 29/12/15.
 */
public class DeleteAdapter extends ArrayAdapter {
    List list = new ArrayList();
    public DeleteAdapter(Context context, int resource) {
        super(context, resource);
    }



    @Override
    public void add(Object object) {
        super.add(object);
        Log.i("Hello", "Hi i am in add method of ListAdapter class ");
        list.add(object);
    }

    @Override
    public int getCount() {
        Log.i("Hello", "List size is" + list.size());
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        Log.i("Hello", "HI I AM IN getItem(int position)" + list.get(position));
        return list.get(position);
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutHandler layoutHandler;
        Log.i("Hi", "HI I AM IN GETView");
        if (row == null) {
            Log.i("HI", "h i am in row == null ");
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.view_details, parent, false);
            layoutHandler = new LayoutHandler();
            Log.i("Helllow", "Hi i am in getView()in ListAdapter");
            layoutHandler.textemployeeid = (TextView) row.findViewById(R.id.textuid);
            layoutHandler.texteventname = (TextView) row.findViewById(R.id.textevent);
            layoutHandler.textvenue = (TextView) row.findViewById(R.id.textvenue);
            layoutHandler.textparticipantlist = (TextView) row.findViewById(R.id.textparticipantlist);
            layoutHandler.texteventdate = (TextView) row.findViewById(R.id.texteventdate);
            layoutHandler.texteventtime = (TextView) row.findViewById(R.id.texteventdate);
            row.setTag(layoutHandler);

        }
        else {
            Log.i("Hi", "Hi i am else part of row == null ");
            layoutHandler = (LayoutHandler) row.getTag();
        }
        DataProvider dataProvider = (DataProvider) this.getItem(position);
        layoutHandler.textemployeeid.setText("" + dataProvider.getmId());
        layoutHandler.texteventname.setText(dataProvider.getmEventName());
        layoutHandler.textvenue.setText(dataProvider.getmVenue());
        layoutHandler.textparticipantlist.setText(dataProvider.getmParticipantList());
        layoutHandler.texteventdate.setText(dataProvider.getmEventDate());
        layoutHandler.texteventtime.setText(dataProvider.getmEventTime());
         row.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
               //  int noOfRows = databaseAdapter.deleteRow()
               //  Message.message(this, " " + noOfRows);

             }
         });
        return row;
    }


    static class LayoutHandler
    {
        TextView textemployeeid;
        TextView texteventname;
        TextView textvenue;
        TextView textparticipantlist;
        TextView texteventdate;
        TextView texteventtime;

    }
}
/*
  int noOfRows = databaseAdapter.deleteRow();
        Message.message(this," "+noOfRows);
 */