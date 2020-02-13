package com.xsj321.nmsl;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.xsj321.nmsl.CustomPhraseActivity.CustomPhrase;
import com.xsj321.nmsl.Util.CopyButtonLibrary;
import com.xsj321.nmsl.Util.TextUtil;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static Context MainContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainContext = this;
        final EditText InputTextBox = findViewById(R.id.find_emoji_text).findViewById(R.id.main_input);
        final Button ShareButton = findViewById(R.id.send);
        final EditText TranslationRes = findViewById(R.id.res);
        final TextUtil textUtil = new TextUtil();


        InputTextBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    TranslationRes.setText(textUtil.FindEmoji(InputTextBox.getText().toString()));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        TranslationRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CopyButtonLibrary copyButtonLibrary= new CopyButtonLibrary(getApplicationContext(),TranslationRes);
                copyButtonLibrary.init();
            }
        });

        ShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_TEXT,TranslationRes.getText().toString());
                intent.setType("text/plain");
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,0,1,getResources().getString(R.string.add_custom_phrase));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getGroupId();
        switch (id){
            case 0:
                Intent  intent = new Intent(this, CustomPhrase.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    public static Context getMainContext() {
        return MainContext;
    }


}
