package com.example.mytodolist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    EditText name,pass;
    DbHelper helper;
    String table_name="ganesh_table";
    String loginname;
    String loginpass,loginemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //for hiding action bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        name= findViewById(R.id.email_login);
        pass= findViewById(R.id.password_login);

        helper=new DbHelper(this);
    }

    public void login(View view) {
        String uname=name.getText().toString();
        String upass=pass.getText().toString();
        if (TextUtils.isEmpty(uname) || TextUtils.isEmpty(upass))
            Toast.makeText(getApplicationContext(),"All fields Required", Toast.LENGTH_SHORT).show();

        SQLiteDatabase db=helper.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor=db.query(table_name,new String[]{"name","password","email"},null,null,null,null,null);


        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                 loginname=cursor.getString(0);
                loginpass=cursor.getString(1);
                loginemail=cursor.getString(2);

                if((loginname.equals(name.getText().toString())) && (loginpass.equals(pass.getText().toString())) ){
                    Toast.makeText(getApplicationContext(),"Login Successfully", Toast.LENGTH_SHORT).show();

                    //passsing data to profile activity
                    Intent i=new Intent(getApplicationContext(),MainActivity.class);
                    i.putExtra("name",loginname);
                    i.putExtra("email",loginemail);
                    i.putExtra("password",loginpass);
                    startActivity(i);
                }
                if(!(loginname.equals(name.getText().toString())) && !(loginpass.equals(pass.getText().toString())) ){
                    Toast.makeText(getApplicationContext(),"Login not successfully", Toast.LENGTH_SHORT).show();
                }

            } //end of while

        }//end of if
        else
            Toast.makeText(getApplicationContext(),"Login Error", Toast.LENGTH_SHORT).show();

    }

    public void signup(View view) {
        Intent i=new Intent(getApplicationContext(),SignUp.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
       finishAffinity();
    }
}