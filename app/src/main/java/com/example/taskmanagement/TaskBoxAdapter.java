package com.example.taskmanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource, parent, false);
        TextView txtName = convertView.findViewById(R.id.task_list_name);
        txtName.setText(getItem(position).getName());
        return convertView;
    }
}
