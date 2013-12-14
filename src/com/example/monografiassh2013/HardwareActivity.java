package com.example.monografiassh2013;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class HardwareActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hardware);
		
		Button botaoDisco = (Button) findViewById(R.id.btn_disco);
		botaoDisco.setOnClickListener( ListenerBotaoDisco());
		
		Button botaoMemoria = (Button) findViewById(R.id.btn_memoria);
		botaoMemoria.setOnClickListener( ListenerBotaoMemoria());
	}
	private View.OnClickListener ListenerBotaoDisco(){
		
		return new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),DiscoActivity.class);
                startActivity(i);

			}
			
		};
	}
	private View.OnClickListener ListenerBotaoMemoria(){
		
		return new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),MemoriaActivity.class);
                startActivity(i);

			}
			
		};
	}
}
