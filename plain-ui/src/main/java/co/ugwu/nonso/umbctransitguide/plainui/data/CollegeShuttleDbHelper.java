package co.ugwu.nonso.umbctransitguide.plainui.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import co.ugwu.nonso.umbctransitguide.plainui.data.CollegeShuttleContract.AgencyEntry;
import co.ugwu.nonso.umbctransitguide.plainui.data.CollegeShuttleContract.RoutesEntry;
import co.ugwu.nonso.umbctransitguide.plainui.data.CollegeShuttleContract.StopsEntry;
import co.ugwu.nonso.umbctransitguide.plainui.data.CollegeShuttleContract.ArrivalsEntry;

/**
 * CollegeShuttleDbHelper: creates and updates the only database for this Project ( CollegeShuttle App )
 *
 * Licensed under The MIT License
 * Redistributions of files should please contain the above copyright notice.
 *
 * @author       Ugwu Chinonso .O.
 * @version      1.0                 (current version number of program)
 * @since        2014-11-22          (the version of the package this class was first added to)
 * Last Update   2014-11-22
 */
public class CollegeShuttleDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "collegeShuttle.db";

    public CollegeShuttleDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // Create a table to the agency ID for the school...Should only contain a single entry
        final String SQL_CREATE_AGENCY_TABLE = "CREATE TABLE " + AgencyEntry.TABLE_NAME + " (" +
                AgencyEntry._ID + " INTEGER PRIMARY KEY," +
                AgencyEntry.COLUMN_AGENCY_ID + " INTEGER UNIQUE NOT NULL, " +
                AgencyEntry.COLUMN_AGENCY_NAME + " TEXT NULL, " +
                AgencyEntry.COLUMN_AGENCY_SHORT_NAME + " TEXT UNIQUE NOT NULL, " +
                AgencyEntry.COLUMN_AGENCY_LONG_NAME + " TEXT UNIQUE NOT NULL, " +
                AgencyEntry.COLUMN_AGENCY_LANGUAGE + " TEXT NOT NULL, " +
                AgencyEntry.COLUMN_AGENCY_COORD_LAT + " TEXT NOT NULL, " +
                AgencyEntry.COLUMN_AGENCY_COORD_LONG + " TEXT NOT NULL, " +
                AgencyEntry.COLUMN_AGENCY_TIMEZONE + " TEXT NOT NULL, " +
                AgencyEntry.COLUMN_AGENCY_URL + " TEXT NOT NULL, " +
                "UNIQUE (" + AgencyEntry.COLUMN_AGENCY_ID +") ON CONFLICT IGNORE"+" );";

        // Create a table to hold [bus] routes.
        final String SQL_CREATE_ROUTES_TABLE = "CREATE TABLE " + RoutesEntry.TABLE_NAME + " (" +
                RoutesEntry._ID + " INTEGER PRIMARY KEY," +
                RoutesEntry.COLUMN_ROUTE_ID + " INTEGER UNIQUE NOT NULL, " +
                RoutesEntry.COLUMN_ROUTE_SHORT_NAME + " TEXT UNIQUE NOT NULL, " +
                RoutesEntry.COLUMN_ROUTE_LONG_NAME + " TEXT UNIQUE NOT NULL, " +
                RoutesEntry.COLUMN_ROUTE_DESC + " TEXT NULL, " +
                RoutesEntry.COLUMN_ROUTE_IS_ACTIVE + " TEXT NOT NULL, " +
                RoutesEntry.COLUMN_ROUTE_IS_HIDDEN + " TEXT NOT NULL, " +
                RoutesEntry.COLUMN_ROUTE_TYPE + " TEXT NOT NULL, " +
                RoutesEntry.COLUMN_ROUTE_THEME_COLOR + " TEXT NOT NULL, " +
                RoutesEntry.COLUMN_ROUTE_TEXT_COLOR + " TEXT NOT NULL, " +
                "UNIQUE (" + RoutesEntry.COLUMN_ROUTE_ID +") ON CONFLICT IGNORE"+" );";

        // Create a table to hold [bus] stops.
        // A stop consists of the string supplied in the stop name, the stop code, its latitude and longitude
        final String SQL_CREATE_STOPS_TABLE = "CREATE TABLE " + StopsEntry.TABLE_NAME + " (" +
                StopsEntry._ID + " INTEGER PRIMARY KEY," +
                StopsEntry.COLUMN_STOP_ID + " INTEGER UNIQUE NOT NULL, " +
                StopsEntry.COLUMN_STOP_NAME + " TEXT UNIQUE NOT NULL, " +
                StopsEntry.COLUMN_STOP_CODE + " TEXT NOT NULL, " +
                StopsEntry.COLUMN_COORD_LAT + " REAL NOT NULL, " +
                StopsEntry.COLUMN_COORD_LONG + " REAL NOT NULL, " +
                "UNIQUE (" + StopsEntry.COLUMN_STOP_CODE +") ON CONFLICT IGNORE"+" );";


        /* Create a table to hold schedule info; requires the following info:
                - stop_id   from 'Stops'  table
                - route_id  from 'Routes' table
                - agency_id from 'Agency' table
        */
        final String SQL_CREATE_ARRIVALS_TABLE = "CREATE TABLE " + CollegeShuttleContract.ArrivalsEntry.TABLE_NAME + " (" +
                // Why AutoIncrement here, and not above?
                // Unique keys will be auto-generated in either case.
                // But for the estimated arrivals Schedule, it's reasonable to assume the user will want information
                // for a certain date and all dates *following*, so the schedule data should be sorted accordingly.

                ArrivalsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +

                // the ID of the stop, route, and agency entries associated with this arrival-time data
                ArrivalsEntry.COLUMN_SERVICE_STOP_ID + " INTEGER NOT NULL, " +
                ArrivalsEntry.COLUMN_SERVICE_ROUTE_ID + " INTEGER NOT NULL, " +
                ArrivalsEntry.COLUMN_SERVICE_AGENCY_ID + " INTEGER NOT NULL, " +

                // Arrival time in DATE-TIME format...most important field in db
                ArrivalsEntry.COLUMN_ARRIVAL_AT + " TEXT NOT NULL, " +

                // Set up the stop_id (location) column as a foreign key to [Bus] Stops table.
                " FOREIGN KEY (" + ArrivalsEntry.COLUMN_SERVICE_STOP_ID + ") REFERENCES " + StopsEntry.TABLE_NAME + " (" + StopsEntry.COLUMN_STOP_ID + "), " +

                // Set up the route_id column as a foreign key to Routes table.
                " FOREIGN KEY (" + ArrivalsEntry.COLUMN_SERVICE_ROUTE_ID + ") REFERENCES " + RoutesEntry.TABLE_NAME + " (" + RoutesEntry.COLUMN_ROUTE_ID + "), " +

                // Set up the agency_id column as a foreign key to Agency table.
                " FOREIGN KEY (" + ArrivalsEntry.COLUMN_SERVICE_AGENCY_ID + ") REFERENCES " + AgencyEntry.TABLE_NAME + " (" + AgencyEntry.COLUMN_AGENCY_ID + "), " +

                // To ensure the application has just one schedule entry per arrival-time per location (stopId) per route (routeId),
                // i created a UNIQUE constraint with REPLACE strategy, which replaces duplicate entries of these 3 fields that occur per record
                //  " UNIQUE (" + ArrivalsEntry.COLUMN_ARRIVAL_AT + ", " + ArrivalsEntry.COLUMN_SERVICE_STOP_ID + ") ON CONFLICT REPLACE);";
                " UNIQUE (" + ArrivalsEntry.COLUMN_ARRIVAL_AT + ", " + ArrivalsEntry.COLUMN_SERVICE_STOP_ID + ", "+ ArrivalsEntry.COLUMN_SERVICE_ROUTE_ID + ") ON CONFLICT REPLACE);";

        sqLiteDatabase.execSQL(SQL_CREATE_AGENCY_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_ROUTES_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_STOPS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_ARRIVALS_TABLE);

    }// end onCreate()

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

        // This database is only a cache for online data, so its upgrade policy is to simply to discard the data and start over
        // Note that this only fires if you change the version number for your database.
        // It does NOT depend on the version number for your application.
        // If you want to update the schema without wiping data, commenting out the next 4 lines should be your top priority before modifying this method.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + AgencyEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RoutesEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + StopsEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ArrivalsEntry.TABLE_NAME);

        onCreate(sqLiteDatabase);

    }// end onUpgrade()

}//end Class
