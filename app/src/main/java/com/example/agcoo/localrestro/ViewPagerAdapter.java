package com.example.agcoo.localrestro;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Agcoo on 26-Dec-16.
 */

public class ViewPagerAdapter extends PagerAdapter{
    private List<PlacesDetails> restaurantsDetails= new ArrayList<>();
    private LayoutInflater inflater;
    private Context ctx;

    public ViewPagerAdapter(Context ctx){
        this.ctx = ctx;
    }
    @Override
    public int getCount() {
        return restaurantsDetails.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = inflater.inflate(R.layout.viewpager_layout,container,false);
        ImageView imageView = (ImageView)item_view.findViewById(R.id.restaurant_img);
        TextView textView = (TextView) item_view.findViewById(R.id.textViewName);
        RatingBar ratingBar = (RatingBar) item_view.findViewById(R.id.ratingBar);
        textView.setText(restaurantsDetails.get(position).getPlaceName());
        ratingBar.setRating(Float.valueOf(restaurantsDetails.get(position).getRating()));
        container.addView(item_view);

        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
