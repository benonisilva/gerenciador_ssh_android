package com.example.monografiassh2013;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.widget.ArrayAdapter;

/*
 * Padrao para dos as classe que recebem as informaçoes do servidor
 * todas as outras classe seguem o mesmo esquema
 * recebem da activity OpcoesActivity e populam o adapter
 * 
 * PS:refatorar para usar apenas uma classe e mudar apenas as informaçoes no adpter
 * 
 * 
 */
public class DiscoActivity extends ListActivity {

	private String[] informacoes; //stings com informacoes

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_disco);
		
		Intent i = getIntent();
		informacoes = (String[]) i.getStringArrayExtra("discos"); // pega as informaçoes passadas pela Activty OpcoesActivity
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		        android.R.layout.test_list_item, informacoes);
		    setListAdapter(adapter);
	}

}
