package com.example.studentmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Loginactivity extends AppCompatActivity {
    EditText edUsername, edPassword;
    Button btn;
    TextView tv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);
        edUsername = findViewById(R.id.txtname);
        edPassword=findViewById(R.id.txtpassword);
        btn=findViewById(R.id.btnlogin);
        tv=findViewById(R.id.Register);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUsername.getText().toString();
                String password= edPassword.getText().toString();
                Database db = new Database(getApplicationContext(),"NIBM",null,1);
                if (username.length()==0 || password.length()==0){
                    Toast.makeText(Loginactivity.this, "Please fill all details", Toast.LENGTH_SHORT).show();
                }else{
                    if (db.login(username,password)==1){

                        Toast.makeText(getApplicationContext(),"LOGIN SUCESS",Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences= getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username",username);
                        editor.apply();

                        startActivity(new Intent(Loginactivity.this,HomeActivity.class));
                    }else {
                        Toast.makeText(getApplicationContext(),"Invalid Username and password",Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Loginactivity.this,RegisterActivity.class));
            }
        });
    }
}