package com.example.monografiassh2013.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/*
 * Classe responsavel por lidar com o banco de dados dos servidores
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

  public static final String TABLE_SERVIDORES = "servidores";
  public static final String COLUMN_ID = "_id";
  public static final String COLUMN_NOME = "nome";
  public static final String COLUMN_HOST = "host";
  public static final String COLUMN_USER = "user";
  public static final String COLUMN_PASS = "pass";
  public static final String COLUMN_PORT = "porta";

  private static final String DATABASE_NAME = "SERVIDORES.db";
  private static final int DATABASE_VERSION = 1;

  // Database creation sql statement
  private static final String DATABASE_CREATE = "create table "
      + TABLE_SERVIDORES + "(" + COLUMN_ID
      + " integer primary key autoincrement, " + COLUMN_NOME + " text not null, "
      + COLUMN_HOST + " text not null, "
      + COLUMN_USER + " text not null, "
      + COLUMN_PASS + " text not null, "
      + COLUMN_PORT +" integer not null);";

  public MySQLiteHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(DATABASE_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(MySQLiteHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVIDORES);
    onCreate(db);
  }

} 