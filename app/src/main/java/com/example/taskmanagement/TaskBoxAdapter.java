package com.example.taskmanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class TaskBoxAdapter extends ArrayAdapter<Task>  {
    private Context mContext;
    private int mResource;

    public TaskBoxAdapter(@NonNull Context context, int resource, @NonNull LinkedList<Task> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getName();
        String description = getItem(position).getDescription();
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource, parent, false);
        TextView txtName = convertView.findViewById(R.id.task_list_name);
        TextView txtDes = convertView.findViewById(R.id.task_list_description);
        txtName.setText(name);
        txtDes.setText(description);

        //txtName.setText(getItem(position).getName());
        return convertView;
    }
}
