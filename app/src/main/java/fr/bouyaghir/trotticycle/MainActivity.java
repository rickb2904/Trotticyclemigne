package fr.bouyaghir.trotticycle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceManager;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MapView map;
    private boolean centerMapOnLocation = true;
    private MyLocationNewOverlay myLocationOverlay;
    private static final int PERMISSION_REQUEST_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        setContentView(R.layout.activity_main);

        map = findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);

        Drawable markerDrawable = ContextCompat.getDrawable(this, R.drawable.ic_marker_red);

        // Création des marqueurs pour les points situés vers l'ouest

        // Point 1
        Marker marker1 = new Marker(map);
        double latitude = 46.619824;
        double longitude = 0.315161;
        String nom = "Cimetière";
        String description = "Marquage au sol";


        marker1.setPosition(new GeoPoint(latitude, longitude));
        marker1.setTitle(nom);
        marker1.setSnippet(description);
        marker1.setIcon(markerDrawable);
        map.getOverlays().add(marker1);

        // Point 2
        Marker marker2 = new Marker(map);
        latitude = 46.620299;
        longitude = 0.311018;
        nom = "Stade";
        description = "Marquage au sol";


        marker2.setPosition(new GeoPoint(latitude, longitude));
        marker2.setTitle(nom);
        marker2.setSnippet(description);
        marker2.setIcon(markerDrawable);
        map.getOverlays().add(marker2);

        // Point 3
        Marker marker3 = new Marker(map);
        latitude = 46.626667;
        longitude = 0.311561;
        nom = "Conservatoire";
        description = "Marquage au sol";


        marker3.setPosition(new GeoPoint(latitude, longitude));
        marker3.setTitle(nom);
        marker3.setSnippet(description);
        marker3.setIcon(markerDrawable);
        map.getOverlays().add(marker3);

        // Point 4
        Marker marker4 = new Marker(map);
        latitude = 46.627951;
        longitude = 0.317551;
        nom = "La Comberie";
        description = "Marquage au sol";


        marker4.setPosition(new GeoPoint(latitude, longitude));
        marker4.setTitle(nom);
        marker4.setSnippet(description);
        marker4.setIcon(markerDrawable);
        map.getOverlays().add(marker4);

        // Point 5
        Marker marker5 = new Marker(map);
        latitude = 46.634216;
        longitude = 0.320358;
        nom = "City Park des Rochereaux";
        description = "Marquage au sol";

        marker5.setPosition(new GeoPoint(latitude, longitude));
        marker5.setTitle(nom);
        marker5.setSnippet(description);
        marker5.setIcon(markerDrawable);
        map.getOverlays().add(marker5);

        // Point 6
        Marker marker6 = new Marker(map);
        latitude = 46.634335;
        longitude = 0.331986;
        nom = "École Desnos";
        description = "Station virtuelle";

        marker6.setPosition(new GeoPoint(latitude, longitude));
        marker6.setTitle(nom);
        marker6.setSnippet(description);
        marker6.setIcon(markerDrawable);
        map.getOverlays().add(marker6);

        // Point 7
        Marker marker7 = new Marker(map);
        latitude = 46.627243;
        longitude = 0.339631;
        nom = "City Park des Cosses";
        description = "Station virtuelle";


        marker7.setPosition(new GeoPoint(latitude, longitude));
        marker7.setTitle(nom);
        marker7.setSnippet(description);
        marker7.setIcon(markerDrawable);
        map.getOverlays().add(marker7);

        // Point 8
        Marker marker8 = new Marker(map);
        latitude = 46.634269;
        longitude = 0.343020;
        nom = "La Rivardière";
        description = "Station virtuelle";


        marker8.setPosition(new GeoPoint(latitude, longitude));
        marker8.setTitle(nom);
        marker8.setSnippet(description);
        marker8.setIcon(markerDrawable);
        map.getOverlays().add(marker8);

        // Point 9
        Marker marker9 = new Marker(map);
        latitude = 46.626141;
        longitude = 0.305015;
        nom = "Square des Coudres";
        description = "Station virtuelle";

        marker9.setPosition(new GeoPoint(latitude, longitude));
        marker9.setTitle(nom);
        marker9.setSnippet(description);
        marker9.setIcon(markerDrawable);
        map.getOverlays().add(marker9);

        // Point 10
        Marker marker10 = new Marker(map);
        latitude = 46.629708;
        longitude = 0.304255;
        nom = "Monfleury";
        description = "Station virtuelle";


        marker10.setPosition(new GeoPoint(latitude, longitude));
        marker10.setTitle(nom);
        marker10.setSnippet(description);
        marker10.setIcon(markerDrawable);
        map.getOverlays().add(marker10);

        // Point 11
        Marker marker11 = new Marker(map);
        latitude = 46.630532;
        longitude = 0.302098;
        nom = "Les Hauts de l’Auxance";
        description = "Station virtuelle";


        marker11.setPosition(new GeoPoint(latitude, longitude));
        marker11.setTitle(nom);
        marker11.setSnippet(description);
        marker11.setIcon(markerDrawable);
        map.getOverlays().add(marker11);

        // Point 12
        Marker marker12 = new Marker(map);
        latitude = 46.620046;
        longitude = 0.274485;
        nom = "Moulinet";
        description = "Station virtuelle";


        marker12.setPosition(new GeoPoint(latitude, longitude));
        marker12.setTitle(nom);
        marker12.setSnippet(description);
        marker12.setIcon(markerDrawable);
        map.getOverlays().add(marker12);

        // Point 13
        Marker marker13 = new Marker(map);
        latitude = 46.625133;
        longitude = 0.279752;
        nom = "École de Limbre";
        description = "Station virtuelle";


        marker13.setPosition(new GeoPoint(latitude, longitude));
        marker13.setTitle(nom);
        marker13.setSnippet(description);
        marker13.setIcon(markerDrawable);
        map.getOverlays().add(marker13);


        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);

        ScaleBarOverlay myScaleBarOverlay = new ScaleBarOverlay(map);
        map.getOverlays().add(myScaleBarOverlay);

        CompassOverlay mCompassOverlay = new CompassOverlay(getApplicationContext(), new InternalCompassOrientationProvider(getApplicationContext()), map);
        mCompassOverlay.enableCompass();
        map.getOverlays().add(mCompassOverlay);

        Button myLocationButton = findViewById(R.id.my_location_button);
        myLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                centerMapOnLocation = true;
                GeoPoint startPoint = myLocationOverlay.getMyLocation();
                if (startPoint != null) {
                    map.getController().animateTo(startPoint);
                } else {
                    Toast.makeText(MainActivity.this, "Impossible de récupérer votre position actuelle", Toast.LENGTH_SHORT).show();
                }
            }
        });

        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                GeoPoint point = new GeoPoint(location.getLatitude(), location.getLongitude());
                myLocationOverlay.setEnabled(true);
                if (centerMapOnLocation) {
                    map.getController().setZoom(18.0);
                    map.getController().setCenter(point);
                }
                centerMapOnLocation = false;
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        };

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_LOCATION);
        } else {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }

        myLocationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(this), map);
        myLocationOverlay.enableMyLocation();
        map.getOverlays().add(myLocationOverlay);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                LocationListener locationListener = new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        GeoPoint point = new GeoPoint(location.getLatitude(), location.getLongitude());
                        myLocationOverlay.setEnabled(true);
                        if (centerMapOnLocation) {
                            map.getController().setZoom(18.0);
                            map.getController().setCenter(point);
                        }
                        centerMapOnLocation = false;
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {
                    }

                    @Override
                    public void onProviderEnabled(String provider) {
                    }

                    @Override
                    public void onProviderDisabled(String provider) {
                    }
                };
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            } else {
                Toast.makeText(this, "Permission de localisation refusée", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        map.onPause();
    }
}
