package com.example.monografiassh2013.conexao;

/*
 * Comandos enviados ao servidor
 */
public final class Comandos {
	public static final String LIST_DISK = "fdisk  -l | awk '{ print $1 \" \" $2 \" \" $3 \" \" $4}'";
	public static final String LIST_PROCESSOS = "ps -auxf |  awk '{print $1 \" \"$2 \" \"$3\" \" $4\" \" $10 \" \"$11}'";
	public static final String LIST_USUARIOS = "python a.py";
	public static final String LIST_REDES = "/sbin/ifconfig";
	public static final String INFO_SISTEMA = "uname -a | awk '{print \"\t\"$6 \"  \"$7\"\t\"$3 }'";
	public static final String INFO_MEMORIA = "free -m | awk '{print $1 \"   \"$2\"   \"$3\" }'";
	public static final String DESLIGA = "shutdown -h now";
	public static final Object REINICIAR = "shutdown -r now";
}