package br.senai.sc.cadastrodeeventos.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBGateway {

    private static DBGateway dbGateway;
    private SQLiteDatabase db;

    public static DBGateway getInstance(Context context) {
        if (dbGateway == null){
            dbGateway = new DBGateway(context);
        }
        return dbGateway;
    }

    private DBGateway(Context context) {
        DatabaseDBHelper dbhelper = new DatabaseDBHelper(context);
        db = dbhelper.getWritableDatabase();
    }

    public SQLiteDatabase getDatabase() {
        return db;
    }

}
