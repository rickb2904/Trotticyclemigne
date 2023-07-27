package fr.bouyaghir.trotticycle;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;


import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.List;



public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;


    private static final String CREATE_TABLE_COORDONEES  =
            "CREATE TABLE point (id INTEGER PRIMARY KEY, latitude REAL, longitude REAL, nom TEXT, description TEXT)";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_COORDONEES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // Coordonnees DB
    public void ajouterCoordonnees(double latitude, double longitude, String nom, String description) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("latitude", latitude);
        values.put("longitude", longitude);
        values.put("nom", nom);
        values.put("description", description);

        db.insert("point", null, values);
        db.close();
    }

    public void ajouterParcours(List<GeoPoint> waypoints) {
        SQLiteDatabase db = getWritableDatabase();

        for (int i = 0; i < waypoints.size(); i++) {
            GeoPoint point = waypoints.get(i);

            ContentValues values = new ContentValues();
            values.put("latitude", point.getLatitude());
            values.put("longitude", point.getLongitude());

            db.insert("parcours", null, values);
        }

        db.close();
    }


    public List<Coordonnee> getpoint() {
        List<Coordonnee> coordonnees = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {"latitude", "longitude", "nom", "description"};
        Cursor cursor = db.query("point", projection, null, null, null, null, null);

        while (cursor.moveToNext()) {
            double latitude = cursor.getDouble(cursor.getColumnIndexOrThrow("latitude"));
            double longitude = cursor.getDouble(cursor.getColumnIndexOrThrow("longitude"));
            String nom = cursor.getString(cursor.getColumnIndexOrThrow("nom"));
            String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
            coordonnees.add(new Coordonnee(latitude, longitude, nom, description));
        }

        cursor.close();
        db.close();

        return coordonnees;
    }
    // verfie si les coordonée sont deja dans la bdd
    public boolean pointExist(double latitude, double longitude) {
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * FROM point WHERE latitude = ? AND longitude = ?";
        String[] selectionArgs = {String.valueOf(latitude), String.valueOf(longitude)};

        Cursor cursor = db.rawQuery(query, selectionArgs);
        boolean exist = cursor.moveToFirst();
        cursor.close();

        return exist;
    }

    //Fin Coordonnees DB
}

