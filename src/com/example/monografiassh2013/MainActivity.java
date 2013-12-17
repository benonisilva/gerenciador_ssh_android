package com.example.monografiassh2013;


import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.example.monografiassh2013.bd.dao.Servidor;
import com.example.monografiassh2013.bd.dao.ServidorDataSource;
import com.example.monografiassh2013.conexao.ConfiguracaoConexao;
import com.example.monografiassh2013.conexao.SSHClienteConexao;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import net.schmizz.sshj.AndroidConfig;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.common.*;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.verification.HostKeyVerifier;




public class MainActivity extends ListActivity {
    //temp
	private String host,pass,username;
	private int porta;
	private ConfiguracaoConexao config;
	private ServidorDataSource datasource;
	private List<Servidor> values;
	
	//temp teste
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//TODO: Verificar se acontece erro
		//Encontrando o bot�o dentro do layout activity_main
		Button botaoNovo = (Button) findViewById(R.id.btn_novo);
		//Definindo funcionalidade para o bot�o
		botaoNovo.setOnClickListener( ListenerBotaoNovo());

		Button botaoConectar = (Button) findViewById(R.id.btn_conectar);
		botaoConectar.setOnClickListener( ListenerBotaoConectar());

		//Gerar lista de bot�es com os perfis de servidores salvos 
		//Button botaoExemplo = (Button) findViewById(R.id.btn_exemplo);

		//TODO: Acessar a tela de conectar enviando sobrecarga para carregar os dados
		//do servidor do perfil salvo
		//botaoExemplo.setOnClickListener( ListenerBotaoConectar());
		
		//temp
		host = "192.168.0.106";
		pass = "aflor151";
		username = "benoni";
		porta = 22;
		config = new ConfiguracaoConexao(host, username, pass, porta);
		
		//abre servidor e popula lista
		
		datasource = new ServidorDataSource(this);
		datasource.open();
		values = datasource.getAllServidores();
		
		// SimpleCursorAdapter para mostrar
	    // elementos numa ListView
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	        android.R.layout.simple_list_item_1, _getNomesServidores(values));
	    setListAdapter(adapter);
	  
		 
	}
	
	// Opens the second activity if an entry is clicked
	  @Override
	  protected void onListItemClick(ListView l, View v, int position, long id) {
	    super.onListItemClick(l, v, position, id);
	    
	    _toastTeste(values.get(position));
	    
	    
	    
	    Intent i = new Intent(getApplicationContext(),ConexaoDiretaActivity.class);
	    //envia o objeto servidor para a activy que fara a conexão
		i.putExtra("servidor", values.get(position));
		startActivity(i);
	    
	  }

	/**
	 * 
	 * @return retorna um CliclListener para o Bot�o NovoPerfil
	 */
	private View.OnClickListener ListenerBotaoNovo(){

		return new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),NovoPerfilActivity.class);
				//i.putExtra("servidor", myParcelableObject);
				startActivity(i);

			}

		};
	}
	private View.OnClickListener ListenerBotaoConectar(){

		return new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				
				SSHClienteConexao s = new SSHClienteConexao();
				try {
					s.conecta(config);
					Intent i = new Intent(getApplicationContext(),ConexaoDiretaActivity.class);
					startActivity(i);
				} catch (Exception e) {
					
					_toastError(e.getMessage());
				}
				finally {
					s.desconecta();
				}

			}

		};
	}

	public class Loadsomestuff extends AsyncTask<String, Integer, String> {


		@Override
		protected String doInBackground(String... arg0) {

			

			return "Executed";


		}

		@Override
		protected void onPostExecute(String abc){

		}

	}
	
	@Override
	  protected void onResume() {
		super.onResume();
		datasource.open();
	    values = datasource.getAllServidores();
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		        android.R.layout.simple_list_item_1, _getNomesServidores(values));
		    setListAdapter(adapter);
	    
	  }

	  @Override
	  protected void onPause() {
		  super.onPause();
		  datasource.close();
	    
	  }
	
	private List<String>_getNomesServidores(List<Servidor> s) {
		
		List<String> nomes = new ArrayList<String>(s.size());
		for(Servidor i: s) {
			nomes.add(i.getNome());
		}
		
		return nomes;
		
	}
	
	private void _toastTeste(Servidor s) {
		Context context = getApplicationContext();
		CharSequence text = s.getId()+" ID";
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}
	
	private void _toastError(String s) {
		Context context = getApplicationContext();
		CharSequence text = s;
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}
           
}
