package com.example.monografiassh2013.conexao;

import java.io.IOException;
import java.io.InputStream;

import net.schmizz.sshj.AndroidConfig;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.common.IOUtils;
import net.schmizz.sshj.connection.ConnectionException;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.transport.TransportException;
import android.util.Log;

//Classe usada para conexao e envio de comandos
public class SSHClienteConexao {

	final SSHClient ssh = new SSHClient(new AndroidConfig());
	
	public boolean conecta(ConfiguracaoConexao c) throws Exception {
		
		
		ssh.addHostKeyVerifier(new NullHostKeyVerifier());
		boolean isOpen= false;
		
		//try {
		
    		ssh.connect(c.getHost(),c.getPorta());
            ssh.authPassword(c.getUsername(), c.getPassword().toCharArray());
            final net.schmizz.sshj.connection.channel.direct.Session session = ssh.startSession();
        	isOpen = session.isOpen();
            //
            //Log.i("logado", isOpen+"");
            
            
            session.close();
        	//ssh.disconnect();
			
		//} catch (Exception e) {
		//	Log.i("exception", e.getMessage());
			
		//}
		return isOpen; 
	}
	
	/*
	 * executa os comandos passados
	 * PS: nao tratar as excessoes para apresentar ao usuario nas classes
	 * que chamarem esse metodo
	 */
	public String exec(String command) {
		//temp
		Log.i("exec", "inicio");
		
		net.schmizz.sshj.connection.channel.direct.Session session = null;
		try {
			session = ssh.startSession();
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		} catch (TransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		} 
		
		Session.Command cmd = null;
		try {
			cmd = session.exec(command);
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
        Log.i("exec", "antes join");
        try {
			cmd.join();
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
        //temp
        Log.i("exec", "pos join");

        InputStream in = (InputStream) cmd.getInputStream();
        //temp
       
        String saida = null;
		try {
        	//Log.i("input", IOUtils.readFully(in).toString());
        	saida = IOUtils.readFully(in).toString();
        	//System.out.println(IOUtils.readFully(in));
        	in.close();
        	session.close();
        	return saida;
        	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
        
        /*
        InputStream er = (InputStream) cmd.getErrorStream();
        
        ByteArrayOutputStream saida = null;
		try {
			Log.i("output",IOUtils.readFully(er).toString());
			saida = IOUtils.readFully(in);
		} catch (IOException e) {
			Log.i("97", "error");
			e.printStackTrace();
		}
        try {
			System.out.println(IOUtils.readFully(er));
		} catch (IOException e) {
			Log.i("103", "error");
			e.printStackTrace();
		}*/
        try {
			session.close();
		} catch (TransportException e) {
			Log.i("109", "error");
			e.printStackTrace();
		} catch (ConnectionException e) {
			Log.i("112", "error");
			e.printStackTrace();
		}
        
        return saida;
    }
	/*
	 * fecha conexao aberta
	 */
	public boolean desconecta() {
		try {
			this.ssh.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
