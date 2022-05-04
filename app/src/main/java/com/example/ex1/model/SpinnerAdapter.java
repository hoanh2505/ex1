package com.example.ex1.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ex1.R;

public class SpinnerAdapter  extends BaseAdapter {
    private Context context;
    private int[] imgs = {
            R.drawable.tcb,
            R.drawable.vcb,
            R.drawable.vib
    };
    private String[] serviceName = {
            "Techcombank", "Vietcombank", "vib"
    };

    public SpinnerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View item = LayoutInflater.from(context).inflate(R.layout.item_image, viewGroup, false);
        ImageView image = item.findViewById(R.id.img);
        TextView textView = item.findViewById(R.id.tv_service_name);
        image.setImageResource(imgs[position]);
        textView.setText(serviceName[position]);
        return item;
    }
}
