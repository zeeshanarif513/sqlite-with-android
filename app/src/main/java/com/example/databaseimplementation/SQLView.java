package com.example.databaseimplementation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SQLView extends AppCompatActivity {
    SqLiteDatabase db;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlview);
        db = new SqLiteDatabase(this);

        tv = (TextView) findViewById(R.id.textView);
        String res = db.viewData();
        tv.setText(res);
    }
}
