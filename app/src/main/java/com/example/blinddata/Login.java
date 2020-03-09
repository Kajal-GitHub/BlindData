package com.example.blinddata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class Login extends AppCompatActivity {
Button btn_login ,btn_voice;
SQLiteDatabase db;
SQLiteOpenHelper openHelper;
EditText email,pass;
Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        openHelper = new DatabaseHelper(this);
        db=openHelper.getReadableDatabase();
        btn_login =(Button) findViewById(R.id.login);
        email=(EditText)findViewById(R.id.email);
        pass=(EditText)findViewById(R.id.password);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_id =  email.getText().toString();
                String password = pass.getText().toString();
                cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL_4 + " =? AND " + DatabaseHelper.COL_5 + " =? " , new String[] {email_id , password});
                if(cursor != null)
                {
                    if (cursor.getCount()>0)
                    {
                        cursor.moveToNext();
                        Toast.makeText(getApplicationContext(),"login succesfully",Toast.LENGTH_LONG).show();

                    }
                    else {
                        Toast.makeText(getApplicationContext(),"login error",Toast.LENGTH_LONG).show();

                    }
                }
            }
        });


        btn_voice =(Button)findViewById(R.id.voice);
        btn_voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SpeechToText.class);
                startActivity(intent);
            }
        });
    }

}
