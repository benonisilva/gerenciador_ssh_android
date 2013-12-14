package com.example.monografiassh2013.conexao;

import java.security.PublicKey;

import net.schmizz.sshj.transport.verification.HostKeyVerifier;

/*
 * resolve problema da checagem de rsa
 */
class NullHostKeyVerifier implements HostKeyVerifier {
   
	@Override
	public boolean verify(String arg0, int arg1, PublicKey arg2) {
		
		return true;
	}        
}