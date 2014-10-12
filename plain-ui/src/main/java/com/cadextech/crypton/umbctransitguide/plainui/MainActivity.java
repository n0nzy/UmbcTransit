package com.cadextech.crypton.umbctransitguide.plainui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    private static final String[] ROUTES = new String[]{"Arbutus/Irvington", "Arundel/BWI Marc Line", "Catonsville", "Downtown A", "Downtown B", "Halethorpe/Satelite"};
    static private final String TAG = "MethodTester";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i(TAG, "Loaded Main Activity!!!");
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //This section handles the auto-complete form
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.ui_search_stop);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, ROUTES);
        textView.setAdapter(adapter);

        //This section handles the auto-complete action
        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Context context = getApplicationContext();
                //CharSequence text = "textView was clicked!";
                //int duration = Toast.LENGTH_SHORT;

                //Toast.makeText(context, text, duration).show();

                Toast.makeText(getApplicationContext(), "Chosen Route is:" + parent.getAdapter().getItem(position), Toast.LENGTH_SHORT).show();
            }
        });

        // --------------------------------------------------------------------- \\
        // Get a reference to the ListView...
        ListView listOfRoutes = (ListView) findViewById(R.id.route_label);

        MyRouteListAdapter customAdapter = new MyRouteListAdapter(this, ROUTES);
        //listOfRoutes.setAdapter(customAdapter);
        
    }// end onCreate()

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
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

    class MyRouteListAdapter extends ArrayAdapter<String> {
        TextView label;
        ImageView image;
        View row;

        public MyRouteListAdapter(Context context,String[] arr)
        {
            super(context, R.layout.list_item, arr);
        }

        public View getView(final int position, View convertView, ViewGroup parent)
        {
            try{
                LayoutInflater inflater=getLayoutInflater();
                row = inflater.inflate(R.layout.list_item, parent, false);
                label = (TextView)row.findViewById(R.id.item_title);
                label.setText(ROUTES[position]);
                label.setTextColor(Color.YELLOW);
            }catch(Exception e){

            }
            return row;
        }
    } // end class

} // end class
