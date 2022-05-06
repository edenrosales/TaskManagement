package com.example.taskmanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TagBoxAdapter extends RecyclerView.Adapter<TagBoxAdapter.TagHolder>{

    private Context mContext;
    private int mResource;
    private List<Tag> tags = new ArrayList<>();

    public TagBoxAdapter.TagHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tag_box, parent, false);
        return new TagBoxAdapter.TagHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TagBoxAdapter.TagHolder holder, int position) {
        Tag currentTag = tags.get(position);
        holder.tagName.setText(currentTag.getName());
        holder.tagColor.setBackgroundColor(currentTag.getColor());
    }


    @Override
    public int getItemCount() {
        return tags.size();
    }


    class TagHolder extends RecyclerView.ViewHolder{
        private TextView tagName;
        private ImageView tagColor;

        public TagHolder(@NonNull View itemView) {
            super(itemView);
            tagName = itemView.findViewById(R.id.tag_text_main);
            tagColor = itemView.findViewById(R.id.imageView_tag);
        }
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
        notifyDataSetChanged();
    }

    public List<Tag> getTags(){
        return tags;
    }


}
