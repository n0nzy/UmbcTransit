package com.cadextech.crypton.umbctransitguide.plainui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class RouteActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_route);

        // Section handles extracting Intent data of the activity that called me (i.e. RouteActivity)
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            String chosen_route = extras.getString("route_name");

            //Grab the (TextView) View  object in the layout xml that represents the name of the selected route
            TextView txtRouteName = (TextView) findViewById(R.id.route_name);

            //change the value of the object's text attribute
            if (txtRouteName != null) txtRouteName.setText(chosen_route);

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
        // Handle action bar item clicks here. The action bar will automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (R.id.action_settings == id) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
