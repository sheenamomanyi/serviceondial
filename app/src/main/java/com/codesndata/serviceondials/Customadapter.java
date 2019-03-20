package com.codesndata.serviceondials;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Customadapter extends ArrayAdapter<String> {
    private String[] service_type, provider_id, charge, area, phone_no;

    private Activity context;

    Customadapter(Activity context, String[] service_type, String[] provider_id, String[] charge, String[] area, String[] phone_no) {
        super(context, R.layout.layout, service_type);
        this.context = context;
        //this.bitmaps = bitmaps; , Bitmap[] bitmaps
        this.service_type = service_type;
        this.provider_id = provider_id;
        this.charge = charge;
        this.area = area;
        this.phone_no = phone_no;

    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout, null, true);
        TextView  services = listViewItem.findViewById(R.id.service_tv);
        TextView  provider = listViewItem.findViewById(R.id.provider_tv);
        TextView  amt_charge = listViewItem.findViewById(R.id.charge_tv);
        TextView  location = listViewItem.findViewById(R.id.area_tv);
        TextView  phone = listViewItem.findViewById(R.id.phone_tv);
        services.setText(service_type[position] );
        provider.setText(provider_id[position] );
        amt_charge.setText(charge[position] );
        location.setText(area[position] );
        phone.setText(phone_no[position] );

        ImageView image = listViewItem.findViewById(R.id.imgvw);
          //image.setImageBitmap(Bitmap.createScaledBitmap(bitmaps[position], 100, 50, false));

        return  listViewItem;


    }


}
