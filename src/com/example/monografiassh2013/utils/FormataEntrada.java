package com.example.monografiassh2013.utils;

import java.util.HashMap;

import android.util.Log;

public class FormataEntrada {
    
	public static String[] FormataSaidaIfconfig(String[] s) {
		
		HashMap<String, String> m = new HashMap<String, String>();
		for(String str:s) {
			m.put(str.split("-")[0], str.split("-")[0]);
		}
		Log.i("sss", s[1].split("-")[0]);
		
		return s;
	}
	
public static String FormataSaidaComando(String s1) {
		String s2=null;
		if(s1!=null) {
			s2=s1;
		}
		//Log.i("sss", s[1].split("-")[0]);
		
		return s2;
	}
}
