package co.ugwu.nonso.umbctransitguide.plainui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


/**
 * RouteList Activity : Home Page & Launcher Activity
 *
 * This class acts as the starting point by loading the RouteListFragment Class.
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

        if (DEBUG_FLAG) Log.d(DEBUG_TAG, "in onCreate");

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_route_list);

        //-----------------------------------------------------------------
        if (null == savedInstanceState) {
            if (DEBUG_FLAG) Log.d(DEBUG_TAG, " onCreate() : No previous state available to restore! ");
            //Invoke the Fragment that displays a dynamic list of available stops...
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.list_of_routes, new RouteListFragment())
                    .commit();
        } else {
            if (DEBUG_FLAG) Log.d(DEBUG_TAG, " onCreate() : Restoring previous state! ");
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

        // Get the Id of the selected menu item
        int id = item.getItemId();

        if (R.id.action_settings == id) {
            return true;
        }

        if (R.id.action_route_list == id) {
            startActivity(new Intent(this, RouteListActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);

    } // end method()

    @Override
    protected void onStart() {
        if (DEBUG_FLAG) Log.d(DEBUG_TAG, "in onStart");
        super.onStart();
        // The activity is about to become visible.
    }

    @Override
    protected void onResume() {
        if (DEBUG_FLAG) Log.d(DEBUG_TAG, "in onResume");
        super.onResume();
        // The activity has become visible (it is now "resumed").
    }

    @Override
    protected void onPause() {
        if (DEBUG_FLAG) Log.d(DEBUG_TAG, "in onPause");
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
    }

    @Override
    protected void onStop() {
        if (DEBUG_FLAG) Log.d(DEBUG_TAG, "in onStop");
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
    }

    @Override
    protected void onDestroy() {
        if (DEBUG_FLAG) Log.d(DEBUG_TAG, "in onDestroy");
        super.onDestroy();
        // The activity is about to be destroyed.
    }

} // end class