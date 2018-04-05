package co.ugwu.nonso.umbctransitguide.plainui;

/**
 * Created by crypton on 10/24/14.
 */

public class Route {

    private int id;
    private String shortName;
    private String longName;
    private boolean isActive = false;


    public Route(String longName) {
        this.longName = longName;
    }

    public Route(int id) {
        this.id = id;
    }

    /**
     * Returns a list of all stops associated with a Route
     * @return String[]
     */
    public String[] obtainStops() {

        String[] stopsNone = { "No stops found" };
        String[] stopsArbutus = { "Commons Dr. & Park Rd.", "Park Rd. & Poplar Ave.", "Hilltop Cir. & Commons Dr.", "Administration Dr. Bus Shelter", "Hilltop Cir. & Hilltop Rd.", "Hilltop Cir. & Walker Ave.", "Hilltop Cir. & Center Rd.", "Poplar Ave. & Stadium Lot", "TRC @ Linden Ave", "Westland Blvd. & Circle Dr.", "Westland Blvd. & Courtney Rd.", "Maiden Choice La. & Westland Blvd", "Maiden Choice La. & Warren Tree", "Maiden Choice La. & Wilkens Ave", "Maiden Choice La. & Grouse Ct", "Maiden Choice La. & Symmington Aven."};
        String[] stopsArundel = { "Commons Dr. & Park Rd.", "Park Rd. & Poplar Ave.", "Hilltop Cir. & Commons Dr.", "BWI Marc Station", "Arundel Mills Mall Visitor Entrace #5", "BWI Marc Station", "Administration Dr. Bus Shelter", "Hilltop Circle & Hilltop Rd." };
        String[] stopsCatonsville = { "Commons Dr. & Park Rd.", "Park Rd. & Poplar Ave.", "Hilltop Cir. & Commons Dr.", "Administration Dr. Bus Shelter", "Rolling Rd @YMCA", "Rolling Rd @Valley Rd (CCBC)", "Catonsville High @Bloomsbury Ave", "Mellor Ave & Bloomsbury Ave" };
        String[] stopsDowntownA = { "Commons Dr. & Park Rd", "Park Rd. & Poplar Ave.", "Hilltop Cir. & Commons Dr.", "Greyhound Station @ Haines","MLK Blvd & Pratt St (UMB)", "Green St & Fayette St", "Green St & Lombard St (UMB)" };

        if ( this.longName.toLowerCase().contains("Arbutus".toLowerCase()) ) {
            return stopsArbutus;
        }
        if ( this.longName.contains("Arundel") ) {
            return stopsArundel;
        }
        if ( this.longName.contains("Catonsville") ) {
            return stopsCatonsville;
        }
        if ( this.longName.contains("Downtown") ) {
            return stopsDowntownA;
        }

        return stopsNone;

    } // end method()

} //end class
