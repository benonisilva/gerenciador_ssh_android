package com.example.monografiassh2013.conexao;

/*
 * Classe de configuração usada em em SSHClienteConexao
 */

public class ConfiguracaoConexao {
	
	private String host,ip,username,password;
	private int porta;

	public String getHost() {
		return host;
	}

	public ConfiguracaoConexao(String host, String username,
			String password, int porta) {
		super();
		this.host = host;
		this.username = username;
		this.password = password;
		this.porta = porta;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPorta() {
		return porta;
	}

	public void setPorta(int porta) {
		this.porta = porta;
	}
}
