package com.example.monografiassh2013;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.monografiassh2013.customviews.MyExpandableListAdapter;
import com.example.monografiassh2013.utils.Processo;
import com.example.monografiassh2013.utils.ProcessosFields;
import com.example.monografiassh2013.utils.ProcessosStore;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;

public class ProcessosActivity2 extends Activity {

	private String[] informacoes;
	private String pid,user,cpu,mem,time,command;
	private ProcessosStore processos;
	private ArrayAdapter<Processo> adapter;
	//private List<String> processos_string;
	//private String[] processos_string;
	ExpandableListView expListView;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.processos_2);
		
		Intent i = getIntent();
		informacoes = (String[]) i.getStringArrayExtra("processos");
		_cabecalho(informacoes);
		_criaStoreProcessos(informacoes);
		List<String> users = new ArrayList<String>();
		final Map<String, List<Processo>> map = processos.getListProcessos();
		expListView = (ExpandableListView) findViewById(R.id.processos_list);
		users.addAll(map.keySet());
		final MyExpandableListAdapter expListAdapter = new MyExpandableListAdapter(
                this, users,map);
		expListView.setAdapter(expListAdapter);
		
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
			processos.addUserProcess(p);
		}
		
	}
		
}
