package co.ugwu.nonso.umbctransitguide.plainui;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A Class that executes a background task to execute a task....
 *
 * Created on       on 10/24/14.
 * @author        Ugwu Chinonso .O.
 */
public class FetchRoutesTask extends AsyncTask<Void, Void, Void> {

    private final boolean DEBUG_FLAG = true;
    private final String DEBUG_TAG = FetchRoutesTask.class.getSimpleName();

    @Override
    protected Void doInBackground(Void... params) {

        // These two need to be declared outside the try/catch so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String forecastJsonStr = null;

        try {

            // Construct the URL for the OpenWeatherMap query
            // Possible parameters are avaiable at OWM's forecast API page, at : http://openweathermap.org/API#forecast
            //URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7");
            URL url = new URL("http://google.com");

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
        return null;

    } // end doInBackground()

} //end class FetchRoutesTask