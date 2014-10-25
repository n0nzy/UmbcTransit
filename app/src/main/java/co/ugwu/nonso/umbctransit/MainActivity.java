package co.ugwu.nonso.umbctransit;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;


public class MainActivity extends Activity {


    private static final String STOPS_TAB = "Stops";
    private static final String FAVS_TAB  = "Favorities";

    protected static final String THUMBNAIL_IDS = "thumbNailIDs";

    private ArrayList<Integer> mThumbIdsFlowers = new ArrayList<Integer>(
            Arrays.asList(R.drawable.ic_launcher));

    private ArrayList<Integer> mThumbIdsAnimals = new ArrayList<Integer>(
            Arrays.asList(R.drawable.ic_launcher));

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final ActionBar tabBar = getActionBar();
        tabBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        StopsFragment stopsFrag = new StopsFragment();
        //Bundle args = new Bundle();
        //args.putIntegerArrayList(THUMBNAIL_IDS, mThumbIdsFlowers);
        //stopsFrag.setArguments(args);
        tabBar.addTab(tabBar.newTab().setText(STOPS_TAB).setTabListener(new TabListener(stopsFrag)));

        FavFragment favFrag = new FavFragment();
        //args = new Bundle();
        //args.putIntegerArrayList(THUMBNAIL_IDS, mThumbIdsAnimals);
        //favFrag.setArguments(args);
        tabBar.addTab(tabBar.newTab().setText(FAVS_TAB).setTabListener(new TabListener(favFrag)));

    }

    public static class TabListener implements ActionBar.TabListener {
        private final Fragment mFragment;

        public TabListener(Fragment fragment) {
            mFragment = fragment;
        }

        @Override
        public void onTabReselected(Tab tab, FragmentTransaction ft) {
        }

        @Override
        public void onTabSelected(Tab tab, FragmentTransaction ft) {
            if (null != mFragment) {
                ft.replace(R.id.fragment_container, mFragment);
            }
        }

        @Override
        public void onTabUnselected(Tab tab, FragmentTransaction ft) {
            if (null != mFragment)
                ft.remove(mFragment);
        }
    }

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
}
