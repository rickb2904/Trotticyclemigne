package fr.bouyaghir.trotticycle;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.Toast;

import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Polyline;

import java.util.ArrayList;

public class RoutingMachine extends AsyncTask<Void, Void, Road> {
    private Context context;
    private GeoPoint startPoint;
    private GeoPoint endPoint;
    private MapView mapView;

    public void setContext(Context context) {
        this.context = context;
    }

    public void setStartPoint(GeoPoint startPoint) {
        this.startPoint = startPoint;
    }

    public void setEndPoint(GeoPoint endPoint) {
        this.endPoint = endPoint;
    }

    public void setMapView(MapView mapView) {
        this.mapView = mapView;
    }

    @Override
    protected Road doInBackground(Void... params) {
        RoadManager roadManager = new OSRMRoadManager(context, null);
        ArrayList<GeoPoint> waypoints = new ArrayList<>();
        waypoints.add(startPoint);
        waypoints.add(endPoint);
        return roadManager.getRoad(waypoints);
    }

    @Override
    protected void onPostExecute(Road road) {
        super.onPostExecute(road);
        if (road != null) {
            Polyline roadOverlay = RoadManager.buildRoadOverlay(road, Color.rgb(148, 0, 211), 10);
            mapView.getOverlays().add(roadOverlay);
            mapView.invalidate(); // Refresh the map view to display the route
        } else {
            Toast.makeText(context, "Impossible de calculer l'itinéraire", Toast.LENGTH_SHORT).show();
        }
    }
}
