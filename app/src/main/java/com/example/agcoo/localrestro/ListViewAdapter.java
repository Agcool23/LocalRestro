package com.example.agcoo.localrestro;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Agcoo on 26-Dec-16.
 */

public class ListViewAdapter extends ArrayAdapter<PlacesDetails>implements View.OnClickListener{

    private ArrayList<PlacesDetails> dataset;
    Context mContext;

    private static class ViewHolder {
        TextView txtName;
        ImageView dp;
        RatingBar ratingBar;
        TextView noData;
    }
    public ListViewAdapter(ArrayList<PlacesDetails> data, Context context) {
        super(context, R.layout.list_row, data);
        this.dataset = data;
        this.mContext=context;
    }

    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Object object= getItem(position);
        PlacesDetails dataModel=(PlacesDetails) object;

        //here we can choose if we want to attach a listener to any view of the adapter
    }
    private int lastPosition = -1;

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        PlacesDetails dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_row, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.textViewName);
            viewHolder.ratingBar = (RatingBar) convertView.findViewById(R.id.ratingBar);
            viewHolder.dp = (ImageView) convertView.findViewById(R.id.restaurant_img);
            viewHolder.noData = (TextView) convertView.findViewById(R.id.noDataTV);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }
        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtName.setText(dataModel.getPlaceName());
        if (dataModel.getRating() == null){
            viewHolder.noData.setVisibility(View.VISIBLE);
        }else {

            viewHolder.ratingBar.setVisibility(View.VISIBLE);
            viewHolder.ratingBar.setRating(Float.parseFloat(dataModel.getRating()));

        }
        // Return the completed view to render on screen
        return convertView;
    }
}
