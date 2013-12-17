package com.example.monografiassh2013.conexao;

/*
 * Comandos enviados ao servidor
 */
public final class Comandos {
	public static final String LIST_DISK = "df -H | grep -vE '^Filesystem|tmpfs|cdrom|none|udev' | awk '{ print $1 \" \" $2 \" \" $3 \" \" $4}'";
	public static final String LIST_PROCESSOS = "ps -a -u | awk '{print $1 \"   \"$2\"   \"$3\"   \"$4 }'";
	public static final String LIST_USUARIOS = "ps -a -u | awk '{print $1 \"\t\" $3 \"\t\" $4}' | sort -nr | uniq -u";
	public static final String LIST_REDES = "ifconfig | grep -vE '^lo' | awk '{ print $1 \" \" $2 \" \" $3 \" \" $4}'";
	public static final String INFO_SISTEMA = "uname -a | awk '{print $1 \"   \"$2\"   \"$3\"   \"$4 }'";
	public static final String INFO_MEMORIA = "free -m | awk '{print $1 \"   \"$2\"   \"$3\"   \"$4 }'";
}
