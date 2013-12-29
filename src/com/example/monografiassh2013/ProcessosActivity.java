package com.example.monografiassh2013;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.widget.ArrayAdapter;

public class ProcessosActivity extends ListActivity {

	private String[] informacoes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_processos);
		
		Intent i = getIntent();
		informacoes = (String[]) i.getStringArrayExtra("processos");
		Log.i("inf",informacoes[0].length()+"");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		        android.R.layout.simple_list_item_1, informacoes);
		    setListAdapter(adapter);
		
	}


}
