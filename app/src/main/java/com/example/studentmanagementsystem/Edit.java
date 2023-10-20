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

public class Edit extends AppCompatActivity {
    EditText ed1, ed2, ed3, edAge, ed4;
    Button b1, b2;
    Button b3;


    //@SuppressLint("MissingInflatedId")
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ed1 = findViewById(R.id.Homename);
        ed2 = findViewById(R.id.Homecourse);
        ed3 = findViewById(R.id.homefee);
        ed4 = findViewById(R.id.id);

        edAge = findViewById(R.id.homeAge);

        b2 = findViewById(R.id.Btnview);
        b1 = findViewById(R.id.btnadd);
        b3 = findViewById(R.id.Editback);

        Intent i = getIntent();

        String t1 = i.getStringExtra("id").toString();
        String t2 = i.getStringExtra("name").toString();
        String t3 = i.getStringExtra("course").toString();
        String t4 = i.getStringExtra("fee").toString();
        int age = i.getIntExtra("age", 0);

        //  String t5 = i.getStringExtra("age").toString();

        ed4.setText(t1);
        ed1.setText(t2);
        ed2.setText(t3);
        ed3.setText(t4);
        edAge.setText(String.valueOf(age));

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), View.class);
                startActivity(i);
            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Delete();
            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Edit1();
            }
        });
    }

    public void Delete() {
        try {

            String id = ed4.getText().toString();
            // String age=edAge.getText().toString();


            // String age = edAge.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("NIBM", Context.MODE_PRIVATE, null);


            String sql = "delete from records where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1, id);
            statement.execute();
            Toast.makeText(this, "Rrecord delete", Toast.LENGTH_SHORT).show();

            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            edAge.setText("");
            ed1.requestFocus();


        } catch (Exception ex) {
            Toast.makeText(this, "Record Fail", Toast.LENGTH_SHORT).show();
        }
    }




    public void Edit1()
    {
        try {
            int age = 0;
            String name= ed1.getText().toString();
            String course= ed2.getText().toString();
            String fee= ed3.getText().toString();
            String id= ed4.getText().toString();
            // String age=edAge.getText().toString();

            age = Integer.parseInt(edAge.getText().toString());
            // String age = edAge.getText().toString();

            SQLiteDatabase db= openOrCreateDatabase("NIBM", Context.MODE_PRIVATE,null);


            String sql= "update records set name =?,course=?, fee=? ,age=? where id = ?";
            SQLiteStatement statement =db.compileStatement(sql);
            statement.bindString(1,name);
            statement.bindString(2,course);
            statement.bindString(3,fee);
            statement.bindLong(4, age);
            statement.bindString(5,id);


            statement.execute();
            Toast.makeText(this, "Recorded updated", Toast.LENGTH_SHORT).show();

            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            edAge.setText("");
            ed1.requestFocus();



        }catch(Exception ex)
        {
            Toast.makeText(this, "Record Fail", Toast.LENGTH_SHORT).show();
        }
    }
}
