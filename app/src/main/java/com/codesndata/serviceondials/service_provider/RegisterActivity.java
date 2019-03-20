package com.codesndata.serviceondials.service_provider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codesndata.serviceondials.R;

public class RegisterActivity extends AppCompatActivity {

    //Declare data fields
    private EditText identity_txt, location_txt, phone_txt, qualifications_txt, username_txt, password_txt;
    String identity, location, phone, qualifications, uname, pword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Connect Java fields to XML fields
        identity_txt= findViewById(R.id.provider_id_tx);
        location_txt = findViewById(R.id.where_located_tx);
        phone_txt= findViewById(R.id.phone_no_tx);
        qualifications_txt = findViewById(R.id.qualification_tx);
        username_txt = findViewById(R.id.username_tx);
        password_txt = findViewById(R.id.password_tx);


        Button quitButton = findViewById(R.id.toLogin_button);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent quit = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(quit);
            }
        });

    }

        public void attemptReg() {

            // Store values at the time of the registration attempt.
            identity = identity_txt.getText().toString();
            location = location_txt.getText().toString();
            phone = phone_txt.getText().toString();
            qualifications = qualifications_txt.getText().toString();
            uname = username_txt.getText().toString();
            pword = password_txt.getText().toString();

            String method = "register";

            BgSync bgTask = new BgSync();
            bgTask.execute(method, identity, location, phone, qualifications, uname, pword);
            finish();

        }


        public void popUp (View view){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Submit data to Service-On-Dial?");
            alertDialogBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {

                    try {

                        try {
                            Toast.makeText(RegisterActivity.this, "Submitting data for registration...", Toast.LENGTH_LONG).show();
                            attemptReg();
                        } catch (Exception x) {
                            finish();
                        }
                        supportFinishAfterTransition();
                    } catch (Exception e) {
                        finish();
                    }

                }
            });
            alertDialogBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(RegisterActivity.this, "Data NOT Submitted!", Toast.LENGTH_LONG).show();
                    finish();
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        }
}
