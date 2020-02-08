package com.xsj321.nmsl.Util;

import android.util.Log;

import com.xsj321.nmsl.Data.Dictionary;

import org.json.JSONException;

import java.io.IOException;

public class TextUtil {
    public String FindEmoji(String InputString ) throws IOException, JSONException {
        Log.v("文本长度",String.valueOf(InputString.length()));
        Dictionary dictionary = new Dictionary();
        StringBuilder FeedBack = new StringBuilder();
        for (int i = 0;i<InputString.length();i++){
            String NowPingYing = FindPingYing(String.valueOf(InputString.charAt(i)));
            String NextPingYing = null;
            if (i!=InputString.length()-1){
                NextPingYing = FindPingYing(String.valueOf(InputString.charAt(i+1)));
            }
            StringBuilder FindBuilder = new StringBuilder();
            try {
                FindBuilder.append(dictionary.emojiJSON.getString(NowPingYing+NextPingYing));
                i++;
            }
            catch (Exception  e){
                Log.d("查询状态","不是词语"+e.toString());
                try {
                    FindBuilder.append( dictionary.emojiJSON.getString(NowPingYing));
                }
                catch (Exception e1){
                    Log.e("查询状态","不是词语且不是单词");
                    FindBuilder.append(InputString.charAt(i));
                }
            }
            finally {
                FeedBack.append(FindBuilder);
            }
        }
        return FeedBack.toString();
    }

    private  String FindPingYing(String inputChar) throws IOException, JSONException {
        String PingYing = "";
        Dictionary dictionary = new Dictionary();
        try {
            PingYing = dictionary.pingyingJSON.getString(inputChar);
        }
        catch (Exception e){
            Log.e("解析错误","无法找到文字拼音,可能为生僻字");
        }
        return PingYing;
    }
}
