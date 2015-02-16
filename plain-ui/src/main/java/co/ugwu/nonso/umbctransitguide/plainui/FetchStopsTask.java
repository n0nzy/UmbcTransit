package co.ugwu.nonso.umbctransitguide.plainui;

import android.os.AsyncTask;

/**
 * FetchStopsTask: A class that queries either a webservice or database to obtain the available stops for a chosen route...
 *
 * AsyncTask: A Class that executes a compute-heavy task in a background thread
 * AsyncTask<Generic:1, Generic:2, Generic:3>
 *     Generic:1 : the type you want to pass to AsyncTask
 *     Generic:2 : .....
 *     Generic:3 : the type that it returns
 *
 * Created on     on 11/21/14.
 * @author        Ugwu Chinonso .O.
 */
public class FetchStopsTask extends AsyncTask<String, Void, String[]> {

    private final boolean DEBUG_FLAG = true;
    private final String DEBUG_TAG = FetchRoutesTask.class.getSimpleName();

    /**
     * This method takes whatever value is passed to it, and executes code to run a task in a background thread!
     * The first value passed to FetchStopsTask.execute() is the first item (params[0]) in the params[] array!
     *
     * @param params
     * @return
     */
    @Override
    protected String[] doInBackground(String... params) {


        return null;

    }// end method

}// end class FetchStopsTask