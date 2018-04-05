package co.ugwu.nonso.umbctransit;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import co.ugwu.nonso.umbctransit.R;

public class SimpleListViewProgrammActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_list_view_programm);

        ListView lv = (ListView) findViewById(R.id.listView1);
        String[] data_source = {"Malaysia", "US", "Indonesia", "Papau New Guinea", "Thailand", "Philippines", "Singapore", "Vietnam"};
        String[] phone_source = new String[] {"Android", "iPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SimpleListViewProgrammActivity.this, android.R.layout.simple_list_item_1, phone_source);
        lv.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.simple_list_view_programm, menu);
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
}
