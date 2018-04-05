package co.ugwu.nonso.umbctransitguide.plainui;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by crypton on 2/16/15.
 */
public class Message {

    public static void show(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void show(String message) {
        //Toast.makeText(new Activity, message );
        //Toast.makeText(new Activity, message, Toast.LENGTH_LONG).show();
    }

    public static void log(String message) {
        final String LOG_TAG = Message.class.getSimpleName();

        Log.d(LOG_TAG, "Log Msg: " + message);
    }

}// end class
