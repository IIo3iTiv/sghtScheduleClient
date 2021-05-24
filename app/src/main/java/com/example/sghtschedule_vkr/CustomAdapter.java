package com.example.sghtschedule_vkr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAdapter extends BaseAdapter {

    String[] listItem;
    Context context;
    LayoutInflater inflater;

    public CustomAdapter(Context context, String[] listItem) {
        this.context = context;
        this.listItem = listItem;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return listItem.length;
    }

    @Override
    public Object getItem(int i) {
        return listItem[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.custom_spinner, null);
        TextView txt = (TextView) view.findViewById(R.id.spinner_item_text);
        txt.setText(listItem[i]);
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.custom_spinner_dropdown, null);

        TextView txt = convertView.findViewById(R.id.spinner_item_text);
        txt.setText(listItem[position]);

        if (position == 0) {
            txt.setHeight(0);
            txt.setVisibility(convertView.GONE);
        }


        return convertView;
    }
}
