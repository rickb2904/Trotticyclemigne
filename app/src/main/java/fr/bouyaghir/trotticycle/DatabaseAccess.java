package fr.bouyaghir.trotticycle;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseAccess {

private DatabaseHelper openHelper;
private SQLiteDatabase db ;
private static DatabaseAccess instance ;

Cursor c = null;


private DatabaseAccess(Context context) {this.openHelper=new DatabaseHelper(context);}


    public static DatabaseAccess getInstance(Context context){

        if(instance==null){
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() { this.db=openHelper.getWritableDatabase();}

    public void close() {
        if(db!=null){

            this.db.close();
        }

    }

}


