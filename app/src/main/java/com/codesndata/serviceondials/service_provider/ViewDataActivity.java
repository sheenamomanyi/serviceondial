package com.codesndata.serviceondials.service_provider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.codesndata.serviceondials.prefs.UserInfo;
import com.codesndata.serviceondials.prefs.UserSession;
import com.codesndata.serviceondials.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ViewDataActivity extends AppCompatActivity {
    private EditText identity_txt, location_txt, phone_txt, qualifications_txt, username_txt, password_txt;
    private UserSession session;
    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        //Connect Java fields to XML fields
        identity_txt= findViewById(R.id.provider_id_tx);
        location_txt = findViewById(R.id.where_located_tx);
        phone_txt= findViewById(R.id.phone_no_tx);
        qualifications_txt = findViewById(R.id.qualification_tx);
        username_txt = findViewById(R.id.username_tx);
        password_txt = findViewById(R.id.password_tx);

        session = new UserSession(this);
        userInfo = new UserInfo(this);

        if(!session.isUserLoggedin()){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        String username = userInfo.getKeyUsername();
        String password = userInfo.getKeyPass();

        username_txt.setText(username);
        password_txt.setText(password);

        viewData();
    }

    public void viewData() {

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            OkHttpClient client = new OkHttpClient();

            String username = String.valueOf(username_txt.getText());
            String password = String.valueOf(password_txt.getText());

            HttpUrl.Builder urlBuilder = HttpUrl.parse("http://192.168.43.58/sod_backend/view.php").newBuilder();
            urlBuilder.addQueryParameter("username", String.valueOf(username));
            urlBuilder.addQueryParameter("password", String.valueOf(password));

            String url = urlBuilder.build().toString();

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, final Response response) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                //txtInfo.setText(response.body().string());

                                try {
                                    String data = response.body().string();

                                    //JSON data response
                                    JSONArray jsonArray = new JSONArray(data);
                                    JSONObject jsonObject;

                                    jsonObject = jsonArray.getJSONObject(0);

                                    identity_txt.setText(jsonObject.getString("provider_id"));
                                    location_txt.setText(jsonObject.getString("where_located"));
                                    phone_txt.setText(jsonObject.getString("phone_no"));
                                    qualifications_txt.setText(jsonObject.getString("qualification"));
                                    username_txt.setText(jsonObject.getString("username"));
                                    password_txt.setText(jsonObject.getString("password"));

                                    String provider = jsonObject.getString("provider_id");
                                    String phone_no = jsonObject.getString("phone_no");
                                    // Inserting row in users table
                                    userInfo.setProvider(provider);
                                    userInfo.setPhone_no(phone_no);
                                    session.setLoggedin(true);

                                } catch (JSONException e) {
                                    toast(e.getMessage());
                                }


                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onLogout(View view){
        session.setLoggedin(false);
        userInfo.clearUserInfo();
        Intent logout = new Intent(this, LoginActivity.class);
        startActivity(logout);
        finish();
    }

    public void postService(View view){
        Intent post = new Intent(this, PostActivity.class);
        startActivity(post);
        finish();
    }

    public void saveInfo(View view){

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Change your Service Provider Info?");
            alertDialogBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                            popUp();
                }
            });
            alertDialogBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(ViewDataActivity.this, "Changes NOT Submitted!", Toast.LENGTH_LONG).show();
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

    }


    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    public void popUp (){

        //Connect Java fields to XML fields
        String provider = identity_txt.toString();
        String area = location_txt.toString();
        String phone = phone_txt.toString();
        String qualification = qualifications_txt.toString();
        String username = username_txt.toString();
        String password = password_txt.toString();

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            OkHttpClient client = new OkHttpClient();

            HttpUrl.Builder urlBuilder = HttpUrl.parse("http://192.168.43.58/sod_backend/save.php").newBuilder();
            urlBuilder.addQueryParameter("provider_id", identity_txt.getText().toString());
            urlBuilder.addQueryParameter("where_located", location_txt.getText().toString());
            urlBuilder.addQueryParameter("phone_no", phone_txt.getText().toString());
            urlBuilder.addQueryParameter("qualification", qualifications_txt.getText().toString());
            urlBuilder.addQueryParameter("username", username_txt.getText().toString());
            urlBuilder.addQueryParameter("password", password_txt.getText().toString());

            String url = urlBuilder.build().toString();

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, final Response response) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                password_txt.setText(response.body().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void toast(String x){
        Toast.makeText(this, x, Toast.LENGTH_SHORT).show();
    }
}
