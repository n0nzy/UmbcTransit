package co.ugwu.nonso.umbctransitguide.plainui;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.support.v4.app.Fragment;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple Fragment that displays details about a selected Route, such as the list of stops in that route.
 *
 * Licensed under The MIT License
 * Redistributions of files should please contain the above copyright notice.
 *
 * @author        Ugwu Chinonso .O.
 */
public class RouteDetailsFragment extends Fragment {

    public RouteDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //String[] stop1 = {"Commons Drive", "Poplar Ave", "Hilltop Circle"};

        // Create some dummy data for the ListView.  Here's a sample weekly forecast
        String[] busStopsArray = {
                "Commons Drive & Park Road",
                "Park Rd & Poplar Ave.",
                "Hilltop Circle & Commons Dr.",
                "Administration Drive & Bus Shelter",
                "Rolling Rd @ YMCA",
                "Rolling Rd @ Valley Rd (CCBC)"
        };
        List<String> busStopsList = new ArrayList<String>(Arrays.asList(busStopsArray));

        //Create an ArrayAdapter.   The ArrayAdapter will take data from a source you specify, and use it to populate the ListView it is attached to.
        ArrayAdapter<String> busStopListAdapter =
                new ArrayAdapter<String>(
                        getActivity(), // The current context (the Parent Activity).
                        R.layout.list_item_busstop, // The name of the layout ID.
                        R.id.list_item_busstop_textview, // The ID of the text-view to populate.
                        busStopsList); //The ArrayList of data

        View rootView = inflater.inflate(R.layout.fragment_route_details, container, false);

        // The Parent Route Details Activity called via intent.  Obtain the intent here and Inspect for Route Name data.
        Intent intent = getActivity().getIntent();

        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            String forecastStr = intent.getStringExtra(Intent.EXTRA_TEXT);
            ((TextView) rootView.findViewById(R.id.textview_route_name)).setText(forecastStr);
        }

        // Get a reference to the ListView, and attach this adapter to it.
        ListView listView = (ListView) rootView.findViewById(R.id.listview_busstops);
        listView.setAdapter(busStopListAdapter);

        return rootView;

    } // end method()

} // end Class
