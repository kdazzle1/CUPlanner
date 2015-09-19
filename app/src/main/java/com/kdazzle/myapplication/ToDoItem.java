package com.kdazzle.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Eric on 9/19/2015.
 */
public class ToDoItem implements Parcelable{
    private String type;
    private String group;
    private String date;
    private String time;
    private String reminder;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    public ToDoItem(String newType, String newGroup, String newDate, String newTime, String newReminder){
        type = newType;
        group = newGroup;
        date = newDate;

        time = newTime;
        reminder = newReminder;
    }




    protected ToDoItem(Parcel in) {
        type = in.readString();
        group = in.readString();
        date = in.readString();

        time = in.readString();
        reminder = in.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(group);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(reminder);

//        dest.writeValue(mContext);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ToDoItem> CREATOR = new Parcelable.Creator<ToDoItem>() {
        @Override
        public ToDoItem createFromParcel(Parcel in) {
            return new ToDoItem(in);
        }

        @Override
        public ToDoItem[] newArray(int size) {
            return new ToDoItem[size];
        }
    };


}
