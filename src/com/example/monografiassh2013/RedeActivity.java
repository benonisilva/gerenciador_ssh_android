package com.example.monografiassh2013;

import com.example.monografiassh2013.bd.dao.Servidor;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;

public class RedeActivity extends ListActivity {
    String [] informacoes;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rede);
		
		Intent i = getIntent();
		informacoes = (String[]) i.getStringArrayExtra("redes");
		
		//temp
		for(int j=0;j<informacoes.length;j++) {
			if(informacoes[j].isEmpty()) {
				Log.d("vazio",j+"");
			}
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		        android.R.layout.simple_list_item_1, informacoes);
		    setListAdapter(adapter);
		    Log.d("adpter len", adapter.getCount()+"");
	}



}
