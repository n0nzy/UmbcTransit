package co.ugwu.nonso.umbctransitguide.plainui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class presents to you a list of available routes to select from
 *
 * Created on       on 10/24/14.
 * @author        Ugwu Chinonso .O.
 */
public class RouteListFragment extends Fragment {

    //public static String[] mRouteList = {"A/I : Arbutus/Irvington", "A/B : Arundel/BWI Marc Line", "C : Catonsville", "D A : Downtown A", "D B : Downtown B", "H/S : Halethorpe/Satelite" };

    //protected String mAgencyId = obtainAgencyId();
    protected final String AGENCY_ID = "112";

    public RouteListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // This calls the class that invokes the background thread using AsyncTask!
        FetchRoutesTask fetchRoutesTask = new FetchRoutesTask();

        // This passes the agency ID of UMBC to the background thread of the FetchRoutesTask class.
        //fetchRoutesTask.execute(mAgencyId);
        fetchRoutesTask.execute(obtainAgencyId());

    } // end onCreate()

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Create some dummy data for the ListView.
        String[] routeListData = {"A/I : Arbutus/Irvington", "A/B : Arundel/BWI Marc Line", "C : Catonsville", "D A : Downtown A", "D B : Downtown B", "H/S : Halethorpe/Satelite" };

        // Converts the primitive array to a/an (Array)List Collection of type 'String'
        List<String> routeList = new ArrayList<String>(Arrays.asList(routeListData));

        //Create an ArrayAdapter.   The ArrayAdapter will take data from a source you specify, and use it to populate the ListView it is attached to.
        final ArrayAdapter<String> routeListAdapter =
                new ArrayAdapter<String>(
                        getActivity(), // The current context (the Parent Activity).
                        R.layout.list_item_generic, // The name of the layout ID.
                        R.id.list_item_generic_textview, // The ID of the text-view to populate.
                        routeList); //The ArrayList of data

        View rootView = inflater.inflate(R.layout.fragment_route_list, container, false);

        // Get a reference to the ListView, and attach this adapter to it.
        ListView listView = (ListView) rootView.findViewById(R.id.listview_routes);
        listView.setAdapter(routeListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickedRouteName = routeListAdapter.getItem(position);
                //int duration = Toast.LENGTH_SHORT;
                //Toast.makeText(getActivity(), clickedRoute, duration).show();

                // This calls the next Activity which is responsible for displaying details about the chosen route
                Intent intent = new Intent(getActivity(), RouteDetailsActivity.class).putExtra(Intent.EXTRA_TEXT, clickedRouteName);
                startActivity(intent);
            }
        });

        return rootView;

        // ----------------------------------------------------------
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_route_details2, container, false);

    } // end method()

    private String obtainAgencyId() {

        // UMBC's Agency ID is 112, obviously not the best way to implement this; will work on this later
        //return "112";
        return this.AGENCY_ID;
    }

} // end Class
