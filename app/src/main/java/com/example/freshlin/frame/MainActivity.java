package com.example.freshlin.frame;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int xx = 1;
        setContentView(R.layout.activity_main);
        context = this;

        findViewById(R.id.itbtnTest).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, TestActivity.class));
            }
        });
    }
}
