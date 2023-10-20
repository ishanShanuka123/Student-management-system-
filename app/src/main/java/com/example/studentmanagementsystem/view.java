package com.example.studentmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class view extends AppCompatActivity {

    ListView lst1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;
    Button b1,b2;






    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        b2 = findViewById(R.id.listback);
        b1 = findViewById(R.id.listlogout);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Loginactivity.class);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(i);
            }
        });



        lst1 = findViewById(R.id.list1);
        SQLiteDatabase db = openOrCreateDatabase("NIBM", Context.MODE_PRIVATE, null);

        final Cursor c = db.rawQuery("select * from records", null);
        int id = c.getColumnIndex("id");
        int name = c.getColumnIndex("name");
        int course = c.getColumnIndex("course");
        int fee = c.getColumnIndex("fee");
        int age = c.getColumnIndex("age");
        titles.clear();

        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, titles);
        lst1.setAdapter(arrayAdapter);

        final ArrayList<student> stud = new ArrayList<student>();

        if (c.moveToFirst()) {
            do {

                student stu = new student();
                stu.id = c.getString(id);
                stu.name = c.getString(name);
                stu.course = c.getString(course);
                stu.fee = c.getString(fee);
                stu.age = c.getInt(age);

                stud.add(stu);


                titles.add(c.getString(id) + "\t\t" + c.getString(name) + "\t\t" + c.getString(course) + "\t\t" + c.getString(fee) + "\t\t" + + c.getInt(age));


            }
            while (c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            lst1.invalidateViews();
        }
        lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position , long id) {
                String aa= titles.get(position).toString();

               student stu = stud.get(position);
                Intent i = new Intent(getApplicationContext(),Edit.class);

                i.putExtra("id",stu.id);
                i.putExtra("name",stu.name);
                i.putExtra("course",stu.course);
                i.putExtra("fee",stu.fee);
                i.putExtra("age",stu.age);

                startActivity(i);




            }
        });


    }
}

