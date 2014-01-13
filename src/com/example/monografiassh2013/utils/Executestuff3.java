package com.example.monografiassh2013.utils;

import com.example.monografiassh2013.conexao.Comandos;

import android.os.AsyncTask;
import android.util.Log;

/*
 * classe auxiliar usada para executar os comandos em background e enviar apara activity que apresentara
 * o resultado
 */
public class Executestuff3 extends AsyncTask<Object, String, String> {
      
	private static int i;

	@Override
	protected String doInBackground(Object... arg0) {
        
		
		Log.i("i= ", i+" i");
		i++;
		 /*   
        String comando,c,sep;//comando recebido e separador usado na formataçao
        comando= (String)arg0[1];
        c = (String)arg0[2];
        sep = (String) arg0[3];
        Intent i = (Intent)arg0[0];//PS:varias intentes fazem a mesma coisa, possivel uso de herança iria enxugar o codigo
        */
		/*
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
        		Log.i("desligar/reiniciar","ok");
        	}
        	else {
        		i.putExtra(c, inf.split(sep)); //envia a lista do resultado de comando
        	}
        	
        	if(!c.equals("")) {
        		startActivity(i);
        	}
        	
        	//finish();
		} catch (Exception e) {
                _toastError(e.getMessage());
		}
        finally {
        	ssh.desconecta();
        }
		*/
		return "Executed";


	}
    
	//idem de ConexaoDiretaActivity
	@Override
	protected void onPostExecute(String abc){
		super.onPostExecute(abc);
		Log.i("on pos execute","pos");
        //myPd_ring.dismiss();
		
        if(i<=10) {
        	Executestuff3 e = new Executestuff3();
	        e.execute(null,Comandos.LIST_PROCESSOS,"processos","\n");
        }

	}
	
	//idem de ConexaoDiretaActivity
	@Override
    protected void onPreExecute() {
		
		Log.i("on pre execute","pre");
		try {
		    Thread.sleep(2500);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		i++;
        //myPd_ring  = new ProgressDialog (UsuariosActivity.this);
        //myPd_ring.setMessage("Loading");
        //myPd_ring.show();
		

    }

}