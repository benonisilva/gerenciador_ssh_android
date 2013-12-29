package com.example.monografiassh2013;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.monografiassh2013.bd.dao.Servidor;
import com.example.monografiassh2013.bd.dao.ServidorDataSource;
import com.example.monografiassh2013.conexao.ConfiguracaoConexao;
import com.example.monografiassh2013.conexao.SSHClienteConexao;
import com.example.monografiassh2013.utils.ConfirmAlert;

/*
 * Responsavel pela conexão com o servidor
 */
public class ConexaoDiretaActivity extends Activity implements ConfirmAlert.DialogReturn {
	private ConfiguracaoConexao config;
	private TextView txv_host,txv_user,txv_pass,txv_port;
	private Servidor servidor;
	private ServidorDataSource datasource;
	private ConfirmAlert confirm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conexao_direta);
		//botoes e textviews
		Button botaoConectar = (Button) findViewById(R.id.btn_conectar);
		Button botaoDeletar = (Button) findViewById(R.id.btn_deletar);
		Button botaoEditar = (Button) findViewById(R.id.btn_editar);

		botaoConectar.setOnClickListener( ListenerBotaoConectar());
		botaoDeletar.setOnClickListener( ListenerBotaoDeletar());
		botaoEditar.setOnClickListener( ListenerBotaoEditar());

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
		//temp usado para debugar
		Log.i("servidor", servidor.getNome());
		Log.i("host", servidor.getHost());
		Log.i("user", servidor.getUser());
		Log.i("pass", servidor.getPass());
		Log.i("port", String.valueOf(servidor.getPorta()));

		//servidores bd
		datasource = new ServidorDataSource(this);
		datasource.open();

		//alerta usado para confimar deletar
		confirm = new ConfirmAlert();
		confirm.setListener(this);

	}
    /*
     * Listener usado no button deletar
     */
	private OnClickListener ListenerBotaoDeletar() {
		return new View.OnClickListener(){

			@Override
			public void onClick(View v) {
               _confirm(v.getContext());
				
			}

		};
	}
	
	
	
	private OnClickListener ListenerBotaoEditar() {
		return new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),NovoPerfilActivity.class);
				//Servidor s = new Servidor();
				//s.setId(-1);
				i.putExtra("servidor", servidor);
				startActivity(i);
				finish();
				
			}

		};
	}

	/*
	 * Listener para o botão conectar
	 */
	private View.OnClickListener ListenerBotaoConectar(){

		return new View.OnClickListener(){

			@Override
			public void onClick(View v) {

				Loadsomestuff l = new Loadsomestuff();
				l.execute("","","");


			}

		};
	}

	/*
	 * usando para mostrar saidas de erro
	 */
	private void _toastError(String s) {
		Context context = getApplicationContext();
		CharSequence text = s;
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}

    /*
     * metodo usado mostrar opçao de confirmação ao deletar um servidor	
     */
	public void _confirm(Context context) {
		AlertDialog dialog = new AlertDialog.Builder(context).create();
		dialog.setTitle("Certo que vai deletar?");
		dialog.setMessage("Escolha Sim or Não");
		dialog.setCancelable(false);
		dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Sim", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int buttonId) {
				confirm.getListener().onDialogCompleted(true);
			}
		});
		dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Não", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int buttonId) {
				confirm.getListener().onDialogCompleted(false);
			}
		});
		dialog.setIcon(android.R.drawable.ic_dialog_alert);
		dialog.show();
	}

    
	/*
	 * Deleta o servidor do bd
	 */
	@Override
	public void onDialogCompleted(boolean answer) {
		Toast.makeText(ConexaoDiretaActivity.this, answer+"", Toast.LENGTH_LONG).show();
		if(answer) {
			datasource.deleteServidor(servidor);
			finish();
			
		}
	}
	
	/*
	 * classe usada para "esperar" a conexão com o servidor ssh
	 */
	public class Loadsomestuff extends AsyncTask<String, Integer, String> {
 
        
		private ProgressDialog myPd_ring;
        
		/*
		 * Executa a conexão com o servidor e espera a resposta enviando resultado para
		 * onPostExecute   
		 */
		@Override
		protected String doInBackground(String... arg0) {
            String result;
			SSHClienteConexao s = new SSHClienteConexao();
			boolean isOpen=false;
			try {
				isOpen = s.conecta(config);
				result = "Sucesso";
			} catch (Exception e) {
				Log.i("ERROR",e.getClass()+" \n"+e.getLocalizedMessage());
				result = e.getMessage()+" Erro ao conectar\n";
			}
			Log.i("conectado ?", isOpen+"");
			if(isOpen) {
				s.desconecta();
				Intent i = new Intent(getApplicationContext(),OpcoesActivity.class);
				i.putExtra("servidor", servidor);
				startActivity(i);
				finish();
			}

			return result;


		}
        
		/*
		 * (non-Javadoc)
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		@Override
		protected void onPostExecute(String abc){
			super.onPostExecute(abc);
	        
			myPd_ring.dismiss(); // termina o progress bar "Loading"
	        _toastError(abc); //mostra mensagem de retorno do doInbackgrund

		}
		
		/*
		 * Mostra progressbar enquanto espera a conexão
		 * 
		 */
		@Override
        protected void onPreExecute() {

            myPd_ring  = new ProgressDialog (ConexaoDiretaActivity.this);
            myPd_ring.setMessage("Loading");
            myPd_ring.show();

        }

	}
	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		datasource.close();
	}
	@Override
	public void onDialogCompleted2(boolean answer) {
		// TODO Auto-generated method stub
		
	}

}
