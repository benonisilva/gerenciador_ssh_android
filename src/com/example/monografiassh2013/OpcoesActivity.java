package com.example.monografiassh2013;

import com.example.monografiassh2013.bd.dao.Servidor;
import com.example.monografiassh2013.conexao.ConfiguracaoConexao;
import com.example.monografiassh2013.conexao.SSHClienteConexao;
import com.example.monografiassh2013.utils.FormataEntrada;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class OpcoesActivity extends Activity {
    private Servidor servidor;
	
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
	}
	private View.OnClickListener ListenerBotaoProcesso(){
		
		return new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				//Intent i = new Intent(getApplicationContext(),ProcessosActivity.class);
                //startActivity(i);
                
                ConfiguracaoConexao config = new ConfiguracaoConexao(servidor.getHost(), servidor.getUser(), servidor.getPass(), servidor.getPorta());
                SSHClienteConexao ssh = new SSHClienteConexao();
                ssh.conecta(config);
                
                try {
					String processos = ssh.exec("ps -A");
                	Log.i("processos", processos.split("\n")[1]);
                	_toastTeste(processos);
                	Intent i = new Intent(getApplicationContext(),ProcessosActivity.class);
                	i.putExtra("processos", processos.split("\n"));
                	startActivity(i);
                	
				} catch (Exception e) {

				}
                finally {
                	ssh.desconecta();
                }

			}
			
		};
	}
	private View.OnClickListener ListenerBotaoUsuarios(){
		
		return new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				//Intent i = new Intent(getApplicationContext(),UsuariosActivity.class);
                //startActivity(i);
                ConfiguracaoConexao config = new ConfiguracaoConexao(servidor.getHost(), servidor.getUser(), servidor.getPass(), servidor.getPorta());
                SSHClienteConexao ssh = new SSHClienteConexao();
                ssh.conecta(config);
                
                try {
					String users = ssh.exec("who");
                	Log.i("users", users.split("\n")[1]);
                	_toastTeste(users);
                	Intent i = new Intent(getApplicationContext(),UsuariosActivity.class);
                	i.putExtra("users", users.split("\n"));
                	startActivity(i);
                	
				} catch (Exception e) {

				}
                finally {
                	ssh.desconecta();
                }

			}
			
		};
	}
	private View.OnClickListener ListenerBotaoSO(){
		
		return new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),SOActivity.class);
                startActivity(i);

			}
			
		};
	}
	private View.OnClickListener ListenerBotaoHardware(){
		
		return new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),HardwareActivity.class);
                startActivity(i);

			}
			
		};
	}
	private View.OnClickListener ListenerBotaoRedes(){
		
		return new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				
                ConfiguracaoConexao config = new ConfiguracaoConexao(servidor.getHost(), servidor.getUser(), servidor.getPass(), servidor.getPorta());
                SSHClienteConexao ssh = new SSHClienteConexao();
                ssh.conecta(config);
                
                try {
					String redes = ssh.exec("ifconfig");
                	Log.i("redes", redes.split("\n")[1]);
                	_toastTeste(redes);
                	Intent i = new Intent(getApplicationContext(),RedeActivity.class);
                	i.putExtra("redes", redes.split("\n"));
                	startActivity(i);
                	
				} catch (Exception e) {

				}
                finally {
                	ssh.desconecta();
                }
                
			}
			
		};
	}
	private View.OnClickListener ListenerBotaoDesligar(){
		
		return new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),DesligarActivity.class);
                startActivity(i);

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

}
