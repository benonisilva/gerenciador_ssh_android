package com.example.monografiassh2013;

import com.example.monografiassh2013.bd.dao.Servidor;
import com.example.monografiassh2013.conexao.ConfiguracaoConexao;
import com.example.monografiassh2013.conexao.SSHClienteConexao;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ConexaoDiretaActivity extends Activity {
    private ConfiguracaoConexao config;
	private TextView txv_host,txv_user,txv_pass,txv_port;
	private Servidor servidor;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conexao_direta);
		//botoes e textviews
		Button botaoConectar = (Button) findViewById(R.id.btn_conectar);
		botaoConectar.setOnClickListener( ListenerBotaoConectar());
		txv_host = (TextView) findViewById(R.id.eTxtHost);
		txv_user = (TextView) findViewById(R.id.etxt_usuario);
		txv_pass = (TextView) findViewById(R.id.etxt_senha);
		txv_port = (TextView) findViewById(R.id.etxt_porta);
		
		
		
		
		Intent i = getIntent();
		servidor = (Servidor) i.getParcelableExtra("servidor");
		//set textviews
	    txv_host.setText(servidor.getHost());
	    txv_user.setText(servidor.getUser());
	    txv_pass.setText(servidor.getPass());
	    txv_port.setText(String.valueOf(servidor.getPorta()));
		
		config = new ConfiguracaoConexao(servidor.getHost(), servidor.getUser(), servidor.getPass(), servidor.getPorta());
		//temp
		Log.i("servidor", servidor.getNome());
		Log.i("host", servidor.getHost());
		Log.i("user", servidor.getUser());
		Log.i("pass", servidor.getPass());
		Log.i("port", String.valueOf(servidor.getPorta()));
		
	}
private View.OnClickListener ListenerBotaoConectar(){
		
		return new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				
				SSHClienteConexao s = new SSHClienteConexao();
				boolean isOpen=false;
				try {
					isOpen = s.conecta(config);
				} catch (Exception e) {
					_toastError(e.getMessage());
				}
				Log.i("conectado ?", isOpen+"");
				if(isOpen) {
					s.desconecta();
					Intent i = new Intent(getApplicationContext(),OpcoesActivity.class);
					i.putExtra("servidor", servidor);
					startActivity(i);
				}
				

			}
			
		};
	}
    
   private void _toastError(String s) {
	Context context = getApplicationContext();
	CharSequence text = s;
	int duration = Toast.LENGTH_SHORT;
	Toast toast = Toast.makeText(context, text, duration);
	toast.show();
}

}
