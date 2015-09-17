package app.nbii.na.citybusapp;


import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import app.nbii.na.citybusapp.Model.AlertDialogManager;
import app.nbii.na.citybusapp.Model.ConnectionDetectors;
import app.nbii.na.citybusapp.Model.GooglePlace;
import app.nbii.na.citybusapp.Model.Place;
import app.nbii.na.citybusapp.Model.PlacesList;

public class NearByPlacesActivity extends ListActivity{

    // flag for Internet connection status
    Boolean isInternetPresent = false;

    // Connection detector class
    ConnectionDetectors cd;

    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();

    // Google Places
    // GooglePlaces googlePlaces;

    // Places List
    PlacesList nearPlaces;

    ArrayList venuesList;
    ArrayAdapter myAdapter;

    // GPS Location
    UserTracker gps;

    // Progress dialog
    ProgressDialog pDialog;

    // Places Listview
    ListView lv;

    String API_KEY = "AIzaSyBMFFkbsLDkBb7sO5NLPgW1YcFrvuFkbcI";

    // ListItems data
    ArrayList<HashMap<String, String>> placesListItems = new ArrayList<HashMap<String,String>>();

    // KEY Strings
    public static String KEY_REFERENCE = "reference"; // id of the place
    public static String KEY_NAME = "name"; // name of the place
    public static String KEY_VICINITY = "vicinity"; // Place area name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by_places);
        // start the AsyncTask that makes the call for the venus search.

        cd = new ConnectionDetectors(this);

        // Check if Internet present
        isInternetPresent = cd.isConnectingToInternet();
        if (!isInternetPresent) {
            // Internet Connection is not present
            alert.showAlertDialog(this, "Internet Connection Error",
                    "Please connect to working Internet connection", false);
            // stop executing code by return
        }

        // creating GPS Class object
        gps = new UserTracker(this);

        // check if GPS location can get
        if (gps.canGetLocation()) {
            Log.d("Your Location", "latitude:" + gps.getLatitude() + ", longitude: " + gps.getLongitude());
        } else {
            // Can't get user's current location
            alert.showAlertDialog(this, "GPS Status",
                    "Couldn't get location information. Please enable GPS",
                    false);
            // stop executing code by return
        }

        // Getting listview
        lv = (ListView)findViewById(R.id.list);

    }

    /**
     * Background Async Task to Load Google places
     * */
    class LoadPlaces extends AsyncTask<String, String, String> {

        String temp;

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(NearByPlacesActivity.this);
            pDialog.setMessage(Html.fromHtml("<b>Search</b><br/>Loading Places..."));
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting Places JSON
         */
        protected String doInBackground(String... args) {
            // creating Places class object
            //googlePlaces = new GooglePlaces();

            try {
                // Separeate your place types by PIPE symbol "|"
                // If you want all types places make it as null
                // Check list of types supported by google
                //
                String types = "cafe|restaurant"; // Listing places only cafes, restaurants

                // Radius in meters - increase this value if you don't find any places
                double radius = 1000; // 1000 meters

                // make Call to the url
                temp = makeCall("https://maps.googleapis.com/maps/api/place/search/json?location="
                        + gps.getLatitude() + "," + gps.getLongitude() + "&types=" + types +
                        "" + "&radius=1000&sensor=true&key=" + API_KEY);


                // get nearest places
                // nearPlaces = googlePlaces.search(gps.getLatitude(),
                // gps.getLongitude(), radius, types);


            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            if (temp == null) {

            } else {
                // loop through each place
                for (Place p : nearPlaces.results) {

                    HashMap<String, String> map = new HashMap<String, String>();

                    // Place reference won't display in listview - it will be hidden
                    // Place reference is used to get "place full details"
                    map.put(KEY_REFERENCE, p.reference);

                    // Place name
                    map.put(KEY_NAME, p.name);


                    // adding HashMap to ArrayList
                    placesListItems.add(map);
                }
                // list adapter
                ListAdapter adapter = new SimpleAdapter(NearByPlacesActivity.this, placesListItems,
                        R.layout.activity_near_by_places,
                        new String[]{KEY_REFERENCE, KEY_NAME}, new int[]{
                        R.id.place, R.id.list_image});

                // Adding data into listview
                lv.setAdapter(adapter);
            }
        }
    }

    public static String makeCall(String url) {

        // string buffers the url
        StringBuffer buffer_string = new StringBuffer(url);
        String replyString = "";
        // instanciate an HttpClient

        HttpClient httpclient = new DefaultHttpClient();

        // instanciate an HttpGet
        HttpGet httpget = new HttpGet(buffer_string.toString());

        try {
            // get the responce of the httpclient execution of the url
            HttpResponse response = httpclient.execute(httpget);
            InputStream is = response.getEntity().getContent();
            // buffer input stream the result
            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayBuffer baf = new ByteArrayBuffer(20);
            int current = 0;
            while ((current = bis.read()) != -1) {
                baf.append((byte) current);

            }
            // the result as a string is ready for parsing
            replyString = new String(baf.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(replyString);
        // trim the whitespaces
        return replyString.trim();
    }

    private static ArrayList<GooglePlace> parseGoogleParse(final String response) {

        ArrayList<GooglePlace> temp = new ArrayList<GooglePlace>();
        try {

            // make an jsonObject in order to parse the response
            JSONObject jsonObject = new JSONObject(response);

            // make an jsonObject in order to parse the response
            if (jsonObject.has("results")) {

                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for (int i = 0; i < jsonArray.length(); i++) {
                    GooglePlace poi = new GooglePlace();
                    if (jsonArray.getJSONObject(i).has("name")) {
                        poi.setName(jsonArray.getJSONObject(i).optString("name"));
                        poi.setRating(jsonArray.getJSONObject(i).optString("rating", " "));

                        if (jsonArray.getJSONObject(i).has("opening_hours")) {
                            if (jsonArray.getJSONObject(i).getJSONObject("opening_hours").has("open_now")) {
                                if (jsonArray.getJSONObject(i).getJSONObject("opening_hours").getString("open_now").equals("true")) {
                                    poi.setOpenNow("YES");
                                } else {
                                    poi.setOpenNow("NO");
                                }
                            }
                        } else {
                            poi.setOpenNow("Not Known");
                        }
                        if (jsonArray.getJSONObject(i).has("types")) {
                            JSONArray typesArray = jsonArray.getJSONObject(i).getJSONArray("types");

                            for (int j = 0; j < typesArray.length(); j++) {
                                poi.setCategory(typesArray.getString(j) + ", " + poi.getCategory());
                            }
                        }
                    }
                    temp.add(poi);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<GooglePlace>();
        }
        return temp;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_near_by_places, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                // do stuff, like showing settings fragment
                return true;
            case R.id.action_view_on_map:

                Intent i = new Intent(this,
                        PlaceMapsActivity.class);
                // Sending user current geo location
                i.putExtra("user_latitude", Double.toString(gps.getLatitude()));
                i.putExtra("user_longitude", Double.toString(gps.getLongitude()));

                // passing near places to map activity
                i.putExtra("near_places", nearPlaces);
                // staring activity
                startActivity(i);

                return true;
        }

        return super.onOptionsItemSelected(item); // important line
    }


}