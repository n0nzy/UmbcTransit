package co.ugwu.nonso.umbctransitguide.plainui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.support.v4.app.Fragment;

import co.ugwu.nonso.umbctransitguide.plainui.R;

/**
 * Created on       on 10/24/14.
 * @author        Ugwu Chinonso .O.
 */
public class RouteListFragment extends Fragment {

    //public static String[] mRouteList = {"A/I : Arbutus/Irvington", "A/B : Arundel/BWI Marc Line", "C : Catonsville", "D A : Downtown A", "D B : Downtown B", "H/S : Halethorpe/Satelite" };

    public RouteListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // This calls the class that invokes the background thread using AsyncTask!
        FetchRoutesTask fetchRoutesTask = new FetchRoutesTask();
        fetchRoutesTask.execute();

    } // end onCreate()

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Create some dummy data for the ListView.
        String[] routeListData = {"A/I : Arbutus/Irvington", "A/B : Arundel/BWI Marc Line", "C : Catonsville", "D A : Downtown A", "D B : Downtown B", "H/S : Halethorpe/Satelite" };

        List<String> routeList = new ArrayList<String>(Arrays.asList(routeListData));

        //Create an ArrayAdapter.   The ArrayAdapter will take data from a source you specify, and use it to populate the ListView it is attached to.
        ArrayAdapter<String> busStopListAdapter =
                new ArrayAdapter<String>(
                        getActivity(), // The current context (the Parent Activity).
                        R.layout.list_item_generic, // The name of the layout ID.
                        R.id.list_item_generic_textview, // The ID of the text-view to populate.
                        routeList); //The ArrayList of data

        View rootView = inflater.inflate(R.layout.fragment_route_details, container, false);

        // Get a reference to the ListView, and attach this adapter to it.
        ListView listView = (ListView) rootView.findViewById(R.id.listview_busstops);
        listView.setAdapter(busStopListAdapter);

        return rootView;

        // ----------------------------------------------------------
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_route_details2, container, false);

    } // end method()

} // end Class
