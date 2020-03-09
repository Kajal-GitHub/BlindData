package com.example.blinddata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    DatabaseHelper databaseHelper;
    Button btn_reg,btn_login;
    EditText txtname,mobile_no,email,password;
    TextView display_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openHelper = new DatabaseHelper(this);
        databaseHelper = new DatabaseHelper(this);
        btn_reg = (Button)findViewById(R.id.register);
        txtname=(EditText)findViewById(R.id.name);
        mobile_no=(EditText)findViewById(R.id.mobileno);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);

        display_name=(TextView)findViewById(R.id.display_name);
        btn_login = (Button) findViewById(R.id.login);

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              db=openHelper.getWritableDatabase();

            String fname = null, mobile = null,email_id = null ,pass = null;

              if(TextUtils.isEmpty(txtname.getText()))
              {
                 txtname.setError("Enter Name");
                 txtname.requestFocus();
              }
              else
              {
                   fname = txtname.getText().toString();
              }

                if(TextUtils.isEmpty(mobile_no.getText()))
                {
                    mobile_no.setError("Enter Mobile");
                    mobile_no.requestFocus();
                }
                else
                {
                    mobile = mobile_no.getText().toString();
                }
                if(TextUtils.isEmpty(email.getText()))
                {
                    email.setError("Enter Email ID");
                    email.requestFocus();
                }
                else
                {
                    email_id= email.getText().toString();
                }

                if(TextUtils.isEmpty(password.getText()))
                {
                    password.setError("Enter Password");
                    password.requestFocus();
                }
                else {
                    pass = password.getText().toString();
                }


                insertdata(fname,mobile,email_id,pass);
                Toast.makeText(getApplicationContext(),"regiter succesfully",Toast.LENGTH_LONG).show();

            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });


             Cursor cursor = databaseHelper.display_name();
             if(cursor.getCount()==0)
             {
                 Toast.makeText(getApplicationContext(),"No data found",Toast.LENGTH_LONG).show();

             }
             else {
                 while (cursor.moveToNext())
                 {
                     display_name.setText(cursor.getString(1));
                 }
             }

    }
    public void insertdata(String fname, String mobile ,String email_id ,String pass)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_2,fname);
        contentValues.put(DatabaseHelper.COL_3,mobile);
        contentValues.put(DatabaseHelper.COL_4,email_id);
        contentValues.put(DatabaseHelper.COL_5,pass);
        long id  = db.insert(DatabaseHelper.TABLE_NAME ,null ,contentValues);

    }





}
