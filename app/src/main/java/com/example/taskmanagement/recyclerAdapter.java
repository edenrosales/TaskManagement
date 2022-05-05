package com.example.taskmanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder>{
    Context context;
    LinkedList<Task> input;
    public recyclerAdapter(Context context, LinkedList<Task> input){
        this.context = context;
        this.input = input;
    }
    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.each_task,parent,false);
        return new recyclerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {
        holder.txtName.setText(input.get(position).getName());
        holder.txtDes.setText(input.get(position).getDescription());
        holder.txtStart.setText(Integer.valueOf(input.get(position).getStart()).toString());
        holder.txtEnd.setText(Integer.valueOf(input.get(position).getEnd()).toString());
    }

    @Override
    public int getItemCount() {
        return input.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtName,txtDes,txtStart,txtEnd;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            txtName = itemView.findViewById(R.id.task_list_name);
            txtDes = itemView.findViewById(R.id.task_list_description);
            txtStart = itemView.findViewById(R.id.task_list_start);
            txtEnd = itemView.findViewById(R.id.task_list_end);
        }
    }
}