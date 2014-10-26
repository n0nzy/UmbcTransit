package co.ugwu.nonso.umbctransitguide.plainui;


import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * RouteDetailsActivity
 *
 * This file contains the details (all available stops) associated with the chosen route.
 *
 * Licensed under The MIT License
 * Redistributions of files should please contain the above copyright notice.
 *
 * Created on       on 10/25/14.
 * @author        Ugwu Chinonso .O.
 *
 */
public class RouteDetailsActivity extends ActionBarActivity {

    //private final boolean DEBUG_FLAG = true;
    //private final String  DEBUG_TAG = RouteDetailsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_details);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    //.add(R.id.container_route_details, new RouteDetailsFragment())
                    .add(R.id.container_route_details, new ItemListFragment())
                    .commit();
        }
    } // end onCreate()

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.route, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will automatically handle clicks on the Home/Up button, so long as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    } // end method()

} //end class
