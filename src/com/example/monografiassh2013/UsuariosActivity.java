package com.example.monografiassh2013;

import com.example.monografiassh2013.bd.dao.Servidor;
import com.example.monografiassh2013.conexao.ConfiguracaoConexao;
import com.example.monografiassh2013.conexao.SSHClienteConexao;
import android.os.Bundle;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class UsuariosActivity extends ListActivity {

	
	private String[] informacoes;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_usuarios);
		
		Intent i = getIntent();
		informacoes = (String[]) i.getStringArrayExtra("users");
		
		//config = new ConfiguracaoConexao(servidor.getHost(), servidor.getUser(), servidor.getPass(), servidor.getPorta());
        //ssh = new SSHClienteConexao();
		
		//TextView txi = (TextView) findViewById(R.id.txt_usuarios_titulo);
		//txi.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		        android.R.layout.test_list_item, informacoes);
		    setListAdapter(adapter);
		    Log.i("adpter len", adapter.getCount()+"");
	}
	
		
	private void _toastError(String s) {
		Context context = getApplicationContext();
		CharSequence text = s;
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}

	

}
