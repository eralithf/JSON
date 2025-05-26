package com.example.usuarios;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<User> lista;

    public UserAdapter(Context context, ArrayList<User> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(android.R.layout.simple_list_item_2, parent, false);

            holder = new ViewHolder();
            holder.t1 = convertView.findViewById(android.R.id.text1);
            holder.t2 = convertView.findViewById(android.R.id.text2);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        User u = lista.get(i);
        holder.t1.setText(u.getName());
        holder.t2.setText(u.getEmail());

        return convertView;
    }

    static class ViewHolder {
        TextView t1;
        TextView t2;
    }
}
