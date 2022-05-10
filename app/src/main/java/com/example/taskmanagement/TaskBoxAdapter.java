package com.example.taskmanagement;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TaskBoxAdapter extends RecyclerView.Adapter<TaskBoxAdapter.TaskHolder> {
    private Context mContext;
    private int mResource;
    private List<Task> tasks = new ArrayList<>();
    private OnItemClickListener listener;


    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_task, parent, false);
        return new TaskHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskBoxAdapter.TaskHolder holder, int position) {
        Task currentTask = tasks.get(position);
        holder.textViewName.setText(currentTask.getName());
        holder.textViewDescription.setText(currentTask.getDescription());
        holder.textViewStart.setText(String.valueOf(currentTask.getStart()) + ":00");
        holder.textViewEnd.setText(String.valueOf(currentTask.getEnd()) + ":00");
        holder.textViewDueDate.setText(currentTask.getDateToString());
        Drawable drawable = holder.taskColor.getDrawable();
        if(MainActivity.colorValues.containsKey(currentTask.getTag().getName())){
            drawable.setColorFilter(MainActivity.colorValues.get(currentTask.getTag().getName()), PorterDuff.Mode.OVERLAY);
        }
        //drawable.setColorFilter(currentTask.getTag().getColor(), PorterDuff.Mode.SCREEN);
        if(currentTask.getCompleted()){
            holder.taskComplete.setBackgroundColor(Color.parseColor("#00FF00"));
        }
        else{
            holder.taskComplete.setBackgroundColor(Color.parseColor("#000000"));
        }

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
        private TextView textViewDueDate;
        private ImageView taskColor;
        private TextView taskComplete;

        public TaskHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.task_list_name);
            textViewDescription = itemView.findViewById(R.id.task_list_description);
            textViewStart = itemView.findViewById(R.id.task_list_start);
            textViewEnd = itemView.findViewById(R.id.task_list_end);
            textViewDueDate = itemView.findViewById(R.id.task_list_due_date_text);
            taskColor = itemView.findViewById(R.id.task_background);
            taskComplete = itemView.findViewById(R.id.isCompleted);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //current position we selected
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(tasks.get(position));
                    }
                }
            });
//            itemView.setOnTouchListener(new View.OnTouchListener(){
//                GestureDetector gestureDetector = new GestureDetector(mContext.getApplicationContext(), new GestureDetector.SimpleOnGestureListener()){
//                    @Override
//                    public void setOnDoubleTapListener(OnDoubleTapListener onDoubleTapListener) {
//                        System.out.println("double tap working");
//                        super.setOnDoubleTapListener(onDoubleTapListener);
//                    }
//                };
//                @Override
//                public boolean onTouch(View view, MotionEvent motionEvent) {
//                    gestureDetector.onTouchEvent(motionEvent);
//                    return false;
//                }
//            });
        }
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }
    public interface OnItemClickListener{
        void onItemClick(Task task);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }



}
