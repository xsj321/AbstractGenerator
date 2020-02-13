package com.xsj321.nmsl.CustomView;

import android.content.Context;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.xsj321.nmsl.R;

public class MainInputBox extends LinearLayout {

    private EditText editText;
    private ImageView imageButton;

    public MainInputBox(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.custom_main_input_box,this);
        editText =  findViewById(R.id.main_input);
        imageButton = findViewById(R.id.delete_button);

        imageButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });
    }

    public Editable getText() {
        return editText.getText();
    }
}
