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
    private TagBoxAdapter.OnItemClickListener listener;


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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //current position we selected
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(tags.get(position));
                    }
                }
            });
        }
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
        notifyDataSetChanged();
    }

    public interface  OnItemClickListener{
        void onItemClick(Tag tag);
    }

    public void setOnItemClickListener(TagBoxAdapter.OnItemClickListener listener){
        this.listener = listener;
    }


    public List<Tag> getTags(){
        return tags;
    }


}
