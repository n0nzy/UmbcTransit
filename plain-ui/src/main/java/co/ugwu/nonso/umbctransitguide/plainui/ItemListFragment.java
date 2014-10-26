package co.ugwu.nonso.umbctransitguide.plainui;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemListFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        // Create some dummy data for the ListView.  Here's a sample weekly forecast
        String[] busStopsArray = {
                "Commons Drive & Park Road",
                "Park Rd & Poplar Ave.",
                "Hilltop Circle & Commons Dr.",
                "Administration Drive & Bus Shelter",
                "Rolling Rd @ YMCA",
                "Rolling Rd @ Valley Rd (CCBC)"
        };
        List<String> busStopsList = new ArrayList<String>(Arrays.asList(busStopsArray));

        ArrayList<Item> items = new ArrayList<Item>();
        for (int i = 0; i < busStopsArray.length; i++) {
            String url = busStopsArray[i];
            String title = busStopsArray[i];
            String description = String.format("Description of Item %d", i);
            Item item = new Item(url, title, description);
            items.add(item);
        }



        setListAdapter(new ItemAdapter(getActivity(), items));

        return v;
    }
}