package com.kdazzle.myapplication.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kdazzle.myapplication.Adapters.ClubsAdapter;
import com.kdazzle.myapplication.AddReminderActivity;
import com.kdazzle.myapplication.R;
import com.kdazzle.myapplication.ShowList;
import com.kdazzle.myapplication.ToDoItem;
import com.kdazzle.myapplication.dummy.DummyContent;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class ClubsFragment extends Fragment implements AbsListView.OnItemClickListener {

    private Bundle savedState = null;
    private String STAV = "stav";
    private String VSTUP = "vstup";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static ArrayList<String> clubList = new ArrayList<>();

    public static ArrayList<String> getClubList() {
        return clubList;
    }

    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ClubsAdapter mAdapter;

    public ClubsAdapter getmAdapter() {
        return mAdapter;
    }
//    // TODO: Rename and change types of parameters
//    public static ClassesFragment newInstance(String param1, String param2) {
//        ClassesFragment fragment = new ClassesFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }



    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ClubsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        // TODO: Change Adapter to display your content
        mAdapter = new ClubsAdapter(getActivity());
        mAdapter.setList(clubList);

        getActivity().setTitle("Clubs");
        setHasOptionsMenu(true);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_todo, menu);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
//            Intent intent = new Intent(getActivity(),AddReminderActivity.class);
//            startActivityForResult(intent,0);
            // Creating alert Dialog with one Button
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

            //AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();

            // Setting Dialog Title
            alertDialog.setTitle("Add Club");

            // Setting Dialog Message
            alertDialog.setMessage("Enter Club Name");
            //final EditText input = new EditText(this);
            //alertDialog.setView(input);

            final EditText input = new EditText(getActivity());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            input.setLayoutParams(lp);
            alertDialog.setView(input); // uncomment this line

            // Setting Icon to Dialog
//            alertDialog.setIcon(R.drawable.key);

            // Setting Positive "Yes" Button
            alertDialog.setPositiveButton("Done",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to execute after dialog
                            String newClub = "" + input.getText();


                            clubList.add(newClub);
                            mAdapter.setList(clubList);
//                            mAdapter.notifyDataSetChanged();
                            dialog.dismiss();
//                            Toast.makeText(getActivity(),"Password Matched", Toast.LENGTH_SHORT).show();
//                            Intent myIntent1 = new Intent(view.getContext(), Show.class);
//                            startActivityForResult(myIntent1, 0);
                        }
                    });
            // Setting Negative "NO" Button
            alertDialog.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to execute after dialog
                            dialog.cancel();
                        }
                    });


            // closed

            // Showing Alert Message
            alertDialog.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Log.e("MINE", "" + (savedInstanceState == null));
//        Log.e("MINE", "" + (savedState == null));
//        if(savedInstanceState!=null){
//            Log.e("MINE", savedInstanceState.getBundle(STAV).getStringArrayList("classList").get(0));
//        }

//        mAdapter.setList(ClassesFragment.getClassList());
//        mAdapter.notifyDataSetChanged();

        if(savedInstanceState != null && savedState == null) {
            savedState = savedInstanceState.getBundle(STAV);
        }
        if(savedState != null) {
            mAdapter.setList(savedState.getStringArrayList("clubList"));
        }
        savedState = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clubs_list, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        (mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);



        return view;
    }





    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

//Save the fragment's state here
        outState.putBundle(STAV, (savedState != null) ? savedState : saveState());;


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        savedState = saveState(); /* vstup defined here for sure */

    }

    private Bundle saveState() { /* called either from onDestroyView() or onSaveInstanceState() */
        Bundle state = new Bundle();
        state.putStringArrayList("clubList", mAdapter.getItems());
        return state;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;


        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
//            mListener.onFragmentInteraction(DummyContent.ITEMS.get(position).id);
            String item = mListView.getItemAtPosition(position).toString();
//            Toast.makeText(getActivity(), "You selected : " + item, Toast.LENGTH_SHORT).show();
//            Toast.makeText(getActivity(), "First Date : " + AddReminderActivity.getMap().get(item).get(0).getDate(), Toast.LENGTH_SHORT).show();
            ArrayList<ToDoItem> list = AddReminderActivity.getMap().get(item);

            if(list == null){
                list = new ArrayList<>();
            }

            Intent intent = new Intent(getActivity(),ShowList.class);
            intent.putExtra("name",item);
            intent.putParcelableArrayListExtra("list",list);

            startActivity(intent);

        }
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    public void renew(ArrayList<String> list){
        clubList = list;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }

}
