package com.xsj321.nmsl.Data;

import android.app.Activity;
import android.content.res.AssetManager;
import android.util.Log;

import com.xsj321.nmsl.MainActivity;
import com.xsj321.nmsl.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class Dictionary{
    public JSONObject emojiJSON;
    public JSONObject pingyingJSON;
    public Dictionary() throws JSONException, IOException {

        InputStream emojiIN = MainActivity.getMainContext().getResources().openRawResource(R.raw.emoji);
        byte[] bytesE = new byte[emojiIN.available()];
        emojiIN.read(bytesE);
        String EMOJI = new String(bytesE);
        emojiJSON = new JSONObject(EMOJI);


        InputStream pingyingIN =MainActivity.getMainContext().getResources().openRawResource(R.raw.pingying);
        byte[] bytesP = new byte[pingyingIN.available()];
        pingyingIN.read(bytesP);
        String PINGYING = new String(bytesP);
        Log.e("获取数据",PINGYING);
        pingyingJSON = new JSONObject(PINGYING);
    }

}
