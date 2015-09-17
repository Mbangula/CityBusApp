package app.nbii.na.citybusapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class NearBusActivity extends FragmentActivity implements LocationListener {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private Double latitude = 0.0;
    private Double longitude = 0.0;

    static final String TAG = "CityBus";
    private static final long MIN_TIME = 1 * 60 * 1000; //1
    private LocationManager lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_bus);

        if(checkPlayServices()){

            //Setup map
            setUpMapIfNeeded();
            //Add markers
            setUpMap();


            lm = (LocationManager) getSystemService(LOCATION_SERVICE);
            //String provider = lm.getBestProvider(new Criteria(), true);
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME, 0, this);

            /*if (provider == null) {
                onProviderDisabled(provider);
            }*/

        }else{
            Toast.makeText(getApplicationContext(), "This device is not supported", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,HomeActivity.class);
            startActivity(i);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME, 0, this);
        setUpMapIfNeeded();
    }

    @Override
    protected void onPause() {
        super.onPause();
        lm.removeUpdates(this);
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {

        LatLng B1 = new LatLng(-22.53233268, 17.04952635);
        LatLng B2 = new LatLng(-22.53233268, 17.04952635);
        LatLng B3 = new LatLng(-22.527984619140625, 17.053117752075195);
        LatLng B4 = new LatLng(-22.53233268, 17.04952635);
        LatLng B5 = new LatLng(-22.314266204833984, 17.023405075073242);
        LatLng B6 = new LatLng(-22.524642944335938, 17.040145874023438);
        LatLng B7 = new LatLng(-22.517393112182617, 17.04051399230957);
        LatLng B8 = new LatLng(-22.514413833618164, 17.04323387145996);
        LatLng B9 = new LatLng(-22.515127182006836, 17.052745819091797);
        LatLng B12 = new LatLng(-22.52018082, 17.06271082);
        LatLng B14 = new LatLng(-22.52295221, 17.05579);
        LatLng B15 = new LatLng(-22.52600274, 17.05453216);
        LatLng B16 = new LatLng(-22.53233268, 17.04952635);
        LatLng B17 = new LatLng(-22.53368331, 17.06628985);
        LatLng B18 = new LatLng(-22.52789912, 17.05629283);
        LatLng B19 = new LatLng(-22.52652169, 17.04810573);
        LatLng B20 = new LatLng(-22.52210988, 17.03758658);
        LatLng B38 = new LatLng(-22.54337011, 17.03453975);
        LatLng B47 = new LatLng(-22.57762163, 17.04837952);
        LatLng B49 = new LatLng(-22.53233268, 17.04952635);
        LatLng B64 = new LatLng(-22.60725863, 17.06908581);
        LatLng B67 = new LatLng(-22.61358161, 17.06009536);
        LatLng B124 = new LatLng(-22.5667843, 17.08695917);
        LatLng B125 = new LatLng(-22.571897506713867, 17.088489532470703);
        LatLng B126 = new LatLng(-22.577430725097656, 17.0915470123291);
        LatLng B127 = new LatLng(-22.581628799438477, 17.092727661132812);
        LatLng B131 = new LatLng(-22.5667973, 17.10174627);
        LatLng B132 = new LatLng(-22.5617628, 17.09813455);
        LatLng B135 = new LatLng(-22.54549719, 17.07521193);
        LatLng B137 = new LatLng(-22.569366455078125, 17.0822696685791);
        LatLng B141 = new LatLng(-22.57950439, 17.08178963);
        LatLng B142 = new LatLng(-22.57576153, 17.0810247);
        LatLng B143 = new LatLng(-22.564250946044922, 17.080936431884766);
        LatLng B144 = new LatLng(-22.53233268, 17.04952635);
        LatLng B145 = new LatLng(-22.53233268, 17.04952635);
        LatLng B146 = new LatLng(-22.53233268, 17.04952635);
        LatLng Me = new LatLng(latitude, longitude);

        /*

        mMap.setMyLocationEnabled(true);

        /*mMap.addMarker(new MarkerOptions()
                .position(Me)
                .title("Your are here"))
                .setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)); */

       /* mMap.addMarker(new MarkerOptions()
                .title("B1")
                .snippet("Single Quarters Engine Service Bus Stop")
                .position(B4)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_bus)));

        mMap.addMarker(new MarkerOptions()
                .title("B2")
                .snippet("Single Quarters Engine Service Bus Stop")
                .position(B4)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_bus))); */

        mMap.addMarker(new MarkerOptions()
                .title("B3")
                .snippet("Herero Bus Stop")
                .position(B3)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus)));

        mMap.addMarker(new MarkerOptions()
                .title("B4")
                .snippet("Single Quarters Engine Service Bus Stop")
                .position(B4)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus)));

        mMap.addMarker(new MarkerOptions()
                .title("B5")
                .snippet("CNN (Council of Churches)")
                .position(B5)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus)));

        mMap.addMarker(new MarkerOptions()
                .title("B6")
                .snippet("Otjikaendu Bus stop")
                .position(B6)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus)));

        mMap.addMarker(new MarkerOptions()
                .title("B7")
                .snippet("Wanaheda Bus Stop")
                .position(B7)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus)));

        mMap.addMarker(new MarkerOptions()
                .title("B8")
                .snippet("A Shipena Bus Stop")
                .position(B8)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus)));

        mMap.addMarker(new MarkerOptions()
                .title("B9")
                .snippet("Golgota 13 Bus Stop")
                .position(B9)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus)));

        mMap.addMarker(new MarkerOptions()
                .title("B12")
                .snippet("Geemende Bus Stop")
                .position(B12)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus)));

        mMap.addMarker(new MarkerOptions()
                .title("B14")
                .snippet("Damara  Bus Stop")
                .position(B14)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus)));

        mMap.addMarker(new MarkerOptions()
                .title("B15")
                .snippet("Miami Service Bus Stop")
                .position(B15)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus)));

        mMap.addMarker(new MarkerOptions()
                .title("B16")
                .snippet("Swapo headquarters Bus Stop")
                .position(B16)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus)));

        mMap.addMarker(new MarkerOptions()
                .title("B17")
                .snippet("Katutura Hospital Bus Stop")
                .position(B17)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus)));

        mMap.addMarker(new MarkerOptions()
                .title("B18")
                .snippet("Miami Service (Right) Bus Stop")
                .position(B18)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus)));

        mMap.addMarker(new MarkerOptions()
                .title("B19")
                .snippet("Yetu Yama Bus Stop")
                .position(B19)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus)));

        mMap.addMarker(new MarkerOptions()
                .title("B20")
                .snippet("John ya Otto Soccer field Bus Stop")
                .position(B20)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus)));

        mMap.addMarker(new MarkerOptions()
                .title("B38")
                .snippet("Ramatex (Khomasdal) Bus Stop")
                .position(B38)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus)));

        mMap.addMarker(new MarkerOptions()
                .title("B47")
                .snippet("Ramatex (Otjomuise) Bus Stop")
                .position(B47)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus)));

        mMap.addMarker(new MarkerOptions()
                .title("B49")
                .snippet("Rocky Crest Bus Stop")
                .position(B49)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus)));

        mMap.addMarker(new MarkerOptions()
                .title("B64")
                .snippet("Academia Bus Stop")
                .position(B64)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus)));

        mMap.addMarker(new MarkerOptions()
                .title("B67")
                .snippet("Unam Bus Stop")
                .position(B67)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus)));

        mMap.addMarker(new MarkerOptions()
                .title("B124")
                .snippet("Parliament Bus Stop")
                .position(B124)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus)));

        mMap.addMarker(new MarkerOptions()
                .title("B125")
                .snippet("WHS Bus Stop")
                .position(B125)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus)));

        mMap.addMarker(new MarkerOptions()
                .title("B126")
                .snippet("Michelle Mclean Bus Stop")
                .position(B126)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus)));

        mMap.addMarker(new MarkerOptions()
                .title("B127")
                .snippet("Marua mall Bus Stop")
                .position(B127)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus)));


        mMap.addMarker(new MarkerOptions()
                .title("B131")
                .snippet("KW1 Bus Stop")
                .position(B131)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus)));

        mMap.addMarker(new MarkerOptions()
                .title("B135")
                .snippet("KW2 Bus Stop")
                .position(B132)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus)));

        mMap.addMarker(new MarkerOptions()
                .title("B135")
                .snippet("Rhino park Bus Stop")
                .position(B135)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus)));

        mMap.addMarker(new MarkerOptions()
                .title("B137")
                .snippet("KFC Bus Stop")
                .position(B137)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus)));

        mMap.addMarker(new MarkerOptions()
                .title("B141")
                .snippet("Air Namibia Bus Stop")
                .position(B141)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus)));

        mMap.addMarker(new MarkerOptions()
                .title("B142")
                .snippet("Nandos (Southern Industrial area) Bus Stop")
                .position(B142)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus)));

        mMap.addMarker(new MarkerOptions()
                .title("B143")
                .snippet("Werhnill Bus Stop")
                .position(B143)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus)));

        mMap.addMarker(new MarkerOptions()
                .title("B144")
                .snippet("Roman Catholic Hospital Bus Stop")
                .position(B144)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus)));

        mMap.addMarker(new MarkerOptions()
                .title("B145")
                .snippet("Single Quarters Engine Service Bus Stop")
                .position(B145)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus)));

        mMap.addMarker(new MarkerOptions()
                .title("B146")
                .snippet("Single Quarters Engine Service Bus Stop")
                .position(B146)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus)));

    }

    @Override
    public void onProviderDisabled(String provider) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.gps);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {


            public void onClick(DialogInterface dialog, int which) {
                Intent startGps = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(startGps);
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {


            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    /*
    *
    *
    */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }

    @Override
    public void onLocationChanged(Location location) {

        latitude = location.getLatitude();
        longitude = location.getLongitude();

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }
}
