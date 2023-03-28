package com.example.flower_ivanova;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class FlowerAdapter extends ArrayAdapter<Flower> {
    private final static String PHOTO_URL = "http://services.hanselandpetal.com/photos/";
    private List<Flower> mFlowers;
    private Context mContext;

    public FlowerAdapter(Context context, List<Flower> flowerNAmes) {
        super(context, R.layout.flower_item, flowerNAmes);
        this.mFlowers = flowerNAmes;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.flower_item, parent, false);

        ImageView flowerimg = (ImageView) view.findViewById(R.id.imageView);
        TextView nameTextView = (TextView) view.findViewById(R.id.flowerName);
        TextView descripTextView = (TextView) view.findViewById(R.id.flowerDisc);

        Log.d("GIT", ""+"https://services.hanselandpetal.com/photos/" + this.mFlowers.get(position).getPhoto());
        Picasso
                .with(view.getContext())
                .load("https://services.hanselandpetal.com/photos/" + this.mFlowers.get(position).getPhoto())
                .into(flowerimg);
        ;

        nameTextView.setText(this.mFlowers.get(position).getName());
        descripTextView.setText(this.mFlowers.get(position).getInstructions());

        return view;
    }
}
