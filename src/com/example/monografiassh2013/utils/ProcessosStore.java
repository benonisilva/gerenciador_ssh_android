package com.example.monografiassh2013.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;

public class ProcessosStore {

	private List<Processo> processos = new ArrayList<Processo>();
	private Map<String,List<Processo>> map_processos_user = new HashMap<String, List<Processo>>();

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
					Log.i("processos order by user ",getListProcessos()+"");
					return p1.getUser().compareTo(p2.getUser());
					
					//break;
                
				default:
					Log.i("processos order default ",getListProcessos()+"");
					return Integer.parseInt(p2.getPid())-Integer.parseInt(p1.getPid());
					//break;
				}
	             
	        }  
	    });  
	    Log.i("processos order",getListProcessos()+"");
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
	
	public void addUserProcess(Processo p) {
		if(map_processos_user.containsKey(p.getUser())) {
			map_processos_user.get(p.getUser()).add(p);
		}
		else {
			ArrayList<Processo> array = new ArrayList<Processo>();
			array.add(p);
			map_processos_user.put(p.getUser(), array);
		}
	}
	
	public Map<String,List<Processo>> getListProcessos(){
		return map_processos_user;
	}
	
	
}
