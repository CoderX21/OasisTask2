package com.example.mytodolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class splashScreen extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_splash_screen);

        tv = findViewById(R.id.textView);


        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(1500);
                    tv.setVisibility(View.VISIBLE);
                    sleep(2000);


                }//end of try


                catch (Exception e) {
                    e.printStackTrace();

                }//end of catch
                finally {

                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);

                }//end of finally

            }//end of run
        };
        thread.start();      //end of thread nd calling run method()
    }
    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}