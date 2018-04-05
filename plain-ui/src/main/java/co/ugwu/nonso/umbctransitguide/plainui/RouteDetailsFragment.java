package co.ugwu.nonso.umbctransitguide.plainui;


import android.content.Intent;
//import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
//import android.widget.ArrayAdapter;

import android.support.v4.app.Fragment;
import android.widget.ListView;
import android.widget.TextView;
//import android.widget.Toast;

//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;


/**
 * A simple Fragment that displays details (i.e. a list of all available stops) associated with the chosen route.
 *
 * Licensed under The MIT License
 * Redistributions of files should please contain the above copyright notice.
 *
 * @author        Ugwu Chinonso .O.
 */
public class RouteDetailsFragment extends Fragment {

    private final boolean DEBUG_FLAG = true;
    private final String  DEBUG_TAG = RouteDetailsFragment.class.getSimpleName();

    //TO-DO: Use some utility function to set the Package name Prefix
    public final static String PACKAGE_NAME = "co.ugwu.nonso.umbctransitguide.plainui.";
    public final static String EXTRA_ROUTE_NAME  = PACKAGE_NAME+"ROUTE_NAME";
    public final static String EXTRA_STOP_ID     = PACKAGE_NAME+"STOP_ID";
    public final static String EXTRA_STOP_NAME   = PACKAGE_NAME+"STOP_NAME";
    public static final String EXTRA_STOP_CODE   = PACKAGE_NAME+"STOP_CODE";



    public RouteDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (DEBUG_FLAG) Log.d(DEBUG_TAG, " onCreate() called! ");

        // This calls the class that invokes the background thread using AsyncTask!
        //FetchStopsTask fetchStopsTask = new FetchStopsTask();

        // This passes the route ID of the currently selected route to the background thread of the FetchStopsTask class.
        //fetchStopsTask.execute(mRouteId);

    } // end onCreate()


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (DEBUG_FLAG) Log.d(DEBUG_TAG, " onCreateView() called! ");

        View rootView = inflater.inflate(R.layout.fragment_route_details, container, false);

        // The Parent Route Details Activity called via intent.  Obtain the intent here and Inspect for Route Name data.
        Intent intent = getActivity().getIntent();

        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {

            final String chosenRouteName = intent.getStringExtra(Intent.EXTRA_TEXT);
            //final String chosenRouteName = this.mRouteName;
            ((TextView) rootView.findViewById(R.id.textview_route_name)).setText(chosenRouteName);

            String[] menuCols = new String[] { "_id",  "name", "stop code", "closest arrival time" };
            int[] to = new int[] { 000, R.id.list_item_busstop_name, R.id.list_item_busstop_stopcode, R.id.list_item_stop_times };

            final MatrixCursor menuCursor = new MatrixCursor(menuCols);
            getActivity().startManagingCursor(menuCursor);

            if (DEBUG_FLAG) Log.d(DEBUG_TAG, chosenRouteName.toLowerCase() );

            menuCursor.addRow(new Object[] { "001", "Commons Drv and Park Road", "Stop Code: 2114", "10 min & 55 min" });
            menuCursor.addRow(new Object[] { "002", "Park Rd and Poplar Avenue", "Stop Code: 2115", "15 min & 1 hr" });
            menuCursor.addRow(new Object[] { "003", "Hilltop Circle and Commons Drive", "Stop Code: 2116", "20 min & 1hr 5min" });

            if ( chosenRouteName.toLowerCase().contains( "Arbutus".toLowerCase() ) ) {
                menuCursor.addRow(new Object[] { "004", "Administration Dr. Bus Shelter", "Stop Code: 4850", "25 min & 1hr 10 min" });
                menuCursor.addRow(new Object[] { "005", "Hilltop Cir. and Hilltop Rd.", "Stop Code: 4851", "30 min & 1hr 20 min" });
                menuCursor.addRow(new Object[] { "006", "Hilltop Cir. and Walker Ave.", "Stop Code: 4852", "40 min & 1hr 25 min" });
            }
            else if ( chosenRouteName.toLowerCase().contains( "Arundel".toLowerCase() ) ) {
                menuCursor.addRow(new Object[] { "007", "BWI Marc Station", "Stop Code: 6712", "30 min & 2hr 25 min" });
                menuCursor.addRow(new Object[] { "008", "Arundel Mills Mall Visitor Entrace #5", "Stop Code: 6713", "35 min & 2hr 45 min" });
                menuCursor.addRow(new Object[] { "009", "BWI Marc Station", "Stop Code: 6714", "40 min & 3hrs" });
            }
            else if ( chosenRouteName.toLowerCase().contains( "Catonsville".toLowerCase() ) ) {
                menuCursor.addRow(new Object[] { "010", "Rolling Road @ YMCA", "Stop Code: 7212", "25 min & 1 hr 5 min" });
                menuCursor.addRow(new Object[] { "011", "Rolling Road @ Valley Road (CCBC)", "Stop Code: 7213", "30 min & 1 hr 15 min" });
                menuCursor.addRow(new Object[] { "012", "Catonsville High @ Bloomsbury", "Stop Code: 7214", "40 min & 1hr 15 min" });
            }
            else if ( chosenRouteName.toLowerCase().contains( "Downtown".toLowerCase() ) ) {
                menuCursor.addRow(new Object[] { "013", "Greyhound Station @ Haines Street", "Stop Code: 7212", "30 min & 1hr 30 min" });
                menuCursor.addRow(new Object[] { "014", "MLK Blvd and Pratt St", "Stop Code: 7213", "35 min & 1hr 40 min" });
                menuCursor.addRow(new Object[] { "015", "Green St & Lombard St(UMB)", "Stop Code: 7214", "40 min & 1hr 50 min" });
            }
            else if ( chosenRouteName.toLowerCase().contains( "Halethorpe".toLowerCase() ) ) {
                menuCursor.addRow(new Object[] { "016", "Rolling Road @ YMCA", "Stop Code: 7212", "35 min & 1hr 25 min" });
                menuCursor.addRow(new Object[] { "017", "Rolling Road @ Valley Road", "Stop Code: 7213", "40 min & 1hr 35 min" });
                menuCursor.addRow(new Object[] { "018", "Catonsville High @ Bloomsbury", "Stop Code: 7214", "55 min & 1hr 55 min" });
            }

            SimpleCursorAdapter menuItems = new SimpleCursorAdapter( getActivity(), R.layout.list_item_stop_row, menuCursor, menuCols, to);

            // Get a reference to the ListView...
            ListView listView = (ListView) rootView.findViewById(R.id.listview_busstops);

            //..and and attach the Simple Cursor Adapter to it
            listView.setAdapter(menuItems);

            // Next attach a listener
            listView.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    menuCursor.moveToPosition(position);
                    int rowId = menuCursor.getInt(menuCursor.getColumnIndexOrThrow("_id"));
                    String clickedStopName = menuCursor.getString(menuCursor.getColumnIndexOrThrow("name"));
                    String clickedStopCode = menuCursor.getString(menuCursor.getColumnIndexOrThrow("stop code"));

                    if (DEBUG_FLAG) Log.d(DEBUG_TAG, "clicked row item = "+rowId);

                    //int duration = Toast.LENGTH_SHORT;
                    //Toast.makeText(getActivity(), "you clicked on item: "+rowId+" : "+clickedStopName, duration).show();

                    Intent intent = new Intent(getActivity(), StopDetailsActivity.class);
                    Bundle extras = new Bundle(); // Create a 'Bundle' of extras i want to pass to the StopDetailsActivity

                    String stopId = Integer.toString(rowId);  // or use this: String.valueOf(rowId);

                    extras.putString(EXTRA_STOP_ID, stopId);
                    extras.putString(EXTRA_STOP_NAME, clickedStopName);
                    extras.putString(EXTRA_STOP_CODE, clickedStopCode);
                    extras.putString(EXTRA_ROUTE_NAME, chosenRouteName);
                    intent.putExtras(extras);
                    startActivity(intent);
                } // end onItemClick()
            });

        } // end if

        return rootView;

    } // end method


} // end Class
