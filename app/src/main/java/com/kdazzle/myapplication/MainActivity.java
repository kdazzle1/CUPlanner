package com.kdazzle.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;

import com.kdazzle.myapplication.Adapters.ToDoItemAdapter;
import com.kdazzle.myapplication.Fragments.ClassesFragment;
import com.kdazzle.myapplication.Fragments.ClubsFragment;
import com.kdazzle.myapplication.Fragments.CompaniesFragment;
import com.kdazzle.myapplication.Fragments.NavigationDrawerFragment;
import com.kdazzle.myapplication.Fragments.TeamsFragment;
import com.kdazzle.myapplication.Fragments.TodoFragment;

import java.util.ArrayList;


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
    private TodoFragment todoFragment;
    private ClassesFragment classFragment;
    private ClubsFragment clubFragment;
    private TeamsFragment teamFragment;
    private CompaniesFragment companyFragment;

    /**
     * Used to store the last screen title. For use in.
     */
//    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        todoFragment = new TodoFragment();
        classFragment = new ClassesFragment();
        clubFragment = new ClubsFragment();
        companyFragment = new CompaniesFragment();
        teamFragment = new TeamsFragment();



//        if (savedInstanceState != null) {
//            //Restore the fragment's instance
//            if(getFragmentManager().findFragmentByTag("TODO")!=null){
//                todoFragment = getSupportFragmentManager().getFragment(
//                        savedInstanceState, "todoTAG");
//            }
//            else if(getFragmentManager().findFragmentByTag("CLASS")!=null){
//                classFragment = getSupportFragmentManager().getFragment(
//                        savedInstanceState, "classTAG");
//            }
//            else if(getFragmentManager().findFragmentByTag("CLUB")!=null){
//                clubFragment = getSupportFragmentManager().getFragment(
//                        savedInstanceState, "clubTAG");
//            }
//            else if(getFragmentManager().findFragmentByTag("COMPANY")!=null){
//                clubFragment = getSupportFragmentManager().getFragment(
//                        savedInstanceState, "companyTAG");
//            }
//            else if(getFragmentManager().findFragmentByTag("TEAM")!=null){
//                clubFragment = getSupportFragmentManager().getFragment(
//                        savedInstanceState, "teamTAG");
//            }
//
//
//        }



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

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            //Restore the fragment's instance
            if (getFragmentManager().findFragmentByTag("TODO") != null) {
                todoFragment = (TodoFragment) getSupportFragmentManager().getFragment(
                        savedInstanceState, "todoTAG");
            } else if (getFragmentManager().findFragmentByTag("CLASS") != null) {
                classFragment = (ClassesFragment)getSupportFragmentManager().getFragment(
                        savedInstanceState, "classTAG");
            } else if (getFragmentManager().findFragmentByTag("CLUB") != null) {
                clubFragment = (ClubsFragment)getSupportFragmentManager().getFragment(
                        savedInstanceState, "clubTAG");
            } else if (getFragmentManager().findFragmentByTag("COMPANY") != null) {
                companyFragment = (CompaniesFragment)getSupportFragmentManager().getFragment(
                        savedInstanceState, "companyTAG");
            } else if (getFragmentManager().findFragmentByTag("TEAM") != null) {
                teamFragment = (TeamsFragment)getSupportFragmentManager().getFragment(
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

//                getSupportActionBar().setTitle("To Do");
                NavigationDrawerFragment.setTitle(0);
//            mTitle = getString(R.string.title_to_do);
        }
        else if (position == 1) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, classFragment, "CLASS").commit();
//            mTitle = getString(R.string.title_classes);
//            getSupportActionBar().setTitle("Classes");
            NavigationDrawerFragment.setTitle(1);
        }
        else if (position == 2) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, clubFragment,"CLUB").commit();
//            mTitle = getString(R.string.title_clubs);
//            getSupportActionBar().setTitle("Clubs");
            NavigationDrawerFragment.setTitle(2);
        }

        else if (position == 3) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, companyFragment,"COMPANY").commit();
//            mTitle = getString(R.string.title_clubs);
//            getSupportActionBar().setTitle("Companies");
            NavigationDrawerFragment.setTitle(3);
        }


        else if (position == 4) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, teamFragment,"TEAM").commit();
//            mTitle = getString(R.string.title_clubs);
//            getSupportActionBar().setTitle("Teams");
            NavigationDrawerFragment.setTitle(4);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();



        SharedPreferences prefs = getSharedPreferences(getClass().getName(), Context.MODE_PRIVATE);
//        sKey.clear();
        int classListSize = prefs.getInt("class_list_size", 0);
        ArrayList<String> classList = new ArrayList<>();

        int counter = 0;

        for(int i=0;i<classListSize;i++)
        {
            counter++;
            classList.add(prefs.getString("class_list_" + i, null));
        }


        int clubListSize = prefs.getInt("clubs_list_size", 0);
        ArrayList<String> clubList = new ArrayList<>();

        for(int i=0;i<clubListSize;i++)
        {
            counter++;
            clubList.add(prefs.getString("clubs_list_" + i, null));
        }


        int companiesListSize = prefs.getInt("companies_list_size", 0);
        ArrayList<String> companiesList = new ArrayList<>();

        for(int i=0;i<companiesListSize;i++)
        {
            counter++;
            companiesList.add(prefs.getString("companies_list_" + i, null));
        }


        int teamListSize = prefs.getInt("team_list_size", 0);
        ArrayList<String> teamList = new ArrayList<>();

        for(int i=0;i<teamListSize;i++)
        {
            counter++;
            teamList.add(prefs.getString("team_list_" + i, null));
        }


        int todoListSize = prefs.getInt("todo_list_size", 0);



        ArrayList<ToDoItem> todoList = new ArrayList<>();

        if(todoListSize < TodoFragment.getList().size()){
            return;
        }

//        Log.e("MINE","todoList.size in onresume is " +todoList.size());
        Log.e("MINE","todoListSize in onresume is " +todoListSize);

        for(int i=0;i<todoListSize;i++)
        {
            counter++;

            String type = prefs.getString("todo_list_type_" + i, null);
            String group = prefs.getString("todo_list_group_" + i, null);
            String date = prefs.getString("todo_list_date_" + i, null);
            String time = prefs.getString("todo_list_time_" + i, null);
            String reminder = prefs.getString("todo_list_reminder_" + i, null);

            todoList.add(new ToDoItem(type,group,date,time,reminder));

        }

        Log.e("MINE", "putting back " + counter + " values in onresume");

//        if(classFragment.getmAdapter()!=null){
            classFragment.renew(classList);
//            Log.e("MINE","fixing class vals");
//        }
//        if(companyFragment.getmAdapter()!=null){
            companyFragment.renew(companiesList);
//            Log.e("MINE", "fixing company vals");
//        }
//        if(clubFragment.getmAdapter()!=null){
            clubFragment.renew(clubList);
//            Log.e("MINE", "fixing club vals");
//        }
//        if(teamFragment.getmAdapter()!=null){
//        Log.e("MINE","this many teams: "+teamList.size());
            teamFragment.renew(teamList);
//            Log.e("MINE", "fixing team vals");
//        }



        Log.e("MINE","this many todo's: "+todoList.size() + " in onresume");
        todoFragment.renew(todoList);
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences prefs = getSharedPreferences(getClass().getName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = prefs.edit(); //2


        ArrayList<String> classList = ClassesFragment.getClassList();
        ArrayList<String> clubsList = ClubsFragment.getClubList();
        ArrayList<String> companiesList = CompaniesFragment.getCompanyList();
        ArrayList<String> teamList= TeamsFragment.getTeamList();
        ArrayList<ToDoItem> todoList = TodoFragment.getList();

        int counter = 0;

        editor.putInt("class_list_size", classList.size()); /* sKey is an array */

        for(int i=0;i<classList.size();i++)
        {
            editor.remove("class_list_" + i);
            editor.putString("class_list_" + i, classList.get(i));
            counter++;
        }

        editor.putInt("clubs_list_size", clubsList.size()); /* sKey is an array */

        for(int i=0;i<clubsList.size();i++)
        {
            editor.remove("clubs_list_" + i);
            editor.putString("clubs_list_" + i, clubsList.get(i));
            counter++;
        }

        editor.putInt("companies_list_size", companiesList.size()); /* sKey is an array */

        for(int i=0;i<companiesList.size();i++)
        {
            editor.remove("companies_list_" + i);
            editor.putString("companies_list_" + i, companiesList.get(i));
            counter++;
        }

        editor.putInt("team_list_size", teamList.size()); /* sKey is an array */

        for(int i=0;i<teamList.size();i++)
        {
            editor.remove("team_list_" + i);
            editor.putString("team_list_" + i, teamList.get(i));
            counter++;
        }

        editor.putInt("todo_list_size", todoList.size()); /* sKey is an array */
        Log.e("MINE","detected todo list size in onpause is " +todoList.size());

        int todoCounter = 0;
        for(int i=0;i<todoList.size();i++)
        {
            todoCounter++;
//            Log.e("MINE","saving a todo item");
            editor.remove("todo_list_type_" + i);
            editor.remove("todo_list_group_" + i);
            editor.remove("todo_list_date_" + i);
            editor.remove("todo_list_time_" + i);
            editor.remove("todo_list_reminder_" + i);

            editor.putString("todo_list_type_" + i, todoList.get(i).getType());
            editor.putString("todo_list_group_" + i, todoList.get(i).getGroup());
            editor.putString("todo_list_date_" + i, todoList.get(i).getDate());
            editor.putString("todo_list_time_" + i, todoList.get(i).getTime());
            editor.putString("todo_list_reminder_" + i, todoList.get(i).getReminder());
            counter++;
        }

        Log.e("MINE","saved " +todoCounter + " todo values");
        Log.e("MINE","saved "+counter+ " values" );


        editor.commit(); //4
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
//            // Only show items in the action bar relevant to this screen
//            // if the drawer is not showing. Otherwise, let the drawer
//            // decide what to show in the action bar.
//            getMenuInflater().inflate(R.menu.menu_todo, menu);
//
////            restoreActionBar();
//            return true;
//
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
