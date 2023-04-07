package fr.bouyaghir.trotticycle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceManager;
import android.Manifest;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.ViewGroup;
import android.widget.Button;
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
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;
import java.util.ArrayList;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;

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

        GeoPoint startPoint = new GeoPoint(46.6333, 0.3167);
        IMapController mapController = map.getController();
        mapController.setZoom(18.0);
        mapController.setCenter(startPoint);

        ArrayList<OverlayItem> items = new ArrayList<>();

        // Créer un marqueur
        Drawable Marker = ContextCompat.getDrawable(this, R.drawable.ic_marker_red);

        Button button = new Button(this);
        button.setText("Y ALLER");


        // Marker 1 station marquage au sol
        Marker marker1 = new Marker(map);
        marker1.setPosition(new GeoPoint(46.634335,0.331986));
        marker1.setTitle("Station 1");
        marker1.setSnippet("Bois 1");
        marker1.setIcon(Marker);
        ((ViewGroup) marker1.getInfoWindow().getView()).addView(button);
        map.getOverlays().add(marker1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer la position actuelle de l'utilisateur
                GeoPoint startPoint = myLocationOverlay.getMyLocation();
                if (startPoint == null) {
                    Toast.makeText(MainActivity.this, "Impossible de récupérer votre position actuelle", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Créer une liste de points pour le trajet
                ArrayList<GeoPoint> waypoints = new ArrayList<>();
                waypoints.add(startPoint);
                waypoints.add(marker1.getPosition()); // remplacer marker1.getPosition() par la position de la destination souhaitée

                // Exécuter l'opération de réseau dans un AsyncTask
                new AsyncTask<ArrayList<GeoPoint>, Void, Road>() {
                    @Override
                    protected Road doInBackground(ArrayList<GeoPoint>... params) {
                        // Calculer la route entre les points
                        RoadManager roadManager = new OSRMRoadManager(MainActivity.this, "https://routing.openstreetmap.fr/");
                        Road road = roadManager.getRoad(params[0]);
                        return road;
                    }

                    @Override
                    protected void onPostExecute(Road road) {
                        // Afficher le trajet sur la carte
                        Polyline roadOverlay = RoadManager.buildRoadOverlay(road, Color.BLUE, 10);
                        map.getOverlays().add(roadOverlay);
                        map.invalidate(); // forcer la mise à jour de l'affichage
                    }
                }.execute(waypoints);
            }
        });


        Button button2 = new Button(this);
        button2.setText("Y ALLER");

// Marker 2 station marquage au sol
        Marker marker2 = new Marker(map);
        marker2.setPosition(new GeoPoint(46.627243,0.339631));
        marker2.setTitle("Station 2");
        marker2.setSnippet("Bois 2");
        marker2.setIcon(Marker);
        ((ViewGroup) marker2.getInfoWindow().getView()).addView(button2);
        map.getOverlays().add(marker2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer la position actuelle de l'utilisateur
                GeoPoint startPoint = myLocationOverlay.getMyLocation();
                if (startPoint == null) {
                    Toast.makeText(MainActivity.this, "Impossible de récupérer votre position actuelle", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Créer une liste de points pour le trajet
                ArrayList<GeoPoint> waypoints = new ArrayList<>();
                waypoints.add(startPoint);
                waypoints.add(marker2.getPosition());

                // Exécuter l'opération de réseau dans un AsyncTask
                new AsyncTask<ArrayList<GeoPoint>, Void, Road>() {
                    @Override
                    protected Road doInBackground(ArrayList<GeoPoint>... params) {
                        // Calculer la route entre les points
                        RoadManager roadManager = new OSRMRoadManager(MainActivity.this, "https://routing.openstreetmap.fr/");
                        Road road = roadManager.getRoad(params[0]);
                        return road;
                    }

                    @Override
                    protected void onPostExecute(Road road) {
                        // Afficher le trajet sur la carte
                        Polyline roadOverlay = RoadManager.buildRoadOverlay(road, Color.BLUE, 10);
                        map.getOverlays().add(roadOverlay);
                        map.invalidate(); // forcer la mise à jour de l'affichage
                    }
                }.execute(waypoints);
            }
        });

        Button button3 = new Button(this);
        button3.setText("Y ALLER");

// Marker 3 station marquage au sol
        Marker marker3 = new Marker(map);
        marker3.setPosition(new GeoPoint(46.634269,0.343020));
        marker3.setTitle("Station 3");
        marker3.setSnippet("Bois 3");
        marker3.setIcon(Marker);
        ((ViewGroup) marker3.getInfoWindow().getView()).addView(button3);
        map.getOverlays().add(marker3);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer la position actuelle de l'utilisateur
                GeoPoint startPoint = myLocationOverlay.getMyLocation();
                if (startPoint == null) {
                    Toast.makeText(MainActivity.this, "Impossible de récupérer votre position actuelle", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Créer une liste de points pour le trajet
                ArrayList<GeoPoint> waypoints = new ArrayList<>();
                waypoints.add(startPoint);
                waypoints.add(marker3.getPosition());

                // Exécuter l'opération de réseau dans un AsyncTask
                new AsyncTask<ArrayList<GeoPoint>, Void, Road>() {
                    @Override
                    protected Road doInBackground(ArrayList<GeoPoint>... params) {
                        // Calculer la route entre les points
                        RoadManager roadManager = new OSRMRoadManager(MainActivity.this, "https://routing.openstreetmap.fr/");
                        Road road = roadManager.getRoad(params[0]);
                        return road;
                    }

                    @Override
                    protected void onPostExecute(Road road) {
                        // Afficher le trajet sur la carte
                        Polyline roadOverlay = RoadManager.buildRoadOverlay(road, Color.BLUE, 10);
                        map.getOverlays().add(roadOverlay);
                        map.invalidate(); // forcer la mise à jour de l'affichage
                    }
                }.execute(waypoints);
            }
        });


        Button button4 = new Button(this);
        button4.setText("Y ALLER");

// Marker 4 station marquage au sol
        Marker marker4 = new Marker(map);
        marker4.setPosition(new GeoPoint(46.626141,0.305015));
        marker4.setTitle("Station 4");
        marker4.setSnippet("Bois 4");
        marker4.setIcon(Marker);
        ((ViewGroup) marker4.getInfoWindow().getView()).addView(button4);
        map.getOverlays().add(marker4);

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer la position actuelle de l'utilisateur
                GeoPoint startPoint = myLocationOverlay.getMyLocation();
                if (startPoint == null) {
                    Toast.makeText(MainActivity.this, "Impossible de récupérer votre position actuelle", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Créer une liste de points pour le trajet
                ArrayList<GeoPoint> waypoints = new ArrayList<>();
                waypoints.add(startPoint);
                waypoints.add(marker4.getPosition());


                // Exécuter l'opération de réseau dans un AsyncTask
                new AsyncTask<ArrayList<GeoPoint>, Void, Road>() {
                    @Override
                    protected Road doInBackground(ArrayList<GeoPoint>... params) {
                        // Calculer la route entre les points
                        RoadManager roadManager = new OSRMRoadManager(MainActivity.this, "https://routing.openstreetmap.fr/");
                        Road road = roadManager.getRoad(params[0]);
                        return road;
                    }

                    @Override
                    protected void onPostExecute(Road road) {
                        // Afficher le trajet sur la carte
                        Polyline roadOverlay = RoadManager.buildRoadOverlay(road, Color.BLUE, 10);
                        map.getOverlays().add(roadOverlay);
                        map.invalidate(); // forcer la mise à jour de l'affichage
                    }
                }.execute(waypoints);
            }
        });



        // Marker 5 station marquage au sol
        Marker marker5 = new Marker(map);
        marker5.setPosition(new GeoPoint(46.629708,0.304255));
        marker5.setTitle("Station 5");
        marker5.setSnippet("Bois 5");
        marker5.setIcon(Marker);
        marker5.setIcon(Marker);
        map.getOverlays().add(marker5);


        // Marker 6 station marquage au sol
        Marker marker6 = new Marker(map);
        marker6.setPosition(new GeoPoint(46.630532,0.302098));
        marker6.setTitle("Station 6");
        marker6.setSnippet("Bois 6");
        marker6.setIcon(Marker);
        marker6.setIcon(Marker);
        map.getOverlays().add(marker6);


        // Marker 7 station marquage au sol
        Marker marker7 = new Marker(map);
        marker7.setPosition(new GeoPoint(46.620046,0.274485));
        marker7.setTitle("Station 7");
        marker7.setSnippet("Bois 7");
        marker7.setIcon(Marker);
        marker7.setIcon(Marker);
        map.getOverlays().add(marker7 );


        // Marker 7 station marquage au sol
        Marker marker9 = new Marker(map);
        marker9.setPosition(new GeoPoint(46.625133,0.279752));
        marker9.setTitle("Station 7");
        marker9.setSnippet("Bois 7");
        marker9.setIcon(Marker);
        marker9.setIcon(Marker);
        map.getOverlays().add(marker9);

        Marker Station3 = new Marker(map);
        Station3.setPosition(new GeoPoint(46.626667,0.311561));
        Station3.setTitle("Rue Louis Plaud");
        Station3.setSnippet("Trotti 3 ");
        Station3.setIcon(Marker);
        Station3.setIcon(Marker);
        map.getOverlays().add(Station3);

        Marker Station4 = new Marker(map);
        Station4.setPosition(new GeoPoint(46.620299 ,0.311018));
        Station4.setTitle("Eglise");
        Station4.setSnippet("Trotti 4 ");
        Station4.setIcon(Marker);
        Station4.setIcon(Marker);
        map.getOverlays().add(Station4);

        Marker Station5 = new Marker(map);
        Station5.setPosition(new GeoPoint(46.619824,0.315161));
        Station5.setTitle("Cimetiere du Porteau");
        Station5.setSnippet("Trotti 5 ");
        Station5.setIcon(Marker);
        Station5.setIcon(Marker);
        map.getOverlays().add(Station5);

        Marker Station6 = new Marker(map);
        Station6.setPosition(new GeoPoint(46.627951,0.317551));
        Station6.setTitle("Parc de la Comberie");
        Station6.setSnippet("Trotti 6 ");
        Station6.setIcon(Marker);
        Station6.setIcon(Marker);
        map.getOverlays().add(Station6);

        Marker Station7 = new Marker(map);
        Station7.setPosition(new GeoPoint(46.634216,0.320358));
        Station7.setTitle("Parc des Rocheraux");
        Station7.setSnippet("Trotti 7 ");
        Station7.setIcon(Marker);
        Station7.setIcon(Marker);
        map.getOverlays().add(Station7);

        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);

        ScaleBarOverlay myScaleBarOverlay = new ScaleBarOverlay(map);
        map.getOverlays().add(myScaleBarOverlay);

        CompassOverlay mCompassOverlay = new CompassOverlay(getApplicationContext(), new InternalCompassOrientationProvider(getApplicationContext()), map);
        mCompassOverlay.enableCompass();
        map.getOverlays().add(mCompassOverlay);

        // Ajouter un Overlay pour afficher la localisation de l'utilisateur
        myLocationOverlay = new MyLocationNewOverlay(map);
        map.getOverlays().add(myLocationOverlay);

        // Ajouter un bouton pour centrer la carte sur la position de l'utilisateur
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


        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                GeoPoint point = new GeoPoint(location.getLatitude(), location.getLongitude());
                myLocationOverlay.setEnabled(true);
                myLocationOverlay.enableMyLocation(); //Accès à la localisation
                myLocationOverlay.setDrawAccuracyEnabled(true);

                //Si la map est centré
                if (centerMapOnLocation) {
                    map.getController().animateTo(point);
                }

                // Réinitialiser le flag de centrage de la carte
                centerMapOnLocation = false;
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {}
            @Override
            public void onProviderEnabled(String provider) {}
            @Override
            public void onProviderDisabled(String provider) {}
        };

        // Vérifier si l'application a la permission d'accéder à la localisation de l'utilisateur
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_LOCATION);
        } else {
            // Si l'application a la permission, commencer à écouter les mises à jour de la localisation de l'utilisateur
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        }



        ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<OverlayItem>(getApplicationContext(), items, new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {
                return true;
            }

            @Override
            public boolean onItemLongPress(int index, OverlayItem item) {
                return false;
            }
        });

        mOverlay.setFocusItemsOnTap(true);
        map.getOverlays().add(mOverlay);
    }


    @Override
    public void onPause(){

        super.onPause();
        map.onPause();

    }

    @Override
    public void onResume(){

        super.onResume();
        map.onResume();

    }


}