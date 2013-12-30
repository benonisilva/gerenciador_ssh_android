package com.example.monografiassh2013.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.util.Log;

public class ProcessosStore {

	private List<Processo> processos = new ArrayList<Processo>();

	public List<Processo> getProcessos() {
		return processos;
	}

	public void addProcesso(Processo p) {
		processos.add(p);
	}
	

	public void sortProcessos(final ProcessosFields f){
		Collections.sort (processos, new Comparator<Object>() {  
	        public int compare(Object o1, Object o2) {  
	            Processo p1 = (Processo) o1;  
	            Processo p2 = (Processo) o2;  
	            switch (f) {
				case USER:
					return p1.getUser().compareTo(p2.getUser());
					
					//break;
                
				default:
					return Integer.parseInt(p1.getPid())-Integer.parseInt(p2.getPid());
					//break;
				}
	             
	        }  
	    });  
	    //Log.i("processos order",processos.toString());
		//return processos;
	}

	
	public List<String> toStrings() {
		StringBuilder builder = new StringBuilder();
		//builder.append("ProcessosStore [getProcessos()=");
		builder.append(getProcessos());
		//builder.append("]");
		String [] ss = builder.toString().replace("[", "").replace(",", "").split("\n");
		List<String> l = Arrays.asList(ss);
		return  l;
	}
	
	
	
}
