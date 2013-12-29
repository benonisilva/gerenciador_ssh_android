package com.example.monografiassh2013;

import com.example.monografiassh2013.bd.dao.Servidor;
import com.example.monografiassh2013.bd.dao.ServidorDataSource;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/*
 * cria novo perfil(servidor) e adiciona ao bd
 */
public class NovoPerfilActivity extends Activity {
    
	private ServidorDataSource datasource;
	//private String nome,host,user,pass;
	//private int porta;
	private EditText ed_nome,ed_host,ed_user,ed_pass,ed_porta;
	private Servidor servidor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_novo_perfil);
		Button botaoConectar = (Button) findViewById(R.id.btn_conectar);
		//pega conteudo de edittext
		ed_nome = (EditText) findViewById(R.id.etxt_nome_servidor);
		ed_host = (EditText) findViewById(R.id.eTxtHost);
		ed_user = (EditText) findViewById(R.id.etxt_usuario);
		ed_pass = (EditText) findViewById(R.id.etxt_senha);
		ed_porta = (EditText) findViewById(R.id.etxt_porta);
		//
		
		Intent i = getIntent();
		servidor = (Servidor) i.getParcelableExtra("servidor");
		
		
		if(servidor.getId()>0) {
			_setEditTexts(servidor);
			botaoConectar.setOnClickListener(ListenerBotaoEditar());
		}
		else {
			//botao salva no bd
			botaoConectar.setOnClickListener( ListenerBotaoSalvar());
		}
		
		
		
		
		//abre bd
		datasource = new ServidorDataSource(this);
	    datasource.open();
	}
	private View.OnClickListener ListenerBotaoSalvar(){
		
		return new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				//TODO: Salvar os dados do perfil
				//Intent i = new Intent(getApplicationContext(),OpcoesActivity.class);
                //startActivity(i);
				
				
				//mensagem de criaçao com sucesso
				if(true) {
					Servidor s = datasource.createServidor(ed_nome.getText().toString(), 
							ed_host.getText().toString(), 
							ed_user.getText().toString(), 
							ed_pass.getText().toString(), 
							Integer.parseInt(ed_porta.getText().toString()));
					
					Context context = getApplicationContext();
					CharSequence text = s.getNome()+" criado com sucesso";
					int duration = Toast.LENGTH_SHORT;
					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
				}
				
			}
			
		};
	}
	
	private View.OnClickListener ListenerBotaoEditar(){
		return new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				datasource.deleteServidor(servidor);
				Servidor s=datasource.createServidor(ed_nome.getText().toString(), 
							ed_host.getText().toString(), 
							ed_user.getText().toString(), 
							ed_pass.getText().toString(), 
							Integer.parseInt(ed_porta.getText().toString()));
				
				Context context = getApplicationContext();
				CharSequence text = s.getNome()+" Editado";
				int duration = Toast.LENGTH_SHORT;
				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
				finish();
				
			}
			
		};
	}
	
	@Override
	  protected void onResume() {
	    datasource.open();
	    super.onResume();
	  }

	  @Override
	  protected void onPause() {
	    datasource.close();
	    super.onPause();
	  }
	  
	  private void _setEditTexts(Servidor servidor) {
		  ed_nome.setText(servidor.getNome());
		  ed_host.setText(servidor.getHost());
		  ed_pass.setText(servidor.getPass());
		  ed_user.setText(servidor.getUser());
		  ed_porta.setText(servidor.getPorta()+"");
	  }
	  
}
