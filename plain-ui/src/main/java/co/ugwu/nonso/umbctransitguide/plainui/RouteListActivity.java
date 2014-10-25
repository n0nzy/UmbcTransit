package co.ugwu.nonso.umbctransitguide.plainui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

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
public class RouteListActivity extends ActionBarActivity {

    private static final boolean DEBUG_FLAG = true;
    private static final String DEBUG_TAG = RouteListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_route_list2);

        //-----------------------------------------------------------------
        if (savedInstanceState == null) {
            if (DEBUG_FLAG) Log.d(DEBUG_TAG, " onCreate() : Previous state info was saved! ");

            //Invoke the Fragment that displays a dynamic list of available stops...
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.list_of_routes, new RouteListFragment())
                    .commit();
        } else {
            if (DEBUG_FLAG) Log.d(DEBUG_TAG, " onCreate() : No previous state was saved! ");
        }

        //------------------------------------------------------------------
        // Handles logic for EditText View Object
        EditText filterEditText = (EditText) findViewById(R.id.filterText);

        filterEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //adapter.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        });

    } // end method()

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
            startActivity(new Intent(this, RouteListActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);

    } // end method()

} // end class