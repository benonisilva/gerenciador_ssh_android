package com.example.monografiassh2013;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import com.example.monografiassh2013.customviews.MyExpandableListAdapter;
import com.example.monografiassh2013.utils.Processo;
import com.example.monografiassh2013.utils.ProcessosFields;
import com.example.monografiassh2013.utils.ProcessosStore;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;

public class ProcessosActivity2 extends Activity {

	private String[] informacoes;
	private ProcessosStore processos;
	//private List<String> processos_string;
	//private String[] processos_string;
	ExpandableListView expListView;
	MyExpandableListAdapter expListAdapter;
	Map<String, List<Processo>> map;
	final List<String> users = new ArrayList<String>();
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.processos_2);
		expListView = (ExpandableListView) findViewById(R.id.processos_list);
		
		Intent i = getIntent();
		informacoes = (String[]) i.getStringArrayExtra("processos");
		_cabecalho(informacoes);
		_criaStoreProcessos(informacoes);
		map = processos.getListProcessos();
		users.addAll(map.keySet());
		expListAdapter = new MyExpandableListAdapter(
                this, users,map);
		expListView.setAdapter(expListAdapter);
		
	}
	
	
	private void _cabecalho(String[] inf) {
		String [] cab = inf[0].split(" ");
		
	}
	
	private void _criaStoreProcessos(String[] inf) {
		processos = new ProcessosStore();
		String[] ps;
		
		for(int i=1;i<inf.length;i++) {
			//Log.i("p", inf[i]);
			ps = inf[i].split("\\s");
			Processo p = new Processo(ps[1],ps[0],ps[2],ps[3],ps[4],ps[5]);
			processos.addUserProcess(p);
		}
		
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
	      // TODO Auto-generated method stub
	      
		getMenuInflater().inflate(R.menu.menu_processos, menu);
	    return true;	    		
	    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    
	    case R.id.itemPesquisar:
	        Log.i("menu pesquisar", "clicado");
	         
	        
	        //in
	        
	        AlertDialog.Builder builder = new AlertDialog.Builder(ProcessosActivity2.this);
	        builder.setTitle("Pesquisa por comando");

	        // Set up the input
	        final EditText input = new EditText(ProcessosActivity2.this);
	        
	        builder.setView(input);

	        // Set up the buttons
	        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { 
	            @Override
	            public void onClick(DialogInterface dialog, int which) {
	                String m_Text = input.getText().toString();
	                expListAdapter.filter(m_Text);
	            }
	        });
	        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int which) {
	                dialog.cancel();
	            }
	        });

	        builder.show();
	        
	        //end
	        //expListAdapter.filter(searchtxt);
	        return true;
	    
	    case R.id.itemPid:
	    	Log.i("menu item Pid", "clicado");
	    	//processos.sortProcessos(ProcessosFields.PID);
	    	expListAdapter.setOrder(ProcessosFields.PID);
	    	//map = processos.getListProcessos();
	    	//expListAdapter = new MyExpandableListAdapter(
	        //        this, users,map);
			//expListView.setAdapter(expListAdapter);
	    	return true;
	    	
	    case R.id.itemMem:
	    	Log.i("menu item Mem", "clicado");
	    	//processos.sortProcessos(ProcessosFields.PID);
	    	expListAdapter.setOrder(ProcessosFields.MEM);
	    	//map = processos.getListProcessos();
	    	//expListAdapter = new MyExpandableListAdapter(
	        //        this, users,map);
			//expListView.setAdapter(expListAdapter);
	    	return true;
	    	
	    case R.id.itemCpu:
	    	Log.i("menu item Cpu", "clicado");
	    	//processos.sortProcessos(ProcessosFields.PID);
	    	expListAdapter.setOrder(ProcessosFields.CPU);
	    	//map = processos.getListProcessos();
	    	//expListAdapter = new MyExpandableListAdapter(
	        //        this, users,map);
			//expListView.setAdapter(expListAdapter);
	    	return true;	
	    	
	    case R.id.itemUser:
	    	Log.i("menu item User", "clicado");
	    	//processos.sortProcessos(ProcessosFields.USER);
	    	Collection<String> unsorted  =  map.keySet();
	    	List<String> sorted = asSortedList(unsorted);
	    	users.clear();
	    	users.addAll(sorted);
			expListAdapter = new MyExpandableListAdapter(
	                this, users,map);
			expListView.setAdapter(expListAdapter);
	    	//adapter.notifyDataSetChanged();
	    	return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	public static
	<T extends Comparable<? super T>> List<T> asSortedList(Collection<T> c) {
	  List<T> list = new ArrayList<T>(c);
	  java.util.Collections.sort(list);
	  return list;
	}
		
}
