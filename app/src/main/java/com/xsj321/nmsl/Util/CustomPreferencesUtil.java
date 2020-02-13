package com.xsj321.nmsl.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.xsj321.nmsl.CustomPhraseActivity.CustomPhrase;
import com.xsj321.nmsl.Data.Words;

import java.util.ArrayList;
import java.util.Map;

public class CustomPreferencesUtil {
    private final static String SAVE_XML = "save_words";
    private static SharedPreferences SharedPreferencesCustomPhrase;
    private CustomPreferencesUtil(){

    }

    public static void setWord(String key,String value,Context context){
        SharedPreferencesCustomPhrase = context.getSharedPreferences(SAVE_XML,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = SharedPreferencesCustomPhrase.edit();
        editor.putString(key,value);
        editor.commit();
    }

    public static String getWord(String key,Context context){
        SharedPreferencesCustomPhrase = context.getSharedPreferences(SAVE_XML,Context.MODE_PRIVATE);
        String res = SharedPreferencesCustomPhrase.getString(key,null);

        Log.d("所有内容",SharedPreferencesCustomPhrase.getAll().toString());
        return res;
    }

    public static ArrayList<Words> getALLasWordList(Context context){
        ArrayList<Words> wordList = new ArrayList();
        SharedPreferencesCustomPhrase = context.getSharedPreferences(SAVE_XML,Context.MODE_PRIVATE);
        if (SharedPreferencesCustomPhrase == null)return null;
        Map<String,?> wordsMap = SharedPreferencesCustomPhrase.getAll();

        for (String key : wordsMap.keySet()){
            wordList.add(new Words(key,wordsMap.get(key).toString()));
        }
        return wordList;
    }

    public static void removeWord(String key,Context context){
        SharedPreferencesCustomPhrase = context.getSharedPreferences(SAVE_XML,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = SharedPreferencesCustomPhrase.edit();
        editor.remove(key);
        editor.commit();
    }
}
