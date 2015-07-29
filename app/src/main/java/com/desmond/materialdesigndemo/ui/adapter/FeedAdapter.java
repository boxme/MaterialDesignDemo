package com.desmond.materialdesigndemo.ui.adapter;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.desmond.materialdesigndemo.R;
import com.desmond.materialdesigndemo.ui.Utils;
import com.desmond.materialdesigndemo.ui.view.SquareImageView;

/**
 * Created by desmond on 29/7/15.
 */
public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ANIMATED_ITEMS_COUNT = 2;

    private int mLastAnimatedPosition = -1;
    private int mItemsCount = 0;


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed, parent, false);
        return new CellFeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        runEnterAnimation(viewHolder.itemView, position);

        CellFeedViewHolder holder = (CellFeedViewHolder) viewHolder;
        if (position % 2 == 0) {
            holder.ivFeedCenter.setImageResource(R.drawable.img_feed_center_1);
            holder.ivFeedBottom.setImageResource(R.drawable.img_feed_bottom_1);
        } else {
            holder.ivFeedCenter.setImageResource(R.drawable.img_feed_center_2);
            holder.ivFeedBottom.setImageResource(R.drawable.img_feed_bottom_2);
        }
    }

    private void runEnterAnimation(View view, int position) {
        if (position >= ANIMATED_ITEMS_COUNT - 1) {
            return;
        }

        if (position > mLastAnimatedPosition) {
            mLastAnimatedPosition = position;
            view.setTranslationY(Utils.getScreenHeight(view.getContext()));
            ViewCompat.animate(view)
                    .translationY(0)
                    .setInterpolator(new DecelerateInterpolator())
                    .setDuration(700)
                    .start();
        }
    }

    @Override
    public int getItemCount() {
        return mItemsCount;
    }

    public void updateItems() {
        mItemsCount = 10;
        notifyDataSetChanged();
    }

    public static class CellFeedViewHolder extends RecyclerView.ViewHolder {
        SquareImageView ivFeedCenter;
        ImageView ivFeedBottom;

        public CellFeedViewHolder(View view) {
            super(view);
            ivFeedCenter = (SquareImageView) view.findViewById(R.id.ivFeedCenter);
            ivFeedBottom = (ImageView) view.findViewById(R.id.ivFeedBottom);
        }
    }
}
