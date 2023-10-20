package com.example.studentmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    EditText ed1, ed2, ed3 ,edAge;
    Button b1,b2;
    Button b4;



   // @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ed1 = findViewById(R.id.Homename);
        ed2 = findViewById(R.id.Homecourse);
        ed3 = findViewById(R.id.homefee);

        edAge =findViewById(R.id.homeAge);

        b2 = findViewById(R.id.Btnview);
        b1=   findViewById(R.id.btnadd);
       b4 = findViewById(R.id.btnlogout);






        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getApplicationContext(),view.class);
               startActivity(i);



            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {
               Intent i = new Intent(getApplicationContext(),Loginactivity.class);
                startActivity(i);
           }
        });



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert();
            }
        });
    }


    public void insert(){
        try {
            int age = 0;
            String name= ed1.getText().toString();
            String course= ed2.getText().toString();
            String fee= ed3.getText().toString();
           // String age=edAge.getText().toString();

           age = Integer.parseInt(edAge.getText().toString());
           // String age = edAge.getText().toString();

            SQLiteDatabase db= openOrCreateDatabase("NIBM", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS records(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR, course VARCHAR,fee VARCHAR,age INTEGER)");

            String sql= "insert into records(name,course,fee,age) values(?,?,?,?)";
            SQLiteStatement  statement =db.compileStatement(sql);
            statement.bindString(1,name);
            statement.bindString(2,course);
            statement.bindString(3,fee);
            statement.bindLong(4, age);

            statement.execute();
            Toast.makeText(this, "Recorded added", Toast.LENGTH_SHORT).show();

            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
           // edAge.setText(""); this change
            edAge.setText(String.valueOf(age));
            ed1.requestFocus();



        }catch(Exception ex)
        {
            Toast.makeText(this, "Record Fail", Toast.LENGTH_SHORT).show();
        }
    }

}