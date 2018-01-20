package com.nerdgeeks.foodmap.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nerdgeeks.foodmap.R;
import com.nerdgeeks.foodmap.model.ReviewsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by hp on 1/3/2017.
 */

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ListHolder> {
    private ArrayList<ReviewsModel> reviewsModelsList = new ArrayList<>();
    private Context mContext;

    public ReviewsAdapter(ArrayList<ReviewsModel> reviews, Context context) {
        this.reviewsModelsList = reviews;
        this.mContext = context;
    }

    @Override
    public ReviewsAdapter.ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reviews, parent, false);
        this.mContext = parent.getContext();
        return new ReviewsAdapter.ListHolder(rootView);
    }

    @Override
    public void onBindViewHolder(final ReviewsAdapter.ListHolder holder, final int position) {

        //adding custom font
        final Typeface ThemeFont = Typeface.createFromAsset(mContext.getAssets(), "fonts/HelveticaNeue.ttf");
        holder.user_name.setTypeface(ThemeFont);
        holder.user_ratings.setTypeface(ThemeFont);
        holder.user_text.setTypeface(ThemeFont);
        holder.user_time.setTypeface(ThemeFont);

        String ratings = reviewsModelsList.get(position).getRatings();
        String photoUrl = reviewsModelsList.get(position).getPhotoUrl();

        if (photoUrl != null) {
            Picasso.with(mContext)
                    .load("" + reviewsModelsList.get(position).getPhotoUrl())
                    .into(holder.user_pic);
        } else {
            Picasso.with(mContext)
                    .load(R.drawable.ic_account)
                    .into(holder.user_pic);
        }

        if (ratings == null) {
            holder.user_ratingbar.setRating(0);
            holder.user_ratings.setText("N/A");
        } else {
            holder.user_ratingbar.setRating(Float.parseFloat(ratings));
            holder.user_ratings.setText(ratings);
        }

        holder.user_name.setText(reviewsModelsList.get(position).getAuthorName());
        holder.user_text.setText(reviewsModelsList.get(position).getAuthorText());
        holder.user_time.setText(reviewsModelsList.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return reviewsModelsList.size();
    }

    class ListHolder extends RecyclerView.ViewHolder {
        private ImageView user_pic;
        private AppCompatTextView user_name;
        private AppCompatTextView user_ratings;
        private AppCompatRatingBar user_ratingbar;
        private AppCompatTextView user_text;
        private AppCompatTextView user_time;

        ListHolder(View itemView) {
            super(itemView);
            user_pic = (ImageView) itemView.findViewById(R.id.pro_pic);
            user_name = (AppCompatTextView) itemView.findViewById(R.id.author_name);
            user_ratings = (AppCompatTextView) itemView.findViewById(R.id.author_rate);
            user_text = (AppCompatTextView) itemView.findViewById(R.id.author_text);
            user_ratingbar = (AppCompatRatingBar) itemView.findViewById(R.id.author_ratingbar);
            user_time = (AppCompatTextView) itemView.findViewById(R.id.author_time);
        }
    }
}
