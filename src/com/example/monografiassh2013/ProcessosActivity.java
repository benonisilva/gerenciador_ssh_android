package com.example.monografiassh2013;

import java.util.List;

import com.example.monografiassh2013.utils.Processo;
import com.example.monografiassh2013.utils.ProcessosFields;
import com.example.monografiassh2013.utils.ProcessosStore;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

public class ProcessosActivity extends ListActivity {

	private String[] informacoes;
	private String pid,user,cpu,mem,time,command;
	private ProcessosStore processos;
	private ArrayAdapter<Processo> adapter;
	//private List<String> processos_string;
	//private String[] processos_string;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_processos);
		
		Intent i = getIntent();
		informacoes = (String[]) i.getStringArrayExtra("processos");
		//temp
		//Log.i("inf",informacoes[1].split(" ")[0]);
		//Log.i("inf",informacoes[1].split(" ")[1]);
		//Log.i("inf",informacoes[1].split(" ")[2]);
		//Log.i("inf",informacoes[1].split(" ")[3]);
		//Log.i("inf",informacoes[1].split(" ")[4]);
		//Log.i("inf",informacoes[1].split(" ")[5]);
		
		_cabecalho(informacoes);
		_criaStoreProcessos(informacoes);
		//Log.i("before",processos.toString());
		//processos.sortProcessos(ProcessosFields.USER);
		//processos.sortProcessos(ProcessosFields.PID);
		
		//temp
		//Log.i("processos ",processos.getProcessos().size()+"");
		
		//final List<String> processos_string = processos.toStrings();
		
		
		adapter = new ArrayAdapter<Processo>(this,
		        android.R.layout.simple_list_item_2, processos.getProcessos());
		    setListAdapter(adapter);
		
	}
	
	
	private void _cabecalho(String[] inf) {
		String [] cab = inf[0].split(" ");
		this.user = cab[0];
		this.pid = cab[1];
		this.cpu = cab[2];
		this.mem = cab[3];
		this.time = cab[4];
		this.command = cab[5];
		
	}
	
	private void _criaStoreProcessos(String[] inf) {
		processos = new ProcessosStore();
		String[] ps;
		
		for(int i=1;i<inf.length;i++) {
			ps = inf[i].split(" ");
			Processo p = new Processo(ps[1],ps[0],ps[2],ps[3],ps[4],ps[5]);
			processos.addProcesso(p);
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
	        return true;
	    case R.id.itemPid:
	    	Log.i("menu item Pid", "clicado");
	    	processos.sortProcessos(ProcessosFields.PID);
	    	adapter = new ArrayAdapter<Processo>(this,
			        android.R.layout.simple_list_item_1, processos.getProcessos());
			    setListAdapter(adapter);
	    	return true;
	    case R.id.itemUser:
	    	Log.i("menu item User", "clicado");
	    	processos.sortProcessos(ProcessosFields.USER);
	    	adapter = new ArrayAdapter<Processo>(this,
			        android.R.layout.simple_list_item_1, processos.getProcessos());
			    setListAdapter(adapter);
	    	//adapter.notifyDataSetChanged();
	    	return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	
}
