package com.codesndata.serviceondials.service_provider;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 *
 */

public class BgSync extends AsyncTask<String,Void,String>{

    public static final String LOGIN_URL  = "http://192.168.43.58/sod_backend/login.php";
    Context ctx;
    AlertDialog alertDialog;


    @Override
    protected void onPreExecute() {
//        alertDialog = new AlertDialog.Builder(ctx).create();
//        alertDialog.setTitle("Server Response");

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
//        if(result.equals("Registration Successful!"))
//        {
//            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
//        } else if(result.contains("Data")){
//            alertDialog.setMessage(result);
//            alertDialog.show();
//
//        }  else {
        // popUp();
//        }
    }

    @Override
    protected String doInBackground(String... params) {

        //Private (Phone) IP: 192.168.43.58
        //Local (Emulator) IP: 10.0.2.2
        String reg_url = "http://192.168.43.58/sod_backend/register.php";
        String login_url = "http://192.168.43.58/sod_backend/login.php";
        String post_url = "http://192.168.43.58/sod_backend/post_service.php";
        String save_info_url = "http://192.168.43.58/sod_backend/save.php";
        String method = params[0];

        switch (method) {
            case "register": {

                //passing parameters
                String provider_id = params[1];
                String where_located = params[2];
                String phone_no = params[3];
                String qualification = params[4];
                String username = params[5];
                String password = params[6];

                try {
                    URL url = new URL(reg_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("provider_id", "UTF-8") + "=" + URLEncoder.encode(provider_id, "UTF-8") + "&" +
                            URLEncoder.encode("where_located", "UTF-8") + "=" + URLEncoder.encode(where_located, "UTF-8") + "&" +
                            URLEncoder.encode("phone_no", "UTF-8") + "=" + URLEncoder.encode(phone_no, "UTF-8") + "&" +
                            URLEncoder.encode("qualification", "UTF-8") + "=" + URLEncoder.encode(qualification, "UTF-8") + "&" +
                            URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                            URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    OS.close();
                    InputStream IS = httpURLConnection.getInputStream();
                    IS.close();
                    return "Registration Successful!";
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "login": {
                String username = params[1];
                String password = params[2];
                try {
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                            URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String response = "";
                    String line = "";

                    while ((line = bufferedReader.readLine()) != null) {
                        response += line;
                    }

                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return response;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "post_service": {
                String service_type = params[1];
                String provider_id = params[2];
                String charge = params[3];
                String area = params[4];
                String phone_no = params[5];
                try {
                    URL url = new URL(post_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String data = URLEncoder.encode("service_type", "UTF-8") + "=" + URLEncoder.encode(service_type, "UTF-8") + "&" +
                            URLEncoder.encode("provider_id", "UTF-8") + "=" + URLEncoder.encode(provider_id, "UTF-8") + "&" +
                            URLEncoder.encode("charge", "UTF-8") + "=" + URLEncoder.encode(charge, "UTF-8") + "&" +
                            URLEncoder.encode("area", "UTF-8") + "=" + URLEncoder.encode(area, "UTF-8") + "&" +
                            URLEncoder.encode("phone_no", "UTF-8") + "=" + URLEncoder.encode(phone_no, "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String response = "";
                    String line;

                    while ((line = bufferedReader.readLine()) != null) {
                        response += line;
                    }

                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return response;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "save": {

                //passing parameters
                String provider_id = params[1];
                String where_located = params[2];
                String phone_no = params[3];
                String qualification = params[4];
                String username = params[5];
                String password = params[6];

                try {
                    URL url = new URL(save_info_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("provider_id", "UTF-8") + "=" + URLEncoder.encode(provider_id, "UTF-8") + "&" +
                            URLEncoder.encode("where_located", "UTF-8") + "=" + URLEncoder.encode(where_located, "UTF-8") + "&" +
                            URLEncoder.encode("phone_no", "UTF-8") + "=" + URLEncoder.encode(phone_no, "UTF-8") + "&" +
                            URLEncoder.encode("qualification", "UTF-8") + "=" + URLEncoder.encode(qualification, "UTF-8") + "&" +
                            URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                            URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    OS.close();
                    InputStream IS = httpURLConnection.getInputStream();
                    IS.close();
                    return "Info Change Successful!";
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        return null;
    }
}

