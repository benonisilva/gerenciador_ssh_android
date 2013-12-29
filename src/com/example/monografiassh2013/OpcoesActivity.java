package com.example.monografiassh2013;

import com.example.monografiassh2013.bd.dao.Servidor;
import com.example.monografiassh2013.conexao.Comandos;
import com.example.monografiassh2013.conexao.ConfiguracaoConexao;
import com.example.monografiassh2013.conexao.SSHClienteConexao;
import com.example.monografiassh2013.utils.ConfirmAlert;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/*
 * apresenta todas as opcoes de comandos
 */
public class OpcoesActivity extends Activity implements ConfirmAlert.DialogReturn {
    private Servidor servidor;
	public ProgressDialog myPd_ring;
	private ConfirmAlert confirm;
	
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//set botoes
		setContentView(R.layout.activity_opcoes);
		
		Button botaoProcesso = (Button) findViewById(R.id.btn_processo);
		botaoProcesso.setOnClickListener( ListenerBotaoProcesso());
		
		Button botaoUsuarios = (Button) findViewById(R.id.btn_usuarios);
		botaoUsuarios.setOnClickListener( ListenerBotaoUsuarios());
		
		Button botaoSistema = (Button) findViewById(R.id.btn_sistema);
		botaoSistema.setOnClickListener( ListenerBotaoSO());
		
		Button botaoHardware = (Button) findViewById(R.id.btn_hardware);
		botaoHardware.setOnClickListener( ListenerBotaoHardware());
		
		Button botaoRedes = (Button) findViewById(R.id.btn_redes);
		botaoRedes.setOnClickListener( ListenerBotaoRedes());
		
		Button botaoDesligar = (Button) findViewById(R.id.btn_desligar);
		botaoDesligar.setOnClickListener( ListenerBotaoDesligar());
		
		Button botaoReiniciar = (Button) findViewById(R.id.btn_reiniciar);
		botaoReiniciar.setOnClickListener( ListenerBotaoReiniciar());
		
		//
		
		
		//pega servidor de ConexaoDireta
		Intent i = getIntent();
		servidor = (Servidor) i.getParcelableExtra("servidor");
		
		//temp
		Log.i("servidor", servidor.getNome());
		Log.i("host", servidor.getHost());
		Log.i("user", servidor.getUser());
		Log.i("pass", servidor.getPass());
		Log.i("port", String.valueOf(servidor.getPorta()));
		
		//myPd_ring =  (ProgressDialog) findViewById(R.id.progressBar1);
		//alerta usado para confimar deletar
		confirm = new ConfirmAlert();
		confirm.setListener(this);
	}
	/*
	 * botao que executa o comando usando a classe auxiliar Executestuff
	 * todas as outras opcoes seguem o mesmo padrao
	 * 
	 * PS: refatorar para diminuir o codigo usando apenas um listner e pegando o id do botao
	 * usando um switch
	 */
    private View.OnClickListener ListenerBotaoProcesso(){
		
		return new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),ProcessosActivity.class);
                //v.getId();            
                Executestuff e = new Executestuff();
                e.execute(i,Comandos.LIST_PROCESSOS,"processos","\n");

			}
			
		};
	}
	private View.OnClickListener ListenerBotaoUsuarios(){
		
		return new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),UsuariosActivity.class);
                //startActivity(i);
				Executestuff e = new Executestuff();
                e.execute(i,Comandos.LIST_USUARIOS,"users","\n");
				/*
				ConfiguracaoConexao config = new ConfiguracaoConexao(servidor.getHost(), servidor.getUser(), servidor.getPass(), servidor.getPorta());
                SSHClienteConexao ssh = new SSHClienteConexao();
                
                
                
                try {
                	ssh.conecta(config);
                	String users = ssh.exec(Comandos.LIST_USUARIOS);
                	Log.i("users", users.split("\n")+"");
                	_toastTeste(users);
                	Intent i = new Intent(getApplicationContext(),UsuariosActivity.class);
                	i.putExtra("users", users.split("\n"));
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
	private View.OnClickListener ListenerBotaoSO(){
		
		return new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),SOActivity.class);
                //startActivity(i);
                
				Executestuff e = new Executestuff();
                e.execute(i,Comandos.INFO_SISTEMA,"so","\t");
				
				/*
				ConfiguracaoConexao config = new ConfiguracaoConexao(servidor.getHost(), servidor.getUser(), servidor.getPass(), servidor.getPorta());
                SSHClienteConexao ssh = new SSHClienteConexao();
                
                
                try {
                	ssh.conecta(config);
                	String users = ssh.exec(Comandos.INFO_SISTEMA);
                	Log.i("so", users.split("\t")+"");
                	_toastTeste(users);
                	Intent i = new Intent(getApplicationContext(),SOActivity.class);
                	i.putExtra("so", users.split("\t"));
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
	private View.OnClickListener ListenerBotaoHardware(){
		
		return new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),HardwareActivity.class);
                //startActivity(i);
				Executestuff e = new Executestuff();
                e.execute(i,Comandos.INFO_SISTEMA,"servidor","\t");
				
				
				/*
                ConfiguracaoConexao config = new ConfiguracaoConexao(servidor.getHost(), servidor.getUser(), servidor.getPass(), servidor.getPorta());
                SSHClienteConexao ssh = new SSHClienteConexao();
                
                
                try {
                	ssh.conecta(config);
                	//String users = ssh.exec(Comandos.INFO_SISTEMA);
                	//Log.i("so", users.split("\t")+"");
                	//_toastTeste(users);
                	Intent i = new Intent(getApplicationContext(),HardwareActivity.class);
                	i.putExtra("servidor", servidor);
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
	private View.OnClickListener ListenerBotaoRedes(){
		
		return new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),RedeActivity.class);
				
				Executestuff e = new Executestuff();
                e.execute(i,Comandos.LIST_REDES,"redes","\n");
				/*
                ConfiguracaoConexao config = new ConfiguracaoConexao(servidor.getHost(), servidor.getUser(), servidor.getPass(), servidor.getPorta());
                SSHClienteConexao ssh = new SSHClienteConexao();
                
                
                try {
                	ssh.conecta(config);
                	String redes = ssh.exec(Comandos.LIST_REDES);
                	Log.i("redes", redes.split("\n")+"");
                	_toastTeste(redes);
                	
                	i.putExtra("redes", redes.split("\n"));
                	startActivity(i);
                	
				} catch (Exception e) {
                     _toastError(e.getMessage());
				}
                finally {
                	ssh.desconecta();
                } */
                
			}
			
		};
	}
	private View.OnClickListener ListenerBotaoDesligar(){
		
		return new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				_confirm(v.getContext(),"Certeza que vai desligar?");

			}
			
		};
	}
	
private View.OnClickListener ListenerBotaoReiniciar(){
		
		return new View.OnClickListener(){

			@Override
			public void onClick(View v) {
			Log.i("lister","clicked");
				_confirmRe(v.getContext(),"Certeza que vai reiniciar?");

			}
			
		};
	}
	
	private void _toastTeste(String s) {
		Context context = getApplicationContext();
		CharSequence text = s;
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
	
	/*
	 * classe auxiliar usada para executar os comandos em background e enviar apara activity que apresentara
	 * o resultado
	 */
	public class Executestuff extends AsyncTask<Object, String, String> {
          
		
		

		@Override
		protected String doInBackground(Object... arg0) {
            
			ConfiguracaoConexao config = new ConfiguracaoConexao(servidor.getHost(), servidor.getUser(), servidor.getPass(), servidor.getPorta());
            SSHClienteConexao ssh = new SSHClienteConexao();
            
            String comando,c,sep;//comando recebido e separador usado na formataçao
            comando= (String)arg0[1];
            c = (String)arg0[2];
            sep = (String) arg0[3];
            Intent i = (Intent)arg0[0];//PS:varias intentes fazem a mesma coisa, possivel uso de herança iria enxugar o codigo
            
            try {
            	ssh.conecta(config);//conecta
            	String inf = ssh.exec(comando);//executa comando e devolve resultado
            	Log.i(c, inf.length()+"");//separa para apresentar como lista
            	//pequena gambiarra usada pois a activity Hardware nao recebe um comando 
            	if(c.equals("servidor")) {
            		i.putExtra(c, servidor);//enviar servidor para HardwareActivity
            		//finish();
            	}
            	else if(c.equals("")) {
            		
            	}
            	else {
            		i.putExtra(c, inf.split(sep)); //envia a lista do resultado de comando
            	}
            	
            	startActivity(i);
            	//finish();
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
	        myPd_ring.dismiss();

		}
		
		//idem de ConexaoDiretaActivity
		@Override
        protected void onPreExecute() {

            myPd_ring  = new ProgressDialog (OpcoesActivity.this);
            myPd_ring.setMessage("Loading");
            myPd_ring.show();

        }

	}
	
	 /*
     * metodo usado mostrar opçao de confirmação ao desligar um servidor	
     */
	private void _confirm(Context context, String msg) {
		AlertDialog dialog = new AlertDialog.Builder(context).create();
		dialog.setTitle(msg);
		dialog.setMessage("Escolha Sim ou Não");
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
	
	@Override
	public void onDialogCompleted(boolean answer) {
		Toast.makeText(OpcoesActivity.this, answer+"", Toast.LENGTH_LONG).show();
		if(answer) {
			Executestuff e = new Executestuff();
			e.execute(null,Comandos.DESLIGA,"","");
			//finish();
			
		}
	}
	@Override
	public void onDialogCompleted2(boolean answer) {
		// TODO Auto-generated method stub
		Toast.makeText(OpcoesActivity.this, answer+"", Toast.LENGTH_LONG).show();
		if(answer) {
			Executestuff e = new Executestuff();
			e.execute(null,Comandos.REINICIAR,"","");
			//finish();
			
		}
	}
	private void _confirmRe(Context context, String msg) {
		AlertDialog dialog = new AlertDialog.Builder(context).create();
		dialog.setTitle(msg);
		dialog.setMessage("Escolha Sim ou Não");
		dialog.setCancelable(false);
		dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Sim", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int buttonId) {
				confirm.getListener().onDialogCompleted2(true);
			}
		});
		dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Não", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int buttonId) {
				confirm.getListener().onDialogCompleted2(false);
			}
		});
		dialog.setIcon(android.R.drawable.ic_dialog_alert);
		dialog.show();
	}

}
