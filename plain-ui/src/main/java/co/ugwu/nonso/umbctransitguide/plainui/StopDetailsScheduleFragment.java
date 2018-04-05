package co.ugwu.nonso.umbctransitguide.plainui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * StopDetail's Schedule: A simple Fragment that displays details about a selected Stop, such as a list of the scheduled arrival times for that stop.
 *
 * Licensed under The MIT License
 * Redistributions of files should please contain the above copyright notice.
 *
 * @author        Ugwu Chinonso .O.
 *
 */
public class StopDetailsScheduleFragment extends Fragment {


    private final boolean DEBUG_FLAG = true;
    private final String  DEBUG_TAG = StopDetailsScheduleFragment.class.getSimpleName();

    public StopDetailsScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_stop_details_schedule, container, false);

        // The Parent StopDetailsActivity called via intent.  Obtain the intent here and Inspect for [Bus] Stop Name data.
        Bundle extras = getActivity().getIntent().getExtras();

        if (extras != null) {

            // get Stop Name through Intent's Extra key
            String chosenStopName = extras.getString(RouteDetailsFragment.EXTRA_STOP_NAME);
            String chosenStopCode = extras.getString(RouteDetailsFragment.EXTRA_STOP_CODE);

            // Set the TextView for StopName with data obtained from the Intent's Extra data
            ((TextView) rootView.findViewById(R.id.textview_stop_name)).setText(chosenStopName+" ["+chosenStopCode+"]");

            // Set the TextView for RouteName with data obtained from the Intent's Extra data
            ((TextView) rootView.findViewById(R.id.textview_route_name)).setText(extras.getString(RouteDetailsFragment.EXTRA_ROUTE_NAME));

        }// end if

        return rootView;

    }// end method


} // end class StopDetailsScheduleFragment