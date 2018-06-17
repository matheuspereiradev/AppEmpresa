package com.example.matheus.appempresa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdmBanco extends SQLiteOpenHelper {
//SERVE SÓ PRA CRIAR O BANCO
    public AdmBanco(Context context){
        super(context, "empresa.bd",null,2);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE compromisso (" +
                "_id integer primary key autoincrement not null," +
                "nome text," +
                "tipo text," +
                "data text," +
                "hora text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
