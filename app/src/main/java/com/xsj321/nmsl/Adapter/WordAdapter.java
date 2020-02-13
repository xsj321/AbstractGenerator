package com.xsj321.nmsl.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xsj321.nmsl.Data.Words;
import com.xsj321.nmsl.R;
import com.xsj321.nmsl.Util.CustomPreferencesUtil;

import java.util.ArrayList;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder>{
    private ArrayList<Words> wordList;
    private Context context;
    public WordAdapter(ArrayList<Words> wordList, Context context){
        this.wordList = wordList;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_list_item, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Words words = wordList.get(position);
        holder.listKey.setText(words.getKey());
        holder.listValue.setText(words.getValue());
        holder.listDelate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPreferencesUtil.removeWord(words.getKey(),context);
                wordList.clear();
                wordList.addAll(CustomPreferencesUtil.getALLasWordList(context));
                WordAdapter.this.notifyItemRemoved(position);
                Log.v("点击序号",String.valueOf(position));

            }
        });
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView listKey;
        TextView listValue;
        ImageView listDelate;
        RecyclerView list;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            listKey = itemView.findViewById(R.id.list_key);
            listValue = itemView.findViewById(R.id.list_value);
            listDelate = itemView.findViewById(R.id.list_delete);
            list = itemView.findViewById(R.id.word_list);
        }
    }
}
