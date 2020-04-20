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

        // Dial phone number if phone number is clicked
        holder.phoneTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selection = holder.phoneTxt.getText().toString();
                if (! selection.equals("")) {
                    Uri tel = Uri.parse("tel:" + selection);
                    Intent dial = new Intent(Intent.ACTION_DIAL, tel);
                    v.getContext().startActivity(dial);
                }
            }
        });
        // New email message if email is clicked
        holder.emailTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selection = holder.emailTxt.getText().toString();
                if (! selection.equals("")) {
                    Uri email = Uri.parse("mailto:" + selection);
                    Intent send = new Intent(Intent.ACTION_SENDTO, email);
                    v.getContext().startActivity(send);
                }
            }
        });
        return convertView;
    }

}
