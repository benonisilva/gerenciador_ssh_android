package com.example.monografiassh2013;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ArrayAdapter;

public class UsuariosActivity extends ListActivity {

	private String[] informacoes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_usuarios);
		
		Intent i = getIntent();
		informacoes = (String[]) i.getStringArrayExtra("users");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		        android.R.layout.simple_list_item_1, informacoes);
		    setListAdapter(adapter);
	}


}