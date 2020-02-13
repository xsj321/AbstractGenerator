package com.xsj321.nmsl.CustomPhraseActivity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.opengl.ETC1;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.xsj321.nmsl.Adapter.WordAdapter;
import com.xsj321.nmsl.Data.Dictionary;
import com.xsj321.nmsl.Data.Words;
import com.xsj321.nmsl.R;
import com.xsj321.nmsl.Util.CustomPreferencesUtil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;

public class CustomPhrase extends AppCompatActivity {
    WordAdapter wordAdapter;
    ArrayList<Words> wordsArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_phrase);
        RecyclerView wordList = findViewById(R.id.word_list);
        wordList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        wordList.setLayoutManager(layoutManager);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInputDialog();
            }
        });
        wordsArrayList = CustomPreferencesUtil.getALLasWordList(this);
        wordAdapter = new WordAdapter(wordsArrayList,this);
        wordList.setAdapter(wordAdapter);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//添加默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用

    }

    private void showInputDialog(){
       final View in =  View.inflate(this,R.layout.sample_custom_phrase_input,null);
       new AlertDialog.Builder(this)
               .setView(in)
               .setPositiveButton(getResources().getString(R.string.accept), new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       EditText keyView = in.findViewById(R.id.custom_key);
                       EditText valueView = in.findViewById(R.id.custom_value);
                       String key = keyView.getText().toString();
                       String value = valueView.getText().toString();
                       if (key.equals("") || value.equals("")){
                           Toast.makeText(getBaseContext(),"你有东西没填哦",Toast.LENGTH_SHORT).show();
                       }
                       else {
                           CustomPreferencesUtil.setWord(key,value,getBaseContext());
                           wordsArrayList.clear();
                           ArrayList<Words> words = CustomPreferencesUtil.getALLasWordList(getBaseContext());
                           wordsArrayList.addAll(words);
                           wordAdapter.notifyItemChanged(words.size());
                       }
                   }
               }).setNegativeButton(getResources().getString(R.string.cancel),null)
               .show();
    }

}
