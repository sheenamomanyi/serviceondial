package com.codesndata.serviceondials;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    ListView listView;
    Dialog dialog;
    Context cxt;
    String urlAddress="http://192.168.43.58/sod_backend/fetch.php";
    SearchView sv;
    private static final String newurl = "http://192.168.43.58/sod_backend/services.php";
    private static final String searchUrl = "http://192.168.43.58/sod_backend/search.php";

    public  Getjson getjsonobj;
    Customadapter customadapter;
    CardView card;
    EditText service, location;
    ImageView noDataImg,noNetworkImg;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.lst);
//        service = findViewById(R.id.service_Etxt);
//        location = findViewById(R.id.area_Etxt);
        sv= findViewById(R.id.sv);
        noDataImg= findViewById(R.id.nodataImg);
        noNetworkImg= findViewById(R.id.noserver);
        dialog = new Dialog(this);
        card = new CardView(this);
        getURLs();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(MainActivity.this, com.codesndata.serviceondials.service_provider.LoginActivity.class);
                startActivity(login);
            }
        });

//        Button search = findViewById(R.id.search_Btn);
//        search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //getSearchResults();
//            }
//        });

    }

    private void attemptSearch() {
        //ProgressDialog loading;

        // Reset errors.
        service.setError(null);
        location.setError(null);

        // Store values at the time of the search attempt.
        String service_type = service.getText().toString();
        String area = location.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for an empty location field.
        if (TextUtils.isEmpty(area)) {
            location.setError(getString(R.string.error_field_required));
            focusView = location;
            cancel = true;
        }

        // Check for an empty service field.
        if (TextUtils.isEmpty(service_type)) {
            service.setError(getString(R.string.error_field_required));
            focusView = service;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt search and focus the first
            // search field with an error.
            focusView.requestFocus();
        } else {
            //search();
        }
    }

//    /**
//     * Represents an asynchronous search task
//     */
//
//    private void search(){
//
//        try {
//            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//
//            OkHttpClient client = new OkHttpClient();
//
//            String s_service = String.valueOf(service.getText());
//            String s_location = String.valueOf(location.getText());
//
//            HttpUrl.Builder urlBuilder = HttpUrl.parse("http://192.168.43.58/sod_backend/search.php").newBuilder();
//            urlBuilder.addQueryParameter("service_type", String.valueOf(s_service));
//            urlBuilder.addQueryParameter("area", String.valueOf(s_location));
//
//            String url = urlBuilder.build().toString();
//
//            okhttp3.Request request = new okhttp3.Request.Builder()
//                    .url(url)
//                    .build();
//
//            client.newCall(request).enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//
//                }
//
//                @Override
//                public void onResponse(Call call, final okhttp3.Response response) {
//
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//
//
//                            customadapter = new Customadapter(MainActivity.this, Getjson.service_tp, Getjson.provider,
//                                    Getjson.amt_charged, Getjson.location, Getjson.phone); //, Getjson.bitmaps
//
//                            listView.setAdapter(customadapter);
//
//                        }
//                    });
//                }
//
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private void toast(String x){
        Toast.makeText(this, x, Toast.LENGTH_SHORT).show();
    }


//    public void showPopUp(View v) {
//        Button chat_btn, call_btn;
//        chat_btn = dialog.findViewById(R.id.chat_btn);
//        call_btn = dialog.findViewById(R.id.call_btn);
//        ImageView profile;
//        profile = dialog.findViewById(R.id.profile_img);
//        TextView txtClose, provider_id, service, charge, phone_no;
//        txtClose = dialog.findViewById(R.id.close_tv);
//        service = dialog.findViewById(R.id.service_tv);
//        charge = dialog.findViewById(R.id.charge_tv);
//        phone_no = dialog.findViewById(R.id.phone_no_tv);
//        provider_id = dialog.findViewById(R.id.provider_id_tv);
//        dialog.setContentView(R.layout.custompopup);
//        provider_id.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.show();
//    }


    //Get Services
    private void getServices() {
        class getServices extends AsyncTask<Void, Void, Void> {
            private ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this, "Loading Services", "Please wait...", false, true);
            }

            @Override
            protected void onPostExecute(Void v) {
                super.onPostExecute(v);
                loading.dismiss();


                customadapter = new Customadapter(MainActivity.this, Getjson.service_tp, Getjson.provider,
                        Getjson.amt_charged, Getjson.location, Getjson.phone); //, Getjson.bitmaps

                listView.setAdapter(customadapter);


            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    getjsonobj.getAllImages();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        getServices getData = new getServices();
        getData.execute();
    }

    //Get Services
    private void getSearchServices() {
        class getSearchServices extends AsyncTask<Void, Void, Void> {
            private ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this, "Loading Services", "Please wait...", false, true);
            }

            @Override
            protected void onPostExecute(Void v) {
                super.onPostExecute(v);
                loading.dismiss();

                try {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    OkHttpClient client = new OkHttpClient();

                    String area = String.valueOf(location.getText());
                    String service_type = String.valueOf(service.getText());

                    HttpUrl.Builder urlBuilder = HttpUrl.parse("http://192.168.43.58/sod_backend/search.php").newBuilder();
                    urlBuilder.addQueryParameter("service_type", String.valueOf(service_type));
                    urlBuilder.addQueryParameter("area", String.valueOf(area));

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

                                        customadapter = new Customadapter(MainActivity.this, Getjson.service_tp, Getjson.provider,
                                                Getjson.amt_charged, Getjson.location, Getjson.phone); //, Getjson.bitmaps

                                        listView.setAdapter(customadapter);

                                }
                            });
                        }

                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    getjsonobj.getAllImages();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        getSearchServices getData = new getSearchServices();
        getData.execute();
    }

    private void getSearchResults() {

        class getSearchResults extends AsyncTask<String, Void, String> {
            private ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this, "Loading Data", "Please Wait...", true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                getjsonobj = new Getjson(s);
                getSearchServices();

            }

            @Override
            protected String doInBackground(String... strings) {
                BufferedReader bufferedReader;
                try {

                    URL url = new URL(strings[0]);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json).append("\n");
                    }

                    return sb.toString().trim();

                } catch (Exception e) {
                    return null;

                }
            }
        }
        getSearchResults gu = new getSearchResults();


        gu.execute(searchUrl);


    }

    private void getURLs() {

        class GetURLs extends AsyncTask<String, Void, String> {
            private ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this, "Loading Data", "Please Wait...", true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                getjsonobj = new Getjson(s);
                getServices();

            }

            @Override
            protected String doInBackground(String... strings) {
                BufferedReader bufferedReader;
                try {

                    URL url = new URL(strings[0]);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json).append("\n");
                    }

                    return sb.toString().trim();

                } catch (Exception e) {
                    return null;

                }
            }
        }
        GetURLs gu = new GetURLs();


        gu.execute(newurl);


    }

}