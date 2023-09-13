package com.example.mytodolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends AppCompatActivity {
    EditText name,email,pass,phone;
    DbHelper helper;
    String table_name="ganesh_table";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);

        //for hiding action bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        name= findViewById(R.id.name);
        email= findViewById(R.id.email_reg);
        pass= findViewById(R.id.password_reg);
        phone= findViewById(R.id.mobile);

        helper=new DbHelper(this);


    }
    private Boolean validateUsername()
    {
        String val= name.getText().toString();
        if(val.isEmpty()) {
            Toast.makeText(getApplicationContext(), "please fill the filed", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{

            return true;

        }

    }

    private Boolean validateEmail() {
        String val= email.getText().toString();
        String emailpattern= "[a-zA-Z0-9._]+@[a-z]+\\.+[a-z]+";
        if(val.isEmpty()){
            Toast.makeText(getApplicationContext(),"please fill the filed",Toast.LENGTH_SHORT).show();
            return false;

        }else if(!val.matches(emailpattern)){
            Toast.makeText(getApplicationContext(),"please enter the valid email",Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;

        }

    }

    private Boolean validatePhoneNo() {
        String val= phone.getText().toString();
        if(val.isEmpty()){
            Toast.makeText(getApplicationContext(),"please fill the filed",Toast.LENGTH_SHORT).show();
            return false;

        }else{
            return true;

        }

    }

    private Boolean validatePassword() {
        String val= pass.getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";
        if(val.isEmpty()){
            Toast.makeText(getApplicationContext(),"please fill the filed",Toast.LENGTH_SHORT).show();
            return false;

        } else if (!val.matches(passwordVal)) {
            Toast.makeText(getApplicationContext(),"password is too weak",Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;

        }

    }


    public void register(View view) {
        if(!validatePassword() | !validatePhoneNo() | !validateEmail() | !validateUsername()){
            return;
        }
        SQLiteDatabase db=helper.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put("name",name.getText().toString());
        cv.put("email",email.getText().toString());
        cv.put("password",pass.getText().toString());

        long row_id= db.insert(table_name,null,cv);
        if(row_id>0) {
            Toast.makeText(this, "Record inserted", Toast.LENGTH_LONG).show();
            Log.d("INFO","Record is added , id:"+row_id);

            Intent i=new Intent(getApplicationContext(),Login.class);
            startActivity(i);
        }
        else
            Toast.makeText(this,"Record not inserted",Toast.LENGTH_LONG).show();
    }

    public void login(View view) {
        Intent i=new Intent(getApplicationContext(),Login.class);
        startActivity(i);
    }
    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}