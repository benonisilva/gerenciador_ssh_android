package com.example.monografiassh2013;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MemoriaActivity extends Activity {

	private String[] informacoes;
	private String[] inf= new String[6];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_memoria);
		
		Intent intent = getIntent();
		informacoes = (String[]) intent.getStringArrayExtra("mem");
		informacoes[1] = informacoes[1].replace("Mem: ", "");
		informacoes[0] = informacoes[0].replace("cached", "");
		int i=0;
		for(String s:informacoes[1].split(" ")) {
			inf[i]=s;
			i++;
			
		}
		//informacoes[0] = informacoes[0].replace(" ", "      ");
		//informacoes[1] = informacoes[1].replace(" ", "      ");
		//informacoes[2]=null;
		//informacoes[3]=null;
		TextView tx1 = (TextView) findViewById(R.id.textView5);
		TextView tx2 = (TextView) findViewById(R.id.textView6);
		TextView tx3 = (TextView) findViewById(R.id.textView7);
		TextView tx4 = (TextView) findViewById(R.id.textView8);
		tx1.setText(inf[0]);
		tx2.setText(inf[1]);
		tx3.setText(inf[2]);
		tx4.setText(inf[3]);
		
		
	}

}
