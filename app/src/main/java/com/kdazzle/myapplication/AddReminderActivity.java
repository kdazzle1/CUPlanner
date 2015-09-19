package com.kdazzle.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Eric on 9/19/2015.
 */
public class AddReminderActivity extends AppCompatActivity {

    private String[] sample_classes = {"cs2110","cs3110","cs4780"};
    private String[] sample_clubs = {"club1","club2","club3"};
    private String[] sample_athletics = {"Sports","IM Soc","IM VB"};
    private String[] sample_companies = {"Google", "Apple", "Microsoft"};

    private static TextView dateText;
    private static TextView timeText;

    private static String timeString;
    private static String dateString;

    private static Time time;
    private static Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        final Spinner typesSpinner = (Spinner) findViewById(R.id.types_spinner);



        final TextView classTV = (TextView) findViewById(R.id.class_textview);
        final TextView clubTV = (TextView) findViewById(R.id.club_textview);
        final TextView companyTV = (TextView) findViewById(R.id.company_textview);
        final TextView athleticTV = (TextView) findViewById(R.id.athletic_textview);


        final Spinner groupSpinner = (Spinner) findViewById(R.id.group_spinner);

        typesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter<String> spinnerArrayAdapter;
                switch (""+((TextView)view).getText()){
                    case "Homework":
                    case "Prelim":
                    case "Final":
                        classTV.setVisibility(TextView.VISIBLE);
                        clubTV.setVisibility(TextView.INVISIBLE);
                        companyTV.setVisibility(TextView.INVISIBLE);
                        athleticTV.setVisibility(TextView.INVISIBLE);
                        groupSpinner.setVisibility(View.VISIBLE);
                        spinnerArrayAdapter = new ArrayAdapter<String>(AddReminderActivity.this, android.R.layout.simple_spinner_item, sample_classes);
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                        groupSpinner.setAdapter(spinnerArrayAdapter);
                        break;
                    case "Interview":
                        companyTV.setVisibility(TextView.VISIBLE);
                        clubTV.setVisibility(TextView.INVISIBLE);
                        classTV.setVisibility(TextView.INVISIBLE);
                        athleticTV.setVisibility(TextView.INVISIBLE);
                        groupSpinner.setVisibility(View.VISIBLE);
                        spinnerArrayAdapter = new ArrayAdapter<String>(AddReminderActivity.this, android.R.layout.simple_spinner_item, sample_companies);
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                        groupSpinner.setAdapter(spinnerArrayAdapter);
                        break;
                    case "Club Event":
                        clubTV.setVisibility(TextView.VISIBLE);
                        classTV.setVisibility(TextView.INVISIBLE);
                        companyTV.setVisibility(TextView.INVISIBLE);
                        athleticTV.setVisibility(TextView.INVISIBLE);
                        groupSpinner.setVisibility(View.VISIBLE);
                        spinnerArrayAdapter = new ArrayAdapter<String>(AddReminderActivity.this, android.R.layout.simple_spinner_item, sample_clubs);
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                        groupSpinner.setAdapter(spinnerArrayAdapter);
                        break;
                    case "Athletic Team":
                        athleticTV.setVisibility(TextView.VISIBLE);
                        clubTV.setVisibility(TextView.INVISIBLE);
                        companyTV.setVisibility(TextView.INVISIBLE);
                        classTV.setVisibility(TextView.INVISIBLE);
                        groupSpinner.setVisibility(View.VISIBLE);
                        spinnerArrayAdapter = new ArrayAdapter<String>(AddReminderActivity.this, android.R.layout.simple_spinner_item, sample_athletics);
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                        groupSpinner.setAdapter(spinnerArrayAdapter);
                        break;

                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        dateText = (TextView) findViewById(R.id.date_text);
        timeText = (TextView) findViewById(R.id.time_text);

        Button dateButton = (Button) findViewById(R.id.date_picker);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getFragmentManager(),"Pick the Date");
            }
        });

        Button timeButton = (Button) findViewById(R.id.time_picker);
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getFragmentManager(),"Pick the Time");
            }
        });

        final Spinner reminderSpinner = (Spinner) findViewById(R.id.reminder_options_spinner);


        Button submit = (Button) findViewById(R.id.submit_add_reminder);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnedIntent = new Intent();
                returnedIntent.putExtra("type",typesSpinner.getSelectedItem().toString());
                returnedIntent.putExtra("group",groupSpinner.getSelectedItem().toString());
                returnedIntent.putExtra("date",dateString);
                returnedIntent.putExtra("time",timeString);
                returnedIntent.putExtra("reminder",reminderSpinner.getSelectedItem().toString());

                setResult(RESULT_OK,returnedIntent);
                finish();
            }
        });
    }


    public static class TimePickerFragment extends DialogFragment implements
            TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return
            return new TimePickerDialog(getActivity(), this, hour, minute, true);
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            time = new Time(hourOfDay,minute,0);
            timeString = hourOfDay + ":" + minute;
            timeText.setText(timeString);
        }
    }


    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
//
            // Create a new instance of TimePickerDialog and return
            return new DatePickerDialog(getActivity(),this,year,month,dayOfMonth);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            date = new Date(year,monthOfYear,dayOfMonth);
            dateString = monthOfYear + "/" + dayOfMonth + "/" + year;
            dateText.setText(dateString);
        }
    }





}
