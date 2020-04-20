/*
Custom Adapter for Multi-column ListView
cite: http://www.exceptionbound.com/programming-tut/android-tutorial/add-android-multicolumn-listview-android
 */

package com.example.cs480projectteam3;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter{

    private ArrayList<HashMap<String, String>> list;
    private Activity activity;
    private static final String FIRST_COLUMN="dept";
    private static final String SECOND_COLUMN="phone";
    private static final String THIRD_COLUMN="email";

    public ListViewAdapter(Activity activity,ArrayList<HashMap<String, String>> list){
        super();
        this.activity=activity;
        this.list=list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    private class ViewHolder{
        TextView deptTxt;
        TextView phoneTxt;
        TextView emailTxt;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        final ViewHolder holder;

        LayoutInflater inflater=activity.getLayoutInflater();

        if(convertView == null){

            convertView=inflater.inflate(R.layout.contact_list_row, null);
            holder=new ViewHolder();

            holder.deptTxt=(TextView) convertView.findViewById(R.id.dept_item);
            holder.phoneTxt=(TextView) convertView.findViewById(R.id.phone_item);
            holder.emailTxt=(TextView) convertView.findViewById(R.id.email_item);

            convertView.setTag(holder);
        }else{

            holder=(ViewHolder) convertView.getTag();
        }

        HashMap<String, String> map=list.get(position);
        holder.deptTxt.setText(map.get(FIRST_COLUMN));
        holder.phoneTxt.setText(map.get(SECOND_COLUMN));
        holder.emailTxt.setText(map.get(THIRD_COLUMN));

        // Implicit intent to dial or text when phone number is clicked
        holder.phoneTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selection = holder.phoneTxt.getText().toString();
                if (! selection.equals("")) {
                    Uri tel = Uri.parse("tel:" + selection);
                    Uri smsTel = Uri.parse("smsto:" + selection);
                    Intent dial = new Intent(Intent.ACTION_VIEW, tel);
                    Intent sms = new Intent(Intent.ACTION_VIEW, smsTel);
                    // Create chooser intent to allow user to choose to dial or send sms
                    Intent chooser = Intent.createChooser(dial, "Select app");
                    chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {sms});
                    v.getContext().startActivity(chooser);
                }
            }
        });
        // Implicit intent to open email app when email is clicked
        holder.emailTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selection = holder.emailTxt.getText().toString();
                if (! selection.equals("")) {
                    Uri email = Uri.parse("mailto:" + selection);
                    Intent intent = new Intent(Intent.ACTION_VIEW, email);
                    v.getContext().startActivity(intent);
                }
            }
        });
        return convertView;
    }

}
