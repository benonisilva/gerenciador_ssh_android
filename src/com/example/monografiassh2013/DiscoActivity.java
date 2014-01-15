package com.example.monografiassh2013;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.monografiassh2013.customviews.MyExpandableListAdapter;
import com.example.monografiassh2013.customviews.MyExpandableListAdapter2;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;

/*
 * Padrao para dos as classe que recebem as informaçoes do servidor
 * todas as outras classe seguem o mesmo esquema
 * recebem da activity OpcoesActivity e populam o adapter
 * 
 * PS:refatorar para usar apenas uma classe e mudar apenas as informaçoes no adpter
 * 
 * 
 */
public class DiscoActivity extends Activity {

	private String[] informacoes; //stings com informacoes
	private Map<String, List<DiskInf>> map = new HashMap<String, List<DiskInf>>(10);
	private List<String> hds = new ArrayList<String>(10);
	private ExpandableListView expListView;
	private MyExpandableListAdapter2 expListAdapter;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_disco);
		
		Intent i = getIntent();
		informacoes = (String[]) i.getStringArrayExtra("discos"); // pega as informaçoes passadas pela Activty OpcoesActivity
		//Log.d("disk", informacoes[0].replaceFirst(".*/dev/", "haha"));
		
		_criamap();
		
		expListView = (ExpandableListView) findViewById(R.id.disk_list);
	    hds.addAll(map.keySet());
		expListAdapter = new MyExpandableListAdapter2(
                this, hds,map);
		expListView.setAdapter(expListAdapter);
	
	}
	
	
	
	
	
	private void _criamap() {
		String hd = null;
		for(String s:informacoes) {
			
			if(s.matches(".*/dev/.*:.*")) {
				hd = s.split(" ")[1].replaceFirst("/dev/", "").replace(":", "");
				Log.i("ok", hd);
				map.put(hd, new ArrayList<DiscoActivity.DiskInf>());
				
			}else {
				String[] list = s.split(" ");
				//Log.i("sub hds 1", list[0]);
				//Log.i("sub hds 2", list[1]);
				//Log.i("sub hds 3", list[2]);
				//Log.i("sub hds 4", list[3]);
				//Log.i("sub hds 5", list[4]);
				//Log.i("sub hds 6", list[5]);
				//if(list.length>=7) Log.i("sub hds 7", list[6]);
				
				if(list.length>=7) {
					DiskInf d = new DiskInf();
					d.setDevice(list[0]);
					if(list[1].contains("*")) { 
						d.setBoot("sim");
						d.setStart(list[2]);
						d.setEnd(list[3]);
						d.setBlocks(list[4]);
						d.setId(list[5]);
						d.setSystem(list[6]);
					
					}
					else {
						d.setBoot(" ");
						d.setStart(list[1]);
						d.setEnd(list[2]);
						d.setBlocks(list[3]);
						d.setId(list[4]);
						d.setSystem(list[5]+"/"+list[6]);
					}
					map.get(hd).add(d);
				}else {
					DiskInf d = new DiskInf();
					d.setDevice(list[0]);
					d.setBoot(" ");
					d.setStart(list[1]);
					d.setEnd(list[2]);
					d.setBlocks(list[3]);
					d.setId(list[4]);
					d.setSystem(list[5]);
					map.get(hd).add(d);
					
								
				}
				
				
				
			}
			
			
		}
		Log.i("map", map+"");
	}
	
	public class DiskInf{
		private String device, boot,start,end, blocks ,id,  system;

		public String getDevice() {
			return device;
		}

		public void setDevice(String device) {
			this.device = device;
		}

		public String getBoot() {
			return boot;
		}

		public void setBoot(String boot) {
			this.boot = boot;
		}

		public String getStart() {
			return start;
		}

		public void setStart(String start) {
			this.start = start;
		}

		public String getEnd() {
			return end;
		}

		public void setEnd(String end) {
			this.end = end;
		}

		public String getBlocks() {
			return blocks;
		}

		public void setBlocks(String blocks) {
			this.blocks = blocks;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getSystem() {
			return system;
		}

		public void setSystem(String system) {
			this.system = system;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("DiskInf [device=");
			builder.append(device);
			builder.append(", boot=");
			builder.append(boot);
			builder.append(", start=");
			builder.append(start);
			builder.append(", end=");
			builder.append(end);
			builder.append(", blocks=");
			builder.append(blocks);
			builder.append(", id=");
			builder.append(id);
			builder.append(", system=");
			builder.append(system);
			builder.append("]");
			return builder.toString();
		}
	}

}
