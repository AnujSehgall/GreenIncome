package com.anuj.greenincome;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Splash extends AppCompatActivity {

    final Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        LayoutInflater inflater = LayoutInflater.from(Splash.this);
        View subView = inflater.inflate(R.layout.custom_dialog, null);

        TextView next = (TextView) findViewById(R.id.next);
        final EditText subEditText = (EditText)subView.findViewById(R.id.et_input);
        next.setText("Press to continue");
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Splash.this,Menu_Act.class);
                startActivity(i);
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("AlertDialog");
        builder.setView(subView);
        AlertDialog alertDialog = builder.create();

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //textInfo.setText(subEditText.getText().toString());
                Toast.makeText(getApplicationContext(),subEditText.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Splash.this, "Cancel", Toast.LENGTH_LONG).show();
            }
        });

        builder.show();
    }
}
