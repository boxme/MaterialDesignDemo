package com.desmond.materialdesigndemo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import com.desmond.materialdesigndemo.R;
import com.desmond.materialdesigndemo.ui.Utils;
import com.desmond.materialdesigndemo.ui.adapter.FeedAdapter;

public class MainActivity extends BaseActivity implements FeedAdapter.OnFeedItemClickListener {

    public static final String ACTION_SHOW_LOADING_ITEM = "action_show_loading_item";

    private static final int ANIM_DURATION_TOOLBAR = 300;
    private static final int ANIM_DURATION_FAB = 400;

    CoordinatorLayout mClContent;

    FloatingActionButton mFabCreate;

    RecyclerView mRvFeed;
    private FeedAdapter mFeedAdapter;

    private boolean mPendingIntroAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mClContent = (CoordinatorLayout) findViewById(R.id.content);
        mFabCreate = (FloatingActionButton) findViewById(R.id.btnCreate);

        setupToolbar();
        setupFeed();

        if (savedInstanceState == null) {
            mPendingIntroAnimation = true;
        } else {
            mFeedAdapter.updateItems();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        if (mPendingIntroAnimation) {
            mPendingIntroAnimation = false;
            startIntroAnimation();
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    private void setupFeed() {
        mRvFeed = (RecyclerView) findViewById(R.id.rvFeed);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 300;
            }
        };
        mRvFeed.setLayoutManager(linearLayoutManager);

        mFeedAdapter = new FeedAdapter();
        mFeedAdapter.setOnFeedItemClickListener(this);
        mRvFeed.setAdapter(mFeedAdapter);
    }

    @Override
    public void onCommentsClick(View view, int position) {
        final Intent intent = new Intent(this, CommentsActivity.class);

        // Get location on screen for tapped view
        int[] startingLocation = new int[2];
        view.getLocationOnScreen(startingLocation);
        intent.putExtra(CommentsActivity.ARG_DRAWING_START_LOCATION, startingLocation[1]);

        startActivity(intent);
        // Disable enter transition for new Activity and exit animation for MainActivity
        overridePendingTransition(0, 0);
    }

    private void startIntroAnimation() {
        mFabCreate.setTranslationY(2 * getResources().getDimensionPixelOffset(R.dimen.btn_fab_size));

        int actionBarSize = Utils.dpToPx(56);
        mToolBar.setTranslationY(-actionBarSize);
        mIvLogo.setTranslationY(-actionBarSize);
        mInboxMenuItem.getActionView().setTranslationY(-actionBarSize);

        ViewCompat.animate(mToolBar)
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(300);

        ViewCompat.animate(mIvLogo)
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(400);

        ViewCompat.animate(mInboxMenuItem.getActionView())
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(500)
                .setListener(new ViewPropertyAnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(View view) {
                        startContentAnimation();
                    }
                })
                .start();
    }

    private void startContentAnimation() {
        ViewCompat.animate(mFabCreate)
                .translationY(0)
                .setInterpolator(new OvershootInterpolator(1.0F))
                .setDuration(ANIM_DURATION_FAB)
                .setStartDelay(300)
                .start();

        mFeedAdapter.updateItems();
    }
}
