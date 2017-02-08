package co.ugwu.nonso.umbctransitguide.plainui.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Custom class i created for interacting directly with the SQLite DB...does NOT make use of Content Providers
 *
 * Created by crypton on 2/16/15.
 */
public class ShuttleDbAdapter {

    CollegeShuttleDbHelper myDbHelper;

    public long insertRoutes(String name, String desc) {

        SQLiteDatabase db = myDbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CollegeShuttleContract.RoutesEntry.COLUMN_ROUTE_SHORT_NAME, name);
        contentValues.put(CollegeShuttleContract.RoutesEntry.COLUMN_ROUTE_DESC, desc);

        long id = db.insert(CollegeShuttleContract.RoutesEntry.TABLE_NAME, null, contentValues);
        db.close();
        return id;

    }

    public String getAllRoutes() {

        SQLiteDatabase db = myDbHelper.getReadableDatabase();

        String[] columns = {
                CollegeShuttleContract.RoutesEntry.COLUMN_ROUTE_SHORT_NAME,
                CollegeShuttleContract.RoutesEntry.COLUMN_ROUTE_LONG_NAME,
                CollegeShuttleContract.RoutesEntry.COLUMN_ROUTE_DESC
        };

        // Returns a cursor object that contains the result set (in tabular form)
        Cursor cursor = db.query(CollegeShuttleContract.RoutesEntry.TABLE_NAME, columns, null, null, null, null, null);

        StringBuffer buffer = new StringBuffer();

        while (cursor.moveToNext()) {
            int primaryKey = cursor.getInt(0); // Note: This is a quick hack! The very first column which should be the _id field

            // Similar to cursor.getInt() above, but instead returns the String COLUMN that matches the route.short_name field in the route table
            String shortName = cursor.getString(cursor.getColumnIndex(CollegeShuttleContract.RoutesEntry.COLUMN_ROUTE_SHORT_NAME));

            // Similar to cursor.getInt() above, but instead returns the String COLUMN that matches the route.long_name field in the route table
            String longName = cursor.getString(cursor.getColumnIndex(CollegeShuttleContract.RoutesEntry.COLUMN_ROUTE_LONG_NAME));

            buffer.append(primaryKey+" "+shortName+" "+longName+"\n");

        } // end while

        return buffer.toString();

    } // end method

    public long insertStops(long routeId) {
        return -1;
    }

    public long insertStops(String routeLongName) {
        return -1;
    }

    public String getAllStops(String routeLongName) {
        return "-1";
    }

    public String getStopDetails(String stopLongName) {
        return "-1";
    }

} // end class
