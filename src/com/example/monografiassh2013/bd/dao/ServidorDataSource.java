package com.example.monografiassh2013.bd.dao;

import java.util.ArrayList;
import java.util.List;

import com.example.monografiassh2013.bd.MySQLiteHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/*
 * Crud de servidor, usando DAO(data assess object)
 */
public class ServidorDataSource {

  // Database fields
  private SQLiteDatabase database;
  private MySQLiteHelper dbHelper;
  private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
      MySQLiteHelper.COLUMN_NOME,MySQLiteHelper.COLUMN_HOST,MySQLiteHelper.COLUMN_USER, MySQLiteHelper.COLUMN_PASS,MySQLiteHelper.COLUMN_PORT };

  public ServidorDataSource(Context context) {
    dbHelper = new MySQLiteHelper(context);
  }

  public void open() throws SQLException {
    database = dbHelper.getWritableDatabase();
  }

  public void close() {
    dbHelper.close();
  }

  public Servidor createServidor(String nome, String host, String user,String pass, int port) {
    ContentValues values = new ContentValues();
    
    values.put(MySQLiteHelper.COLUMN_NOME, nome);
    values.put(MySQLiteHelper.COLUMN_HOST, host);
    values.put(MySQLiteHelper.COLUMN_USER, user);
    values.put(MySQLiteHelper.COLUMN_PASS, pass);
    values.put(MySQLiteHelper.COLUMN_PORT, port);
    
    long insertId = database.insert(MySQLiteHelper.TABLE_SERVIDORES, null,
        values);
    Cursor cursor = database.query(MySQLiteHelper.TABLE_SERVIDORES,
        allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
        null, null, null);
    cursor.moveToFirst();
    Servidor newServidor = cursorToServidor(cursor);
    cursor.close();
    return newServidor;
  }

  public void deleteServidor(Servidor servidor) {
    long id = servidor.getId();
    System.out.println("Servidor deleted with id: " + id);
    database.delete(MySQLiteHelper.TABLE_SERVIDORES, MySQLiteHelper.COLUMN_ID
        + " = " + id, null);
  }

  public List<Servidor> getAllServidores() {
    List<Servidor> listaServidores = new ArrayList<Servidor>();

    Cursor cursor = database.query(MySQLiteHelper.TABLE_SERVIDORES,
        allColumns, null, null, null, null, null);

    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      Servidor servidor = cursorToServidor(cursor);
      listaServidores.add(servidor);
      cursor.moveToNext();
    }
    // make sure to close the cursor
    cursor.close();
    return listaServidores;
  }
  /*
  public void updateServidor(Servidor servidor) {
	    long id = servidor.getId();
	    System.out.println("Servidor deleted with id: " + id);
	    database.update(MySQLiteHelper.TABLE_SERVIDORES, MySQLiteHelper.COLUMN_ID
	        + " = " + id, null);
	  }*/
  
  private Servidor cursorToServidor(Cursor cursor) {
    Servidor servidor = new Servidor();
    servidor.setId(cursor.getLong(0));
    servidor.setNome(cursor.getString(1));
    servidor.setHost(cursor.getString(2));
    servidor.setUser(cursor.getString(3));
    servidor.setPass(cursor.getString(4));
    servidor.setPorta(cursor.getInt(5));
    
    
    return servidor;
  }
} 