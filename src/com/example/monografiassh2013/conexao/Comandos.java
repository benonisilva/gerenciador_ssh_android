package com.example.monografiassh2013.conexao;

/*
 * Comandos enviados ao servidor
 */
public final class Comandos {
	public static final String LIST_DISK = "fdisk  -l | grep dev | awk '{ print $1 \" \" $2 \" \" $3 \" \" $4 \" \" $5 \" \" $6 \" \" $7 \" \" $8 \" \" $9 \" \" $10}'";
	public static final String LIST_PROCESSOS = "ps -aux |  awk '{print $1 \" \"$2 \" \"$3\" \" $4\" \" $10 \" \"$11}'";
	public static final String LIST_USUARIOS = "who -a";
	public static final String LIST_REDES = "/sbin/ifconfig";
	public static final String INFO_SISTEMA = "uname -a | awk '{print $1\"\t\" $2\"\t\" $3\"\t\" $4\"\t\" $5\" \" $6\" \" $7\" \" $8\" \" $9\" " +
			" \" $10\" \" $11\" \" $12\"\t\" $13\"\t\" $14\"\t\" $15\"\t\" $16\"\t\" $17\"\t\"}'";
	public static final String INFO_MEMORIA = "free -m | awk '{print $1 \" \"$2 \" \" $3 \" \" $4 \" \" $5 \" \"$6 \" \"}'";
	public static final String DESLIGA = "shutdown -h now";
	public static final String REINICIAR = "shutdown -r now";
}