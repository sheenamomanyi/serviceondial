package com.codesndata.serviceondials;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Getjson {
    //Context cxt;
    //private static final String TAG = "services";
    //public static Bitmap[] bitmaps;
    public static String[] service_tp, provider, amt_charged, location, phone;

    public static final String JSON_ARRAY="result", service_type = "service_type",
            provider_id = "provider_id", charge = "charge", area = "area", phone_no = "phone_no"; // IMAGEURL = "ImagePath",

    private JSONArray urls;

    Getjson(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            urls = jsonObject.getJSONArray(JSON_ARRAY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
//    private Bitmap getImage(JSONObject jo){
//        URL url;
//        Bitmap image = null;
//        try {
//            url = new URL(jo.getString(IMAGEURL));
//            image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//        } catch (IOException | JSONException e) {
//            e.printStackTrace();
//        }
//        return image;
//    }

    public void getAllImages() throws JSONException {
        service_tp = new String[urls.length()];
        provider = new String[urls.length()];
        amt_charged = new String[urls.length()];
        location = new String[urls.length()];
        phone = new String[urls.length()];
        //String[] image_Url = new String[urls.length()];


        //bitmaps = new Bitmap[urls.length()];

        if(urls.length() != 0){
            for(int i=0;i<urls.length();i++){
                service_tp[i]= urls.getJSONObject(i).getString(service_type);
                provider[i]= urls.getJSONObject(i).getString(provider_id);
                amt_charged[i]= urls.getJSONObject(i).getString(charge);
                location[i]= urls.getJSONObject(i).getString(area);
                phone[i]= urls.getJSONObject(i).getString(phone_no);
                //image_Url[i] = urls.getJSONObject(i).getString(IMAGEURL);

                //JSONObject jsonObject = urls.getJSONObject(i);
                //bitmaps[i]=getImage(jsonObject);

            }
        }

    }


//    public void showAlert(){
//
//        Log.d(TAG, "Switching Internet ON");
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(cxt);
//        builder.setIcon(R.drawable.ic_action_info);
//        builder.setTitle("NETWORK REQUIRED");
//        builder.setMessage("\nPlease, switch ON Hotspot to create connection to the server.");
//        builder.setCancelable(true);
////        builder.setPositiveButton("SWITCH ON", new android.content.DialogInterface.OnClickListener(){
////            public void onClick(DialogInterface dialog, int which) {
////                Intent intent = new Intent();
////                intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$DataUsageSummaryActivity"));
////
////            }
////
////        });
//
//        AlertDialog dialog = builder.create();
//        dialog.show();
//
//    }



}
