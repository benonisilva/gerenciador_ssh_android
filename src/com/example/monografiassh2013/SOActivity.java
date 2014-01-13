package com.example.monografiassh2013;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SOActivity extends ListActivity {

	private String[] informacoes;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_so);
				
		Intent i = getIntent();
		informacoes = (String[]) i.getStringArrayExtra("so");
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		        android.R.layout.test_list_item, informacoes);
		    setListAdapter(adapter);
		
		
		
	}

}
