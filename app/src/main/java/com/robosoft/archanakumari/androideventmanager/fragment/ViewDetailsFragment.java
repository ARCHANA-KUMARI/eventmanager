package com.robosoft.archanakumari.androideventmanager.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.robosoft.archanakumari.androideventmanager.DatabaseAdapter;
import com.robosoft.archanakumari.androideventmanager.Message;
import com.robosoft.archanakumari.androideventmanager.Modal.DataProvider;
import com.robosoft.archanakumari.androideventmanager.R;
import com.robosoft.archanakumari.androideventmanager.adapter.ListDataAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewDetailsFragment extends Fragment {


    SQLiteDatabase sqLiteDatabase;
    DatabaseAdapter databaseAdapter;
    Cursor cursor;
    Context context;
    View view;
    int noOfRows;
    ListDataAdapter listDataAdapter;
    ListView listView;
    DataProvider dataProvider;
    public ViewDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getContext();
        Log.i("Hello", "I AM IN ONCREATEvIEW");
        // Inflate the layout for this fragment
       view =  inflater.inflate(R.layout.fragment_view_details, container, false);
        listView = (ListView) view.findViewById(R.id.list);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("Hello", "I AM IN ONactivityCreated");
        databaseAdapter = new DatabaseAdapter(context);
        listDataAdapter = new ListDataAdapter(context,R.layout.view_details);
        sqLiteDatabase = databaseAdapter.helper.getReadableDatabase();
        cursor = databaseAdapter.getAllData(sqLiteDatabase);
        listView.setAdapter(listDataAdapter);
        if(cursor.moveToFirst()){
            do {
               int uid = cursor.getInt(0);
                //Log.i("Uid is",""+uid);
                String eventname = cursor.getString(1);
               // Log.i("Event name",eventname);
                String venue = cursor.getString(2);
                //Log.i("Venue is",venue);
                String participantlist = cursor.getString(3);
                //Log.i("Participant List",participantlist);
                String eventdate = cursor.getString(4);
               // Log.i("Event date",eventdate);
                String eventtime = cursor.getString(5);
                //Log.i("Event time",eventtime);
                //Log.i("Hello", "I AM IN cursormovetofirst");
                dataProvider = new DataProvider(uid,eventname,venue,participantlist,eventdate,eventtime);
                listDataAdapter.add(dataProvider);
            }while(cursor.moveToNext());
        }

        cursor = databaseAdapter.getAllData(sqLiteDatabase);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Log.i("HI", "i am in onClick methood");
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete Alert");
                builder.setMessage("Are you sure want to delete?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int count = 0;
                        if (cursor.moveToFirst()) {
                            Log.i("HI", "i am in onClick methood");
                            do {

                                if (count == position) {
                                    // Log.i("HI ","I AND POSITION BOTH ARE SAME"+i+"AND"+position);
                                    int uid = cursor.getInt(0);
                                    noOfRows = databaseAdapter.deleteRow(uid, sqLiteDatabase);
                                    listDataAdapter.remove(dataProvider);
                                    listDataAdapter.notifyDataSetChanged();

                                    if (noOfRows <= 0) {
                                        Message.message(context, "Data is not Deleted");
                                    } else {
                                        Message.message(context, "Deleted Successfully");
                                    }
                                    Log.i("Hello", "Total no of rows deletes is" + noOfRows);
                                }
                                count++;
                            } while (cursor.moveToNext());


                        }
                    }
                });
                builder.setNegativeButton("No", null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();


            }
        });



    }
}
