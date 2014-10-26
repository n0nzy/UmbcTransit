package co.ugwu.nonso.umbctransitguide.plainui;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * AsyncTask: A Class that executes a compute-heavy task in a background thread
 * In this case, it connects to a web-service to obtain the available routes...
 *
 * AsyncTask<Generic:1, Generic:2, Generic:3>
 *     Generic:1 : the type you want to pass to AsyncTask
 *     Generic:3 : the type that it returns
 *
 * Created on       on 10/24/14.
 * @author        Ugwu Chinonso .O.
 */
public class FetchRoutesTask extends AsyncTask<String, Void, String[]> {

    private final boolean DEBUG_FLAG = true;
    private final String DEBUG_TAG = FetchRoutesTask.class.getSimpleName();

    /**
     * This method takes whatever value is passed to it, and executes code to run a task in a background thread!
     * The first value passed to FetchRoutesTask.execute() is the first item (params[0]) in the params[] array!
     *
     * @param params
     * @return
     */
    @Override
    protected String[] doInBackground(String... params) {

        // These two need to be declared outside the try/catch so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String forecastJsonStr = null;

        //Extracting the 1st parameter passed to the background thread: In this case, it will be the agency ID.
        String agencyId = params[0];

        try {

            // Construct the URL for the Web-Service....
            final String ORIG_URL = "https://transloc-api-1-2.p.mashape.com/routes.json?agencies=112%2C16&callback=call&geo_area=39.256116%2C-76.710749%7C3218.69";

            final String BASE_URL = "https://transloc-api-1-2.p.mashape.com/agencies.json?";
            final String AGENCY_PARAM = "agencies";
            final String CALLBACK_PARAM = "callback";
            final String GEO_AREA_PARAM = "geo_area";

            Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                    .appendQueryParameter(AGENCY_PARAM, agencyId)
                    .appendQueryParameter(CALLBACK_PARAM, "call")
                    .appendQueryParameter(GEO_AREA_PARAM, "39.256116%2C-76.710749%7C3218.69")
                    .build();

            URL url2 = new URL(builtUri.toString());

            if (DEBUG_FLAG) Log.d(DEBUG_TAG, "Built URI: " + builtUri.toString());

            // Construct the URL for the OpenWeatherMap query
            // Possible parameters are available at OWM's forecast API page, at : http://openweathermap.org/API#forecast
            //URL url1 = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7");
            URL url = new URL("http://twitter.com");

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            forecastJsonStr = buffer.toString();
            if (DEBUG_FLAG) Log.d(DEBUG_TAG, "Obtained Data: "+forecastJsonStr);

        } catch (IOException e) {

            if (DEBUG_FLAG) Log.e(DEBUG_TAG, "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attempting to parse it.
            return null;

        } finally {

            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    if (DEBUG_FLAG) Log.e(DEBUG_TAG, "Error closing stream", e);
                }
            }
        } // end finally

        // This will only happen if there was an error getting or parsing the values OR there are no available routes!
        return null;

    } // end doInBackground()

    /**
     * <p>Runs on the UI thread after #doInBackground(). The specified result is the value returned by #doInBackground().</p>
     *
     * <p>This method won't be invoked if the task was cancelled.</p>
     *
     * @param result    The result of the operation computed by doInBackground().
     * @see #onPreExecute
     * @see #doInBackground
     * @see #onCancelled(Object)
     */
    @Override
    protected void onPostExecute(String[] result) {

        //super.onPostExecute(result);

        if (result != null) {
            ArrayAdapter<String> routeListAdapter = null;
            //myResultAdapter.clear();
            for(String routeNameStr : result) {
                routeListAdapter.add(routeNameStr);
            }
            // New data is back from the server.  Hooray!
        }

    } // end method()

} //end class FetchRoutesTask