package co.ugwu.nonso.umbctransitguide.plainui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * RouteList Activity : Home Page & Launcher Activity
 *
 * This file acts as the starting point by presenting you with a list of available routes to select from.
 *
 *
 * Licensed under The MIT License
 * Redistributions of files should please contain the above copyright notice.
 *
 * @author        Ugwu Chinonso .O.
 *
 */
public class RouteListActivity2 extends ListActivity {

    public static String[] mRouteList = {"A/I : Arbutus/Irvington", "A/B : Arundel/BWI Marc Line", "C : Catonsville", "D A : Downtown A", "D B : Downtown B", "H/S : Halethorpe/Satelite" };

    //private RouteFragment mRouteDetailsFragment;

    public static final String INDEX = "route_name";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_list);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, android.R.id.text1, getRouteList() );

        setListAdapter(adapter);


        // Handles logic for EditText View Object
        EditText filterEditText = (EditText) findViewById(R.id.filterText);

        filterEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        });

    } // end method()

    /**
     * Utility function that returns a String list of routes
     * @return String
     */
    private List<String> getRouteList() {

        //Initialize empty list to fill in routes
        ArrayList<String> routeList = new ArrayList<String>();

        routeList.addAll(Arrays.asList(mRouteList));

        return routeList;

    }// end method()

    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {
        Intent chosenRouteIntent = new Intent(this, co.ugwu.nonso.umbctransitguide.plainui.RouteActivity.class);
        chosenRouteIntent.putExtra(INDEX, mRouteList[pos]);
        startActivity(chosenRouteIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.route_list, menu);
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
            startActivity(new Intent(this, RouteListActivity2.class));
            return true;
        }

        return super.onOptionsItemSelected(item);

    } // end method()

} // end class