package com.example.monografiassh2013.customviews;

import java.util.List;
import java.util.Map;

import com.example.monografiassh2013.R;
import com.example.monografiassh2013.utils.Processo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {
	 
    private Activity context;
    private Map<String, List<Processo>> processoCollections;
    private List<String> users;
 
    public MyExpandableListAdapter(Activity context, List<String> users,
            Map<String, List<Processo>> processoCollections) {
        this.context = context;
        this.processoCollections = processoCollections;
        this.users = users;
    }
 
    public Object getChild(int groupPosition, int childPosition) {
        return processoCollections.get(users.get(groupPosition)).get(childPosition);
    }
 
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
 
    public View getChildView(final int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
        final Processo processo = (Processo) getChild(groupPosition, childPosition);
        LayoutInflater inflater = context.getLayoutInflater();
 
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.child_user, null);
        }
 
        TextView item = (TextView) convertView.findViewById(R.id.processo_user);
        TextView item2 = (TextView) convertView.findViewById(R.id.processo_user2);
        TextView item3 = (TextView) convertView.findViewById(R.id.processo_user3);
        TextView item4 = (TextView) convertView.findViewById(R.id.processo_user4);
        TextView item5 = (TextView) convertView.findViewById(R.id.processo_user5);
 
       // ImageView delete = (ImageView) convertView.findViewById(R.id.delete);
        /*
        delete.setOnClickListener(new OnClickListener() {
 
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Do you want to remove?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                List<String> child =
                                    laptopCollections.get(laptops.get(groupPosition));
                                child.remove(childPosition);
                                notifyDataSetChanged();
                            }
                        });
                builder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        }); 
 */
        item.setText(processo.getPid());
        item2.setText(processo.getCpu());
        item3.setText(processo.getMen());
        item4.setText(processo.getTime());
        item5.setText("comando");
        return convertView;
    }
 
    public int getChildrenCount(int groupPosition) {
        return processoCollections.get(users.get(groupPosition)).size();
    }
 
    public Object getGroup(int groupPosition) {
        return users.get(groupPosition);
    }
 
    public int getGroupCount() {
        return users.size();
    }
 
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
 
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
        String userName = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.grup_item_user,
                    null);
        }
        TextView item = (TextView) convertView.findViewById(R.id.user);
        Log.i("item is null", item+"");
        item.setTypeface(null, Typeface.BOLD);
        item.setText(userName);
        return convertView;
    }
 
    public boolean hasStableIds() {
        return true;
    }
 
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}