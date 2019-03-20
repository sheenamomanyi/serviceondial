package com.codesndata.serviceondials.prefs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 *
 */

public class UserInfo {
    private static final String TAG = com.codesndata.serviceondials.prefs.UserSession.class.getSimpleName();
    private static final String PREF_NAME = "userinfo";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASS = "password";
    private static final String KEY_Provider = "provider_id";
    private static final String KEY_Phone = "phone_no";
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;

    public UserInfo(Context ctx) {
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences(PREF_NAME, ctx.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setUsername(String username){
        editor.putString(KEY_USERNAME, username);
        editor.apply();
    }

    public void setProvider(String provider_id){
        editor.putString(KEY_Provider, provider_id);
        editor.apply();
    }

    public void setPhone_no(String phone_no){
        editor.putString(KEY_Phone, phone_no);
        editor.apply();
    }

    public void setPass(String pass){
        editor.putString(KEY_PASS, pass);
        editor.apply();
    }

    public void clearUserInfo(){
        editor.clear();
        editor.commit();
    }

    public String getKeyUsername(){return prefs.getString(KEY_USERNAME, "");}

    public String getKeyPass(){return prefs.getString(KEY_PASS, "");}

    public String getKeyProvider(){return prefs.getString(KEY_Provider, "");}

    public String getKeyPhone(){return prefs.getString(KEY_Phone, "");}
}
