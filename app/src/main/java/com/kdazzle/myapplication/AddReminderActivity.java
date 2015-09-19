package com.kdazzle.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.kdazzle.myapplication.Fragments.ClassesFragment;
import com.kdazzle.myapplication.Fragments.ClubsFragment;
import com.kdazzle.myapplication.Fragments.CompaniesFragment;
import com.kdazzle.myapplication.Fragments.TeamsFragment;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

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

    private static Date date;
    private static Time time;

    public static HashMap<String, ArrayList<ToDoItem>> getMap() {
        return map;
    }

    private static HashMap<String, ArrayList<ToDoItem>> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_reminder);

        time = null;
        date = null;


//        getActionBar().setDisplayHomeAsUpEnabled(true);



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
                switch ("" + ((TextView) view).getText()) {
                    case "Homework":
                    case "Prelim":
                    case "Final":
                        classTV.setVisibility(TextView.VISIBLE);
                        clubTV.setVisibility(TextView.INVISIBLE);
                        companyTV.setVisibility(TextView.INVISIBLE);
                        athleticTV.setVisibility(TextView.INVISIBLE);
                        groupSpinner.setVisibility(View.VISIBLE);
                        if (ClassesFragment.getClassList().size() != 0) {
                            spinnerArrayAdapter = new ArrayAdapter<String>(AddReminderActivity.this, android.R.layout.simple_spinner_item, ClassesFragment.getClassList());
                        } else {
                            ArrayList<String> defaultClasses = new ArrayList<String>();
                            defaultClasses.add("You Must Add A Class");

                            spinnerArrayAdapter = new ArrayAdapter<String>(AddReminderActivity.this, android.R.layout.simple_spinner_item, defaultClasses);
                        }

                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                        groupSpinner.setAdapter(spinnerArrayAdapter);
                        break;
                    case "Interview":
                        companyTV.setVisibility(TextView.VISIBLE);
                        clubTV.setVisibility(TextView.INVISIBLE);
                        classTV.setVisibility(TextView.INVISIBLE);
                        athleticTV.setVisibility(TextView.INVISIBLE);
                        groupSpinner.setVisibility(View.VISIBLE);
                        if (CompaniesFragment.getCompanyList().size() != 0) {
                            spinnerArrayAdapter = new ArrayAdapter<String>(AddReminderActivity.this, android.R.layout.simple_spinner_item, CompaniesFragment.getCompanyList());
                        } else {
                            ArrayList<String> defaultCompanies = new ArrayList<String>();
                            defaultCompanies.add("You Must Add A Company");

                            spinnerArrayAdapter = new ArrayAdapter<String>(AddReminderActivity.this, android.R.layout.simple_spinner_item, defaultCompanies);
                        }

                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                        groupSpinner.setAdapter(spinnerArrayAdapter);
                        break;
                    case "Club Event":
                        clubTV.setVisibility(TextView.VISIBLE);
                        classTV.setVisibility(TextView.INVISIBLE);
                        companyTV.setVisibility(TextView.INVISIBLE);
                        athleticTV.setVisibility(TextView.INVISIBLE);
                        groupSpinner.setVisibility(View.VISIBLE);
                        if (ClubsFragment.getClubList().size() != 0) {
                            spinnerArrayAdapter = new ArrayAdapter<String>(AddReminderActivity.this, android.R.layout.simple_spinner_item, ClubsFragment.getClubList());
                        } else {
                            ArrayList<String> defaultClubs = new ArrayList<String>();
                            defaultClubs.add("You Must Add A Company");

                            spinnerArrayAdapter = new ArrayAdapter<String>(AddReminderActivity.this, android.R.layout.simple_spinner_item, defaultClubs);
                        }

                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                        groupSpinner.setAdapter(spinnerArrayAdapter);
                        break;
                    case "Athletic Team":
                        athleticTV.setVisibility(TextView.VISIBLE);
                        clubTV.setVisibility(TextView.INVISIBLE);
                        companyTV.setVisibility(TextView.INVISIBLE);
                        classTV.setVisibility(TextView.INVISIBLE);
                        groupSpinner.setVisibility(View.VISIBLE);
                        if (TeamsFragment.getTeamList().size() != 0) {
                            spinnerArrayAdapter = new ArrayAdapter<String>(AddReminderActivity.this, android.R.layout.simple_spinner_item, TeamsFragment.getTeamList());
                        } else {
                            ArrayList<String> defaultAthletics = new ArrayList<String>();
                            defaultAthletics.add("You Must Add A Team");

                            spinnerArrayAdapter = new ArrayAdapter<String>(AddReminderActivity.this, android.R.layout.simple_spinner_item, defaultAthletics);
                        }

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
        dateText.setText("Not Chosen");
        timeText = (TextView) findViewById(R.id.time_text);
        timeText.setText("Not Chosen");

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
                timePicker.show(getFragmentManager(), "Pick the Time");
            }
        });

        final Spinner reminderSpinner = (Spinner) findViewById(R.id.reminder_options_spinner);


        Button submit = (Button) findViewById(R.id.submit_add_reminder);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(groupSpinner.getSelectedItem() == "You Must Add A Class" ||
                        groupSpinner.getSelectedItem() == "You Must Add A Club" ||
                        groupSpinner.getSelectedItem() == "You Must Add A Company" ||
                        groupSpinner.getSelectedItem() == "You Must Add A Team" ||
                        date == null ||
                        time == null){
                    Toast.makeText(AddReminderActivity.this,"Invalid Input",Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent returnedIntent = new Intent();
                returnedIntent.putExtra("type", typesSpinner.getSelectedItem().toString());
                returnedIntent.putExtra("group", groupSpinner.getSelectedItem().toString());
                returnedIntent.putExtra("date",dateString);
                returnedIntent.putExtra("time", timeString);
                returnedIntent.putExtra("reminder", reminderSpinner.getSelectedItem().toString());

                ArrayList<ToDoItem> list = map.get(groupSpinner.getSelectedItem().toString());
                if(list == null){
                    list = new ArrayList<ToDoItem>();
                }
                list.add(new ToDoItem(typesSpinner.getSelectedItem().toString(),
                                groupSpinner.getSelectedItem().toString(),
                                dateString, timeString, reminderSpinner.getSelectedItem().toString()));
                map.put(groupSpinner.getSelectedItem().toString(), list);

                setResult(RESULT_OK, returnedIntent);
                finish();
            }
        });
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_add, menu);
//        return true;
//    }


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
            if (minute < 10) {
                time = new Time(hourOfDay, minute, 0);
                timeString = hourOfDay + ":0" + minute;
                timeText.setText(timeString);
            }
            else {
                time = new Time(hourOfDay, minute, 0);
                timeString = hourOfDay + ":" + minute;
                timeText.setText(timeString);
            }
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
            monthOfYear += 1;
            date = new Date(year,monthOfYear,dayOfMonth);
            dateString = monthOfYear + "/" + dayOfMonth + "/" + year;
            dateText.setText(dateString);
        }
    }





}
