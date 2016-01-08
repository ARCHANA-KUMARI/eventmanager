package com.robosoft.archanakumari.androideventmanager.Modal;

/**
 * Created by archanakumari on 28/12/15.
 */
public class DataProvider {

    private int mId;
    private String mEventName;
    private String mVenue;
    private String mParticipantList;
    private String mEventDate;
    private String mEventTime;


    public DataProvider(int mId, String mEventName, String mVenue, String mParticipantList, String mEventDate, String mEventTime) {

        this.mId = mId;
        this.mEventName = mEventName;
        this.mVenue = mVenue;
        this.mParticipantList = mParticipantList;
        this.mEventDate = mEventDate;
        this.mEventTime = mEventTime;
    }

    public int getmId() {
        return mId;
    }

    public String getmEventName() {
        return mEventName;
    }

    public String getmVenue() {
        return mVenue;
    }

    public String getmParticipantList() {
        return mParticipantList;
    }

    public String getmEventTime() {
        return mEventTime;
    }

    public String getmEventDate() {
        return mEventDate;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public void setmEventName(String mEventName) {
        this.mEventName = mEventName;
    }

    public void setmParticipantList(String mParticipantList) {
        this.mParticipantList = mParticipantList;
    }

    public void setmVenue(String mVenue) {
        this.mVenue = mVenue;
    }

    public void setmEventDate(String mEventDate) {
        this.mEventDate = mEventDate;
    }

    public void setmEventTime(String mEventTime) {
        this.mEventTime = mEventTime;
    }
}
