package com.xsj321.nmsl.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xsj321.nmsl.Data.Words;
import com.xsj321.nmsl.R;

import java.util.ArrayList;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder>{
    private ArrayList<Words> wordList;
    public WordAdapter(ArrayList<Words> wordList){
        this.wordList = wordList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_list_item, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Words words = wordList.get(position);
        holder.listKey.setText(words.getKey());
        holder.listValue.setText(words.getValue());
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView listKey;
        TextView listValue;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            listKey = itemView.findViewById(R.id.list_key);
            listValue = itemView.findViewById(R.id.list_value);
        }
    }
}
