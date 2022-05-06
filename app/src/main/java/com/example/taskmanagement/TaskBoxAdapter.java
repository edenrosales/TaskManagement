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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TaskBoxAdapter extends RecyclerView.Adapter<TaskBoxAdapter.TaskHolder> {
    private Context mContext;
    private int mResource;
    private List<Task> tasks = new ArrayList<>();

    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_task, parent, false);
        return new TaskHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskBoxAdapter.TaskHolder holder, int position) {
        Task currentTask = tasks.get(position);
        holder.textViewName.setText(currentTask.getName());
        holder.textViewDescription.setText(currentTask.getDescription());
        holder.textViewStart.setText(String.valueOf(currentTask.getStart()));
        holder.textViewEnd.setText(String.valueOf(currentTask.getEnd()));
    }


    @Override
    public int getItemCount() {
        return tasks.size();
    }


    class TaskHolder extends RecyclerView.ViewHolder{
        private TextView textViewName;
        private TextView textViewDescription;
        private TextView textViewStart;
        private TextView textViewEnd;

        public TaskHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.task_list_name);
            textViewDescription = itemView.findViewById(R.id.task_list_description);
            textViewStart = itemView.findViewById(R.id.task_list_start);
            textViewEnd = itemView.findViewById(R.id.task_list_end);
        }
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }


}
