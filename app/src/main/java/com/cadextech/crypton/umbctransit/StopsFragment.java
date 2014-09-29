package com.cadextech.crypton.umbctransit;

import android.app.FragmentManager;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class StopsFragment extends Fragment {

    public StopsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void enterClicked() {
        Intent intent = new Intent(getActivity(), SimpleListViewProgrammActivity.class);
        getActivity().startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_stops, container, false);

    }

} //End StopsFragment