package co.ugwu.nonso.umbctransitguide.plainui;


import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

/**
 * RouteDetailsActivity
 *
 * This class loads the RouteDetailsFragment.
 *
 * Licensed under The MIT License
 * Redistributions of files should please contain the above copyright notice.
 *
 * Created on       on 10/25/14.
 * @author        Ugwu Chinonso .O.
 *
 */
public class RouteDetailsActivity extends ActionBarActivity {

    private final boolean DEBUG_FLAG = true;
    private final String  DEBUG_TAG = RouteDetailsActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_details);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_route_details, new RouteDetailsFragment())
                    .commit();
            if (DEBUG_FLAG) Log.d(DEBUG_TAG, " onCreate() : No saved Instance State! Hence, a new Fragment Transaction! ");
        } else {
            if (DEBUG_FLAG) Log.d(DEBUG_TAG, " onCreate() : Restoring previous state! ");
        }

    } // end method


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.route, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will automatically handle clicks on the Home/Up button, so long as you specify a parent activity in AndroidManifest.xml.

        // This extracts the ID of the clicked Menu item
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    } // end method


    @Override
    protected void onResume() {
        if (DEBUG_FLAG) Log.d(DEBUG_TAG, "in onResume()");
        super.onResume();
        // The activity has become visible (it is now "resumed").
    } // end method


    @Override
    protected void onDestroy() {
        if (DEBUG_FLAG) Log.d(DEBUG_TAG, "in onDestroy()");
        super.onDestroy();
        // The activity is about to be destroyed.
    } // end method


} //end class
