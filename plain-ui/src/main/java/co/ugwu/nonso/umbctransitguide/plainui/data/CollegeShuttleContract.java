package co.ugwu.nonso.umbctransitguide.plainui.data;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * CollegeShuttleContract: Used for Database Definitions and Content Provider Definitions
 *
 * Licensed under The MIT License
 * Redistributions of files should please contain the above copyright notice.
 *
 * @author       Ugwu Chinonso .O.
 * @version      1.0                 (current version number of program)
 * @since        2014-11-22          (the version of the package this class was first added to)
 * Last Update   2014-11-22
 */
public class CollegeShuttleContract {


    // The "Content authority" is a name for the entire content provider, similar to the relationship between a domain name and its website.
    // A convenient string to use for the content authority is the package name for the app, which is guaranteed to be unique on the device.
    public static final String CONTENT_AUTHORITY = "co.ugwu.nonso.umbctransitguide";

    // Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact the content provider.
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    // Possible paths (appended to base content URI for possible URI's)
    // For instance, content://com.example.android.umbctransit.app/routes/ is a valid path for looking at route data.
    // content://com.example.android.umbctransit.app/givemeroot/ will fail,
    // as the ContentProvider hasn't been given any information on what to do with "givemeroot".
    // At least, let's hope not.
    public static final String PATH_AGENCY = "agency";
    public static final String PATH_ROUTES = "routes";
    public static final String PATH_STOPS = "stops";
    public static final String PATH_ARRIVALS = "arrivals";
    //public static final String PATH_UPDATES = "updates";  //Supposed to hold values that determines if new content exists on custom web-service

    // Format used for storing dates in the database.  Also used for converting those strings back into date objects for comparison/processing.
    public static final String DATE_FORMAT = "yyyyMMdd";


    /**
     * Converts Date class to a string representation, used for easy comparison and database lookup.
     *
     * @param date The input date
     * @return a DB-friendly representation of the date, using the format defined in DATE_FORMAT.
     */
    public static String getDbDateString(Date date){
        // Because the API returns a unix timestamp (measured in seconds),
        // it must be converted to milliseconds in order to be converted to valid date.
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date);
    }

    /**
     * Helper Function: Converts a dateText to a long Unix time representation
     *
     * @param dateText the input date string
     * @return the Date object
     */
    public static Date getDateFromDb(String dateText) {
        SimpleDateFormat dbDateFormat = new SimpleDateFormat(DATE_FORMAT);
        try {
            return dbDateFormat.parse(dateText);
        } catch ( ParseException e ) {
            e.printStackTrace();
            return null;
        }
    } // end method

    /* Inner class that defines the table contents of the Agency table. Here Agency means the School's Transit System.  Should contain ONLY one entry: The AgencyID of the school  */
    public static final class AgencyEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_AGENCY).build();

        //These return special MIME type prefixes that indicate whether the URI returns a single item, list of items, or a directory
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_AGENCY;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_AGENCY;

        // Table name
        public static final String TABLE_NAME = "agency";

        // Unique ID obtained from web-service....different from Primary key in local DB
        public static final String COLUMN_AGENCY_ID = "agency_id"; // 112

        // Human-Friendly info about the Agency
        public static final String COLUMN_AGENCY_NAME = "name"; // Arbutus
        public static final String COLUMN_AGENCY_SHORT_NAME = "short_name"; // A I
        public static final String COLUMN_AGENCY_LONG_NAME = "long_name"; // Arbutus

        // Location information associated with school (referred to as Agency)
        public static final String COLUMN_AGENCY_COORD_LONG = "coord_longitude"; //-78.68218
        public static final String COLUMN_AGENCY_COORD_LAT = "coord_latitude"; //35.78061

        // Meta-data associated with the School (referred to as Agency)
        public static final String COLUMN_AGENCY_LANGUAGE = "language"; //en
        public static final String COLUMN_AGENCY_URL = "url"; //http://transit.umbc.edu/arbutus
        public static final String COLUMN_AGENCY_TIMEZONE = "time_zone"; //America/New York


        //returns data for a single item using the Primary key
        public static Uri buildAgencyUri(long _id) {
            return ContentUris.withAppendedId(CONTENT_URI, _id);
        }

    }// end class AgencyEntry

    /* Inner class that defines the table contents of the location table */
    public static final class RoutesEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_ROUTES).build();

        //These return special MIME type prefixes that indicate whether the URI returns a single item, list of items, or a directory
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_ROUTES;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_ROUTES;

        // Table name
        public static final String TABLE_NAME = "routes";

        // Unique ID obtained from web-service....different from Primary key in local DB
        public static final String COLUMN_ROUTE_ID = "route_id"; // value such as: 4003694

        // Human-Friendly info about the Routes
        public static final String COLUMN_ROUTE_SHORT_NAME = "short_name";
        public static final String COLUMN_ROUTE_LONG_NAME = "long_name";
        public static final String COLUMN_ROUTE_DESC = "description";

        // Availability STATUS of this route
        public static final String COLUMN_ROUTE_IS_ACTIVE = "is_active"; // default: false
        public static final String COLUMN_ROUTE_IS_HIDDEN = "is_hidden"; // default: false

        public static final String COLUMN_ROUTE_TYPE = "route_type"; // this should normally be 'bus'

        //public static final String COLUMN_ROUTE_AGENCY_ID = "agency_id"; // default: 112

        // Meta-Data associated with each Route
        public static final String COLUMN_ROUTE_THEME_COLOR = "theme_color"; // value such as: e7b908
        public static final String COLUMN_ROUTE_TEXT_COLOR = "text_color"; // value such as: dd9ddd


        //returns data for a single item using the Primary key
        public static Uri buildRouteUri(long _id) {
            return ContentUris.withAppendedId(CONTENT_URI, _id);
        }

    }// end (inner) class RoutesEntry


    /* Inner class that defines the table contents of the location table */
    public static final class StopsEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_STOPS).build();

        //These return special MIME type prefixes that indicate whether the URI returns a single item, list of items, or a directory
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_STOPS;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_STOPS;

        // Table name
        public static final String TABLE_NAME = "stops";

        // Unique ID obtained from web-service....different from Primary key in local DB
        public static final String COLUMN_STOP_ID = "stop_id"; // value such as: 4128450

        // Stop Name for this stop name
        public static final String COLUMN_STOP_NAME = "name";

        // Stop Code for this Stop
        public static final String COLUMN_STOP_CODE = "code";

        // In order to uniquely pinpoint the location on the map when we launch the map intent, we store the latitude and longitude as returned by web-service.
        public static final String COLUMN_COORD_LAT  = "coord_lat";
        public static final String COLUMN_COORD_LONG = "coord_long";


        //returns data for a single item using the Primary key.
        public static Uri buildStopUri(long _id) {
            return ContentUris.withAppendedId(CONTENT_URI, _id);
        }

    }// end (inner) class StopsEntry


    /* Inner class that defines the schedule...holds contents of the 'estimated arrivals' table...Most important table in DB! */
    public static final class ArrivalsEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_ARRIVALS).build();

        //These return special MIME type prefixes that indicate whether the URI returns a single item, list of items, or a directory
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_ARRIVALS;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_ARRIVALS;

        // Table name
        public static final String TABLE_NAME = "arrivals";

        // Unique ID obtained from web-service....different from Primary key in local DB
        public static final String COLUMN_SERVICE_STOP_ID = "service_stop_id"; // value such as: 4128450

        // Unique ID obtained from web-service....different from Primary key in local DB
        public static final String COLUMN_SERVICE_ROUTE_ID = "service_route_id"; // value such as: 4003694

        // Estimated arrival time given the stopId and routeId...Most important field in this table!
        public static final String COLUMN_ARRIVAL_AT = "arrival_at";

        // Unique ID obtained from web-service....different from Primary key in local DB
        public static final String COLUMN_SERVICE_AGENCY_ID = "service_agency_id"; // 112


    }// end (inner) class ArrivalsEntry


}//end class CollegeShuttleContract