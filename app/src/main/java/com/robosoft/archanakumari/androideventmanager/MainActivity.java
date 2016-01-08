package com.robosoft.archanakumari.androideventmanager;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Toolbar toolbar;
    Spinner spinner;
    private Button mSetDate;
    private int mYear_x,mMonth_x,mDay_x;
    static final int DIALOG_ID = 0;
    EditText mCurrentTime,mSelectedTime,mEventName,mVenue;
    private Button mStartTime,mEndTime;
    static final int DIALOG_ID_TIME = 1;
    private int mHour_x,mMinute_x,mSecond;
    static final int DIALOG_ID_TIME_END = 2;
    String mSpinnerItem;
    DatabaseAdapter databaseAdapter;
    Toast mToast;
    private int mCurrentYear,mCurrentMonth,mCurrentDay,mCurrentHour,mCurrentMinute,mCurrentSecond;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         toolbar = (Toolbar) findViewById(R.id.appbar);
         setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
         spinner = (Spinner) findViewById(R.id.spinner);
         ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this, R.array.array, android.R.layout.simple_spinner_item);
         spinner.setAdapter(arrayAdapter);
         spinner.setOnItemSelectedListener(this);
         final Calendar calendar = Calendar.getInstance();
         mYear_x = calendar.get(Calendar.YEAR);
         mMonth_x = calendar.get(Calendar.MONTH);
         mDay_x = calendar.get(Calendar.DAY_OF_MONTH);
         mCurrentTime = (EditText) findViewById(R.id.editcurrenttime);
         mSelectedTime = (EditText) findViewById(R.id.editselectedtime);
         mEventName = (EditText) findViewById(R.id.eventname);
         mVenue = (EditText) findViewById(R.id.venue);
         showDialogOnButtonClick();
         showTimePickerDialog();
         showTimerPickerDialogForEndDate();
        Calendar cal = new GregorianCalendar();
        int mCurrentYear,mCurrentMonth,mCurrentDay;
        mCurrentYear = cal.get(Calendar.YEAR);
        mCurrentMonth = cal.get(Calendar.MONTH)+1;
        mCurrentDay = cal.get(Calendar.DAY_OF_MONTH);
        mCurrentHour = cal.get(Calendar.HOUR);
        mCurrentMinute = cal.get(Calendar.MINUTE);
        mCurrentSecond = cal.get(Calendar.SECOND);


        mCurrentTime.setText(mCurrentYear + "/" + mCurrentMonth + "/" + mCurrentDay+ " " +  mCurrentHour + ":" +mCurrentMinute+":"+mCurrentSecond) ;
       // mSelectedTime.setText(+mYear_x + "/" + mMonth_x + "/" + mDay_x + " " + mHour_x + ":" + mMinute_x);
        databaseAdapter = new DatabaseAdapter(this);
    }

    public void showDialogOnButtonClick(){
        Log.i("Hello", "hI I AM showDialogOnButtonClick");
        mSetDate = (Button) findViewById(R.id.setdate);
        mSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             showDialog(DIALOG_ID);
            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if(id == DIALOG_ID)
        {
            Log.i("Hello","Hi i am in onCreateDialog If Method");
            return new DatePickerDialog(this,mDatepickerListener,mYear_x,mMonth_x,mDay_x);
        }
        else if(id == DIALOG_ID_TIME){

            return new TimePickerDialog(this,mTimepickerListener,mHour_x,mMinute_x,false);
        }
        else if(id == DIALOG_ID_TIME_END)
        {
            return new TimePickerDialog(this,mTimepickerListener,mHour_x,mMinute_x,false);
        }
        return null;
    }

    private TimePickerDialog.OnTimeSetListener mTimepickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            mHour_x = hourOfDay;
            mMinute_x = minute;
          //  mCurrentTime.setText(mYear_x+"/"+mMonth_x+"/"+mDay_x+" "+mHour_x+":"+mMinute_x);
            mSelectedTime.setText(+mYear_x+"/"+mMonth_x+"/"+mDay_x+" "+mHour_x+":"+mMinute_x);
            Toast.makeText(MainActivity.this,mHour_x+":"+mMinute_x,Toast.LENGTH_LONG).show();
        }
    };

    private DatePickerDialog.OnDateSetListener mDatepickerListener = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear_x = year;
            mMonth_x = monthOfYear+1;
            mDay_x = dayOfMonth;
          //  mCurrentTime.setText(mYear_x+"/"+mMonth_x+"/"+mDay_x+" "+mHour_x+":"+mMinute_x);
            mSelectedTime.setText(+mYear_x+"/"+mMonth_x+"/"+mDay_x+" "+mHour_x+":"+mMinute_x);
            Toast.makeText(MainActivity.this,mYear_x+"/"+mMonth_x+"/"+mDay_x,Toast.LENGTH_LONG).show();
        }
    };

    public void showTimePickerDialog(){
        mStartTime = (Button) findViewById(R.id.starttime);
        mStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_ID_TIME);
            }
        });
    }
    public void showTimerPickerDialogForEndDate(){

        mEndTime  = (Button) findViewById(R.id.endtime);
        mEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_ID_TIME_END);
            }
        });

    }
  //  mCurrentTime.setOnClickListner(new View.OnClickListener)
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        TextView textView = (TextView) view;
        mSpinnerItem = textView.getText().toString();
        Toast.makeText(this,""+textView.getText().toString(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    //INSERT DATA INTO DATABASE
    public void addEventDetails(View view) {

        String eventname = mEventName.getText().toString();
        String venuename = mVenue.getText().toString();
        String  participantlist = mSpinnerItem;
        String eventdate =  mSelectedTime.getText().toString();
        String date = eventdate.substring(0, eventdate.indexOf(" "));
        Log.i("date is "," "+date);
        String time = eventdate.substring(eventdate.indexOf(" ") + 1);
        Log.i("Time is "," "+time);
        //String
        long id = databaseAdapter.insertData(eventname, venuename, participantlist, date, time);
        if (id < 0) {
            Message.message(this, "Unsuccessfully");
        } else {
            Message.message(this, "Data is inserted successfully");
        }
        if(id>=0){

            //HH converts hour in 24 hours format (0-23), day calculation
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
           // Calendar cal = Calendar.getInstance();
            Date currentdatetime = new Date();
            Log.i("Hello","Currentdatetime"+currentdatetime);
             String startdate;
            Date startdatenew = null;
            startdate =  dateFormat.format(currentdatetime);
            Log.i("Hello","startdate"+startdate);
            try {
               startdatenew = dateFormat.parse(startdate);
                Log.i("Hello","Strt date is"+startdatenew);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //  currentdatetime = format.parse()
            Log.i("Hi","Current date is"+startdatenew);
            String setdate = date+" "+time+":00";
            Log.i("Hello","setdate is"+setdate);
            Date selectedDate=null;
            try {
                selectedDate = dateFormat.parse(setdate);
                Log.i("Hello","selectedDate is"+selectedDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Log.i("Hello","SelectedDate is"+selectedDate.getTime());
            Log.i("Hello", "StartDateNew is" + startdatenew.getTime());
            long diff = selectedDate.getTime() - startdatenew.getTime();
            Log.i("Hello","DIFF"+diff);
            //   Date selecteddatetime = new Date(setdate);
         //   Log.i("Hello:","Selecteddate is"+selecteddatetime);



            Intent intent = new Intent(MainActivity.this,AlarmReceiverActivity.class);
            PendingIntent pendingIntent =  PendingIntent.getActivity(MainActivity.this,2,intent,PendingIntent.FLAG_CANCEL_CURRENT);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+(diff),pendingIntent);
            if(mToast!=null){
                mToast.cancel();
            }
            mToast = Toast.makeText(getApplicationContext(), "Alarm for activity is set in" + diff + "Seconds", Toast.LENGTH_LONG);
            mToast.show();

        }


    }

     public void toCancel(View view){
         mEventName.setText("");
         mVenue.setText("");
         mCurrentTime.setText("");
         mSelectedTime.setText("");
         Intent intent = new Intent(MainActivity.this,AlarmReceiverActivity.class);
         PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,3,intent,0);
         AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
         alarmManager.cancel(pendingIntent);
         if(mToast!=null){
             mToast.cancel();
         }

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

