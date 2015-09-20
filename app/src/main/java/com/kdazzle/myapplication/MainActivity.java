package com.kdazzle.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

import com.kdazzle.myapplication.Fragments.ClassesFragment;
import com.kdazzle.myapplication.Fragments.ClubsFragment;
import com.kdazzle.myapplication.Fragments.CompaniesFragment;
import com.kdazzle.myapplication.Fragments.NavigationDrawerFragment;
import com.kdazzle.myapplication.Fragments.TeamsFragment;
import com.kdazzle.myapplication.Fragments.TodoFragment;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,
        ClassesFragment.OnFragmentInteractionListener, ClubsFragment.OnFragmentInteractionListener,
        TodoFragment.OnFragmentInteractionListener, CompaniesFragment.OnFragmentInteractionListener,
        TeamsFragment.OnFragmentInteractionListener{

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Fragments for each piece
     */
    private Fragment todoFragment, classFragment, clubFragment, teamFragment, companyFragment;

    /**
     * Used to store the last screen title. For use in.
     */
//    private CharSequence mTitle;

    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        todoFragment = new TodoFragment();
        classFragment = new ClassesFragment();
        clubFragment = new ClubsFragment();
        companyFragment = new CompaniesFragment();
        teamFragment = new TeamsFragment();

        alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        if (savedInstanceState != null) {
            //Restore the fragment's instance
            if(getFragmentManager().findFragmentByTag("TODO")!=null){
                todoFragment = getSupportFragmentManager().getFragment(
                        savedInstanceState, "todoTAG");
            }
            else if(getFragmentManager().findFragmentByTag("CLASS")!=null){
                classFragment = getSupportFragmentManager().getFragment(
                        savedInstanceState, "classTAG");
            }
            else if(getFragmentManager().findFragmentByTag("CLUB")!=null){
                clubFragment = getSupportFragmentManager().getFragment(
                        savedInstanceState, "clubTAG");
            }
            else if(getFragmentManager().findFragmentByTag("COMPANY")!=null){
                clubFragment = getSupportFragmentManager().getFragment(
                        savedInstanceState, "companyTAG");
            }
            else if(getFragmentManager().findFragmentByTag("TEAM")!=null){
                clubFragment = getSupportFragmentManager().getFragment(
                        savedInstanceState, "teamTAG");
            }


        }



        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
//        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, todoFragment).commit();
        }
    }

    public void setAlarm(ToDoItem item){
        String[] mdy = item.getDate().split("/");
        int month = Integer.parseInt(mdy[0]);
        int day = Integer.parseInt(mdy[1]);
        int year = Integer.parseInt(mdy[2]);

        String[] time = item.getTime().split(":");
        int hour = Integer.parseInt(time[0]);
        int minute = Integer.parseInt(time[1]);

        Calendar calendar = Calendar.getInstance();

        calendar.set(year, month, day, hour, minute);

        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, calendar.getTimeInMillis(), alarmIntent);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            //Restore the fragment's instance
            if (getFragmentManager().findFragmentByTag("TODO") != null) {
                todoFragment = getSupportFragmentManager().getFragment(
                        savedInstanceState, "todoTAG");
            } else if (getFragmentManager().findFragmentByTag("CLASS") != null) {
                classFragment = getSupportFragmentManager().getFragment(
                        savedInstanceState, "classTAG");
            } else if (getFragmentManager().findFragmentByTag("CLUB") != null) {
                clubFragment = getSupportFragmentManager().getFragment(
                        savedInstanceState, "clubTAG");
            } else if (getFragmentManager().findFragmentByTag("COMPANY") != null) {
                clubFragment = getSupportFragmentManager().getFragment(
                        savedInstanceState, "companyTAG");
            } else if (getFragmentManager().findFragmentByTag("TEAM") != null) {
                clubFragment = getSupportFragmentManager().getFragment(
                        savedInstanceState, "teamTAG");
            }
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

//Save the fragment's instance

        if(getFragmentManager().findFragmentByTag("TODO") != null && getFragmentManager().findFragmentByTag("TODO").isVisible()){
            getSupportFragmentManager().putFragment(outState, "todoTAG", todoFragment);
        }
        else if(getFragmentManager().findFragmentByTag("CLASS") != null && getFragmentManager().findFragmentByTag("CLASS").isVisible()){
            getSupportFragmentManager().putFragment(outState, "classTAG", classFragment);
        }
        else if(getFragmentManager().findFragmentByTag("CLUB") != null && getFragmentManager().findFragmentByTag("CLUB").isVisible()){
            getSupportFragmentManager().putFragment(outState, "clubTAG", clubFragment);
        }
        else if(getFragmentManager().findFragmentByTag("COMPANY") != null && getFragmentManager().findFragmentByTag("COMPANY").isVisible()){
            getSupportFragmentManager().putFragment(outState, "companyTAG", companyFragment);
        }
        else if(getFragmentManager().findFragmentByTag("TEAM") != null && getFragmentManager().findFragmentByTag("TEAM").isVisible()){
            getSupportFragmentManager().putFragment(outState, "teamTAG", teamFragment);
        }



    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        if (position == 0) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            if (todoFragment != null)
                fragmentManager.beginTransaction().replace(R.id.container, todoFragment, "TODO").commit();



//            mTitle = getString(R.string.title_to_do);
        }
        else if (position == 1) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, classFragment, "CLASS").commit();
//            mTitle = getString(R.string.title_classes);

        }
        else if (position == 2) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, clubFragment,"CLUB").commit();
//            mTitle = getString(R.string.title_clubs);
        }

        else if (position == 3) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, companyFragment,"COMPANY").commit();
//            mTitle = getString(R.string.title_clubs);
        }


        else if (position == 4) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, teamFragment,"TEAM").commit();
//            mTitle = getString(R.string.title_clubs);
        }
    }
/*
    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }*/

//    public void restoreActionBar() {
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
//        actionBar.setDisplayShowTitleEnabled(true);
//        actionBar.setTitle(mTitle);
//    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        if (!mNavigationDrawerFragment.isDrawerOpen()) {
//            // Only show items in the action bar relevant to this screen
//            // if the drawer is not showing. Otherwise, let the drawer
//            // decide what to show in the action bar.
//            getMenuInflater().inflate(R.menu.menu_todo, menu);
//
//            restoreActionBar();
//            return true;
//        }
//        return super.onCreateOptionsMenu(menu);
//    }



    @Override
    public void onFragmentInteraction(String id) {

    }

/*
    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    } */
}
