package com.trishasofttech.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button btn_view, btn_save;
EditText etname, etemail;
TextView tv_data;
SQLiteDatabase sd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etemail = findViewById(R.id.etemail);
        etname = findViewById(R.id.etname);
        btn_save = findViewById(R.id.btn_save);
        btn_view  = findViewById(R.id.btn_view);
        tv_data = findViewById(R.id.tv_data);

        /*create or open the sqlite database*/
        sd = openOrCreateDatabase("virudb", 0, null );
        /*to create table inside the database*/
        sd.execSQL("create table if not exists virutable (name varchar(200), email varchar(250))");

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*to insert the data into virutable*/
                sd.execSQL("insert into virutable values ('"+etname.getText().toString()+"', " +
                        "'"+etemail.getText().toString()+"')");
                Toast.makeText(MainActivity.this, "Insertion done", Toast.LENGTH_SHORT).show();
                etemail.setText("");
                etname.setText("");
            }
        });

        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c = sd.rawQuery("select * from virutable", null);
                c.moveToFirst();
                tv_data.setText(c.getString(0)+ "\n"+ c.getString(1));
            }
        });
    }
}