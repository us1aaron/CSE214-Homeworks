/**
 * Aaron Li
 * 113319376
 * aaron.li@stonybrook.edu
 * Assignment 7
 * CSE214
 * REC02 - Felix Rieg-Baumhauer
 */
/**
 * This is the NEoDataBase class
 * In this class, the program will access the NASA database in order to retreive information
 * API_Key is the unqiue key for my user
 * API_root is the root url for accessing the NASA database
 * NearEarthObjects are stored in the arraylist list
 *
 */

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;
import org.json.*;
public class NeoDataBase {
    public static final String API_KEY = "yQ2xIqlUXC65GNBGgJgKy8MpnO8IGuNgfru7w8ub"; //MAKE ONE
    public static final String API_ROOT = "https://api.nasa.gov/neo/rest/v1/neo/browse?"; //CHECK
    public NeoDataBase(){}
    ArrayList<NearEarthObject> list = new ArrayList<NearEarthObject>();

    /**
     * Builds a query URL given a page number
     * @param pagenumber
     * page number the user wants to access
     * Precondition:
     * pagnumber > 0 && pagenumber < 716
     * @return
     * string url
     * @exception
     * throws IllegalArgumentException if pagenumber is not in valid range
     */
    public String buildQueryURL(int pagenumber){
        return API_ROOT + "page=" + pagenumber + "&api_key=" + API_KEY;
    }

    /**
     * Opens a connection to the data source indicated by queryURL and adds all NearEarthObjects found in the dataset
     * @param url
     * url to be accessed to retrieve information
     * Precondition:
     * queryURL is a non-null string representing a valid API request to the NASA NeoW service
     * Postcondition:
     * All NearEarthObject records returned have been added to the database, or else a IllegalArgumentException has been thrown
     * @exception
     * throws IllegalArguemntException if queryURL is null or cound not be resolved by the server
     */
    public void addAll(String url){
        String req = url;
        try {
            URL getRequest = new URL(req); //Creates a URL object from the URL string
            JSONTokener tokener = new JSONTokener(getRequest.openStream());
            JSONObject root = new JSONObject(tokener);
            //System.out.println(root.getJSONArray("near_earth_objects").toString());
            JSONArray a = root.getJSONArray("near_earth_objects");
            for(int i=0;i<a.length();i++){
                JSONObject obj = (JSONObject) a.get(i);
                long close_approach_date = 0;
                double miss_dist = 0;
                String orbit_body = "";
                int id = Integer.parseInt((String)(obj.get("neo_reference_id")));
                String name = (String)obj.get("name");
                double abs_mag = (Double)(obj.get("absolute_magnitude_h"));
                double min_diam = obj.getJSONObject("estimated_diameter").getJSONObject("kilometers").getDouble("estimated_diameter_min");
                double max_diam = obj.getJSONObject("estimated_diameter").getJSONObject("kilometers").getDouble("estimated_diameter_max");
                boolean danger = (Boolean)obj.get("is_potentially_hazardous_asteroid");
                JSONArray arr = obj.getJSONArray("close_approach_data");
                for (int k = 0; k < arr.length(); k++) {
                    JSONObject obj2 = (JSONObject) arr.get(k);
                    close_approach_date = arr.getJSONObject(i).getLong("epoch_date_close_approach");
                    miss_dist = obj2.getJSONObject("miss_distance").getDouble("kilometers");
                    orbit_body = obj2.getString("orbiting_body");
                }
                NearEarthObject newObj = new NearEarthObject(id, name, abs_mag, min_diam, max_diam, danger, close_approach_date, miss_dist, orbit_body);
                list.add(newObj);
            }
        } catch(IOException ex) {
           System.out.println();
        } catch(JSONException ex) {
           System.out.println();
        }
    }

    /**
     * Sorts the database using the specified Comparator of NearEarthObjects
     * @param comp
     * Comparator of NearEarthObjects which will be used to sort the database
     * Precondiiton:
     * comp is not null
     * Postcondition:
     * The database has been sorted based on the order specified by the inidcated Comparator of NearEarthObjects
     * @exception
     * throws IllegalArgumentException if comp is null
     */
    public void sort(Comparator<NearEarthObject> comp){
        Collections.sort(list, comp);
    }

    /**
     * Displays the database in a neat, tabular form, listing all member variables for each NearEarthObject
     * Precondiiton:
     * NeoDatabase is initialized and not null
     * Postcondiiton:
     * The table has been printed but reamins unchanged
     */
    public void printTable(){
        NearEarthObject temp;
        System.out.println("ID       |Name        |Mag. |Diameter  |Danger |Close Date  |Miss Dist |Orbits");
        System.out.println("================================================================================");
        for(int i = 0; i < list.size(); ++i) {
            temp = list.get(i);
            DecimalFormat format = new DecimalFormat("0.#");
            System.out.print(String.format("%-9d", temp.getReference_ID()) + "|");
            System.out.print(String.format("%-12s", temp.getName()) + "|");
            System.out.print(String.format("%-5s", (format.format(temp.getAbsoluteMagnitude()))) + "|");
            System.out.print(String.format("%-10f", temp.getAverageDiameter()) + "|");
            System.out.print(String.format("%-7b", temp.getisDangerous()) + "|");
            System.out.print(String.format("%-12s", temp.getDate()) + "|");
            System.out.print(String.format("%-10d", (int)(temp.getMissDistance())) + "|");
            System.out.println(String.format("%-8s", temp.getOrbitingBody()));
        }
    }
}
