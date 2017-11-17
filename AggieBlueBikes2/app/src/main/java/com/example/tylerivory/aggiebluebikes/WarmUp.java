package com.example.tylerivory.aggiebluebikes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WarmUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warm_up);

    }

    public void change(View view)
    {
        Intent intent = new Intent(WarmUp.this, MainActivity.class);
        startActivity(intent);
    }

}
