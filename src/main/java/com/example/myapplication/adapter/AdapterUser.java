package com.example.myapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myapplication.APIActivity;
import com.example.myapplication.R;
import com.example.myapplication.dto.User;

import java.util.ArrayList;

public class AdapterUser extends ArrayAdapter<User> {

    Context context;
    int layoutResourceId;
    ArrayList<User> data = null;

    public AdapterUser(Context context, int layoutResourceId, ArrayList<User> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        UserHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = LayoutInflater.from(context);
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new UserHolder();
            holder.nick = (TextView)row.findViewById(R.id.nick);
            holder.desc = (TextView)row.findViewById(R.id.desc);

            row.setTag(holder);
        }
        else
        {
            holder = (UserHolder)row.getTag();
        }

        User object = data.get(position);
        holder.nick.setText(object.getNick());
        holder.desc.setText(object.getDescription());

        return row;
    }

    static class UserHolder
    {
        TextView nick;
        TextView desc;
    }
}