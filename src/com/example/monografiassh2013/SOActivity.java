package com.example.monografiassh2013;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

public class SOActivity extends Activity {

	private String[] informacoes;
	private TextView txv_kernel,txv_versao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_so);
		txv_kernel = (TextView) findViewById(R.id.txt_Kerner);
		txv_versao = (TextView) findViewById(R.id.txt_versao);
		
		Intent i = getIntent();
		informacoes = (String[]) i.getStringArrayExtra("so");
		
		txv_kernel.setText("Kernel: "+informacoes[2]);
		txv_versao.setText("Versão: "+informacoes[1]);
		
		
		
	}

}
