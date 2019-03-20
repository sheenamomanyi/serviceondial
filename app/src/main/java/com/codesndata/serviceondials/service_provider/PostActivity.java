package com.codesndata.serviceondials.service_provider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.codesndata.serviceondials.R;
import com.codesndata.serviceondials.prefs.UserInfo;
import com.codesndata.serviceondials.prefs.UserSession;

import java.util.Objects;

public class PostActivity extends AppCompatActivity {

    //Declare data fields
    private EditText service_txt, provider_txt, charge_txt, area_txt, phoneNo_txt;
    private UserSession session;
    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Connect Java fields to XML fields
        service_txt = findViewById(R.id.service_type_tx);
        provider_txt= findViewById(R.id.providerid_tx);
        charge_txt = findViewById(R.id.charge_tx);
        area_txt = findViewById(R.id.area_tx);
        phoneNo_txt= findViewById(R.id.phone_no_tx);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        session = new UserSession(this);
        userInfo = new UserInfo(this);

        if(!session.isUserLoggedin()){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        String provider = userInfo.getKeyProvider();
        String phone_no = userInfo.getKeyPhone();

        provider_txt.setText(provider);
        phoneNo_txt.setText(phone_no);
    }

    public void attemptPosting() {

        String service_type, provider, charge,  area, phone_no;
        // Store values at the time of the registration attempt.
        service_type = service_txt.getText().toString();
        provider = provider_txt.getText().toString();
        charge = charge_txt.getText().toString();
        area = area_txt.getText().toString();
        phone_no = phoneNo_txt.getText().toString();

        String method = "post_service";

        BgSync bgTask = new BgSync();
        bgTask.execute(method, service_type, provider, charge, area, phone_no);

    }

    public void popUp (View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Post a Service to Service-On-Dial?");
        alertDialogBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {

                try {
                        Toast.makeText(PostActivity.this, "Posting your Service...", Toast.LENGTH_LONG).show();
                        attemptPosting();
                } catch (Exception e) {
                }

            }
        });
        alertDialogBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(PostActivity.this, "Service NOT Posted!", Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    public void backToInfo(View view){
        Intent info = new Intent(PostActivity.this, ViewDataActivity.class);
        startActivity(info);
    }

}
