package co.ugwu.nonso.umbctransitguide.plainui;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Route Activity
 *
 * This file contains the details (all available stops) associated with the chosen route.
 *
 *
 * Licensed under The MIT License
 * Redistributions of files should please contain the above copyright notice.
 *
 * @author        Ugwu Chinonso .O.
 *
 */
public class RouteActivity extends Activity {

    //String[] mStopsList = {"Commons Dr. & Park Rd.", "Park Rd. & Poplar Ave.", "Hilltop Cir. & Commons Dr.", "Administration Dr. Bus Shelter", "Hilltop Cir. & Hilltop Rd.", "Hilltop Cir. & Walker Ave.", "Hilltop Cir. & Center Rd.", "Poplar Ave. & Stadium Lot", "TRC @ Linden Ave", "Westland Blvd. & Circle Dr.", "Westland Blvd. & Courtney Rd.", "Maiden Choice La. & Westland Blvd", "Maiden Choice La. & Warren Tree", "Maiden Choice La. & Wilkens Ave", "Maiden Choice La. & Grouse Ct", "Maiden Choice La. & Symmington Aven."};

    public final String DEBUG_TAG = "Route Name Passed: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_route_list);
        setContentView(R.layout.activity_route);

        //Grab the (TextView) View  object in the layout xml that represents the name of the selected route
        TextView txtRouteName = (TextView) findViewById(R.id.route_name);

        //change the value of the object's text attribute
        if (obtainRouteNamePassed() != null) txtRouteName.setText(obtainRouteNamePassed());

        // get the List of Stops from the Route passed to this Activity and created ListView out of it.
        //final ArrayAdapter<String> adapter = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, android.R.id.text1, getStopsList(obtainRouteNamePassed()) );

        //setListAdapter(adapter);

    } // end onCreate()

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.route, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here.
        // The action bar will automatically handle clicks on the Home/Up button, so long as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_route_list) {
            startActivity(new Intent(this, RouteListActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);

    } // end method()

    /**
     * Calls the Route class and invokes a method that returns an array of stops for a specific route, requires the name of a route as input
     *
     * @param nameOfRoute
     * @return
     */
    private String[] getStopsList(String nameOfRoute) {

        Route chosenRoute = new Route(nameOfRoute);

        return chosenRoute.obtainStops();

    }// end method()

    //@Override
    public void onListItemClick(ListView l, View v, int pos, long id) {
        Intent chosenRouteIntent = new Intent(this, RouteListActivity.class);
        //chosenRouteIntent.putExtra(INDEX, mRouteList[pos]);
        //startActivity(chosenRouteIntent);
    }

    /**
     * Utility Function that extracts and returns the name of the passed Route from the RouteListActivity
     * @return String
     */
    private String obtainRouteNamePassed() {
        // Section handles extracting Intent data of the activity that called me (i.e. RouteActivity)
        Bundle extras = getIntent().getExtras();
        //Log.i( DEBUG_TAG, extras.getString("route_name") );
        if (extras != null) {
            //Log.i( DEBUG_TAG, extras.getString("route_name") );
            return extras.getString("route_name");
        }
        return null;
    }

} // End class RouteActivity
