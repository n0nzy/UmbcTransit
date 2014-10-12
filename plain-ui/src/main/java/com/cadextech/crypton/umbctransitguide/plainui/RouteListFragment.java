package com.cadextech.crypton.umbctransitguide.plainui;



import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class RouteListFragment extends Fragment {


    public RouteListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final String[] route_list = {"A / I : Arbutus/Irvington", "A / B : Arundel/BWI Marc Line", "C : Catonsville", "D A : Downtown A", "D B : Downtown B", "H / S : Halethorpe/Satelite"};

        //final String[] route_list = {"C : Catonsville", "D A : Downtown A", "A / B : Arundel/BWI Marc Line", "H / S : Halethorpe/Satelite"};

        LinearLayout ll = new LinearLayout(getActivity());
        ll.setOrientation(LinearLayout.VERTICAL);
        //ll.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        //ll.setGravity(Gravity.CENTER);


        for (int i=0; i<route_list.length; i++) {

            TextView routeName = new TextView(getActivity());
            routeName.setText(route_list[i]);
            routeName.setPadding(45, 35, 0, 35);
            routeName.setTextSize(17);

            final int finalI = i;
            routeName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), RouteActivity.class);
                    intent.putExtra("route_name", route_list[finalI]);
                    getActivity().startActivity(intent);
                }
            });

            ll.addView(routeName);

        } // end for loop

        return ll;

    } // end onCreateView()


} // end RouteListFragment
