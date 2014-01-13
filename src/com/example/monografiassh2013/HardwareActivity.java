package com.example.monografiassh2013;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.monografiassh2013.bd.dao.Servidor;
import com.example.monografiassh2013.conexao.Comandos;
import com.example.monografiassh2013.conexao.ConfiguracaoConexao;
import com.example.monografiassh2013.conexao.SSHClienteConexao;

public class HardwareActivity extends Activity {

	private Servidor servidor;
	public ProgressDialog myPd_ring2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hardware);

		Button botaoDisco = (Button) findViewById(R.id.btn_disco);
		botaoDisco.setOnClickListener( ListenerBotaoDisco());

		Button botaoMemoria = (Button) findViewById(R.id.btn_memoria);
		botaoMemoria.setOnClickListener( ListenerBotaoMemoria());

		Intent i = getIntent();
		servidor = (Servidor) i.getParcelableExtra("servidor");

		//temp
		Log.i("servidor", servidor.getNome());
		Log.i("host", servidor.getHost());
		Log.i("user", servidor.getUser());
		Log.i("pass", servidor.getPass());
		Log.i("port", String.valueOf(servidor.getPorta()));

	}
	private View.OnClickListener ListenerBotaoDisco(){

		return new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),DiscoActivity.class);
				//startActivity(i);

				Executestuff2 e = new Executestuff2();
				e.execute(i,Comandos.LIST_DISK,"discos","\n");

				/*
                ConfiguracaoConexao config = new ConfiguracaoConexao(servidor.getHost(), servidor.getUser(), servidor.getPass(), servidor.getPorta());
                SSHClienteConexao ssh = new SSHClienteConexao();


                try {
                	ssh.conecta(config);
                	String processos = ssh.exec(Comandos.LIST_DISK);
                	Log.i("discos", processos.split("\n")+"");
                	//_toastTeste(processos);
                	Intent i = new Intent(getApplicationContext(),DiscoActivity.class);
                	i.putExtra("discos", processos.split("\n"));
                	startActivity(i);

				} catch (Exception e) {
                        _toastError(e.getMessage());
				}
                finally {
                	ssh.desconecta();
                }*/

			}

		};
	}
	private View.OnClickListener ListenerBotaoMemoria(){

		return new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),MemoriaActivity.class);
				//startActivity(i);
				Executestuff2 e = new Executestuff2();
				e.execute(i,Comandos.INFO_MEMORIA,"mem","\n");

				/*
                ConfiguracaoConexao config = new ConfiguracaoConexao(servidor.getHost(), servidor.getUser(), servidor.getPass(), servidor.getPorta());
                SSHClienteConexao ssh = new SSHClienteConexao();


                try {
                	ssh.conecta(config);
                	String processos = ssh.exec(Comandos.INFO_MEMORIA);
                	Log.i("mem", processos.split("\n")+"");
                	//_toastTeste(processos);
                	Intent i = new Intent(getApplicationContext(),MemoriaActivity.class);
                	i.putExtra("mem", processos.split("\n"));
                	startActivity(i);

				} catch (Exception e) {
                        _toastError(e.getMessage());
				}
                finally {
                	ssh.desconecta();
                }*/

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

	public class Executestuff2 extends AsyncTask<Object, String, String> {


		

		@Override
		protected String doInBackground(Object... arg0) {

			//tmp
			Log.i("teste", "error");
			//tmp
			Log.i("teste", servidor.getHost());

			ConfiguracaoConexao config = new ConfiguracaoConexao(servidor.getHost(), servidor.getUser(), servidor.getPass(), servidor.getPorta());
			SSHClienteConexao ssh = new SSHClienteConexao();



			String comando,c,sep;
			comando= (String)arg0[1];
			c = (String)arg0[2];
			sep = (String) arg0[3];
			Intent i = (Intent)arg0[0];

			try {
				ssh.conecta(config);
				String inf = ssh.exec(comando);
				Log.i(c, inf.split(sep)+"");

				i.putExtra(c, inf.split(sep));
				startActivity(i);
				finish();
			} catch (Exception e) {
				_toastError(e.getMessage());
			}
			finally {
				ssh.desconecta();
			}

			return "Executed";


		}
		//idem de ConexaoDiretaActivity
		@Override
		protected void onPostExecute(String abc){
			super.onPostExecute(abc);
			myPd_ring2.dismiss();

		}

		//idem de ConexaoDiretaActivity
		@Override
		protected void onPreExecute() {

			myPd_ring2  = new ProgressDialog(HardwareActivity.this);
			myPd_ring2.setMessage("Loading...");
			myPd_ring2.show();

		}
	}
}
