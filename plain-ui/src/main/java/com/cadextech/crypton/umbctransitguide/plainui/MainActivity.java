package com.cadextech.crypton.umbctransitguide.plainui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    private static final String[] STOPS = new String[]{"Arbutus/Irvington", "Arundel/BWI Marc Line", "Catonsville", "Downtown A", "Downtown B", "Halethorpe/Satelite"};
    static private final String TAG = "Activity Tester";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //This section handles the auto-complete form
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.ui_search_stop);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, STOPS);
        textView.setAdapter(adapter);

        //This section handles the auto-complete action
        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Chosen Route is:" + parent.getAdapter().getItem(position), Toast.LENGTH_SHORT).show();
            }
        });

        TextView mTextView = (TextView) findViewById(R.id.route1);
        // Declare and  setup click.
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activateTextView();
            }
        });

        //---------------------------------------------
        ListView listOfRoutes = new ListView(this);

        //setListAdapter(new ArrayAdapter<String>(TitlesListActivity.this, R.layout.list_text_item_layout, TitlesListActivity.mTitleArray));
        String[] routes_available = {"B: Bowensville", "C: Catonsville", "DA: Downtown A", "DB: Downtown B"};

        ListView lv = (ListView) findViewById(R.id.route_label);

        ArrayAdapter<String> routeAdapter = new ArrayAdapter(this, R.layout.list_item, routes_available); //new ListAdapter<String>(this, R.layout.list_avail_route, routes_available);
        listOfRoutes.setAdapter(routeAdapter);
        setContentView(listOfRoutes);

        //ListView mListView;

        // Get a reference to the ListView...
        //mListView = (ListView) findViewById(R.id.route_label);

        // ...and attach this adapter to it.
        //mListView.setAdapter(routeAdapter);

        Log.i(TAG, "Loaded Main Activity!!!");
        
    }// end onCreate()

    private void activateTextView() {
        //Log.i(TAG, "Entered page for clicked textview");

        //Context context = getApplicationContext();
        //CharSequence text = "textView was clicked!";
        //int duration = Toast.LENGTH_SHORT;

        //Toast.makeText(context, text, duration).show();

        Intent intent = new Intent(this,RouteActivity.class);
        this.startActivity(intent);

    }  // end method()


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    private void prepareUI() {
        TextView hw = (TextView) findViewById(R.id.ui_agency_name);
        hw.setText("Hello I/O 2013!");

        //hw.setBackground(getCurrentFocus().getBackground());
        //int maxWidth = hw.getMaxWidth();
        //hw.setMinHeight(maxWidth);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

} // end class
