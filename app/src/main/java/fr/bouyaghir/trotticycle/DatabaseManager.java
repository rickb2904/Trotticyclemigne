package fr.bouyaghir.trotticycle;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import fr.bouyaghir.trotticycle.entity.Parking;
import fr.bouyaghir.trotticycle.entity.PointDAttraction;
import fr.bouyaghir.trotticycle.entity.PointDePassage;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Trotticycle.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseManager(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSql = "create table Parking ("
                + "id integer primary key autoincrement,"
                + "nom text not null,"
                + "lieu text not null,"
                + "longitude double not null,"
                + "latitude double not null"
                + ")";
        db.execSQL(strSql);
        String strSql2 = "create table MoyenDeTransport ("
                + "id integer primary key autoincrement,"
                + "Type text not null"
                + ")";
        db.execSQL(strSql2);
        String strSql3 = "create table PointDePassage ("
                + "id integer primary key autoincrement,"
                + "nom text not null,"
                + "longitude double not null,"
                + "latitude double not null"
                + ")";
        db.execSQL(strSql3);
        String strSql4 = "create table Trajet ("
                + "id integer primary key autoincrement,"
                + "prix double not null,"
                + "duree double not null"
                + ")";
        db.execSQL(strSql4);
        String strSql5 = "create table PointDAttraction ("
                + "id integer primary key autoincrement,"
                + "nom text not null,"
                + "lieu text not null,"
                + "longitude double not null,"
                + "latitude double not null"
                + ")";
        db.execSQL(strSql5);
    }

    /**
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     * script executé si la version est modifié
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Parking> getAllParking() {
        ArrayList<Parking> parkingList = new ArrayList<>();

        // Obtenir une référence en lecture de la base de données
        SQLiteDatabase db = this.getReadableDatabase();

        // Définir les colonnes que vous souhaitez récupérer de la table "Parking"
        String[] columns = {"id", "nom", "lieu", "latitude", "longitude"};

        // Effectuer la requête pour récupérer tous les enregistrements de la table "Parking"
        Cursor cursor = db.query("Parking", columns, null, null, null, null, null);

        // Parcourir le curseur et ajouter chaque enregistrement à la liste de parkings
        if (cursor.moveToFirst()) {
            do {
                Parking parking = new Parking();
                parking.setId(cursor.getInt(cursor.getColumnIndex("id")));
                parking.setNom(cursor.getString(cursor.getColumnIndex("nom")));
                parking.setLieu(cursor.getString(cursor.getColumnIndex("lieu")));
                parking.setLatitude(cursor.getDouble(cursor.getColumnIndex("latitude")));
                parking.setLongitude(cursor.getDouble(cursor.getColumnIndex("longitude")));
                parkingList.add(parking);
            } while (cursor.moveToNext());
        }

        // Fermer le curseur et la base de données
        cursor.close();
        db.close();

        // Retourner la liste de parkings
        return parkingList;
    }


    public ArrayList<PointDAttraction> getAllPointDAttraction() {
        ArrayList<PointDAttraction> pointDAttractionList = new ArrayList<>();

        // Obtenir une référence en lecture de la base de données
        SQLiteDatabase db = this.getReadableDatabase();

        // Définir les colonnes que vous souhaitez récupérer de la table "PointDAttraction"
        String[] columns = {"id", "nom", "lieu", "latitude", "longitude"};

        // Effectuer la requête pour récupérer tous les enregistrements de la table "PointDAttraction"
        Cursor cursor = db.query("PointDAttraction", columns, null, null, null, null, null);

        // Parcourir le curseur et ajouter chaque enregistrement à la liste de points d'attraction
        if (cursor.moveToFirst()) {
            do {
                PointDAttraction pointDAttraction = new PointDAttraction();
                pointDAttraction.setId(cursor.getInt(cursor.getColumnIndex("id")));
                pointDAttraction.setNom(cursor.getString(cursor.getColumnIndex("nom")));
                pointDAttraction.setLieu(cursor.getString(cursor.getColumnIndex("lieu")));
                pointDAttraction.setLatitude(cursor.getDouble(cursor.getColumnIndex("latitude")));
                pointDAttraction.setLongitude(cursor.getDouble(cursor.getColumnIndex("longitude")));
                pointDAttractionList.add(pointDAttraction);
            } while (cursor.moveToNext());
        }

        // Fermer le curseur et la base de données
        cursor.close();
        db.close();

        // Retourner la liste de points d'attraction
        return pointDAttractionList;
    }

}
