package com.desmond.materialdesigndemo.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.desmond.materialdesigndemo.R;

public class BaseActivity extends AppCompatActivity {

    Toolbar mToolBar;
    ImageView mIvLogo;

    MenuItem mInboxMenuItem;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        mInboxMenuItem = menu.findItem(R.id.action_inbox);
        mInboxMenuItem.setActionView(R.layout.menu_item_view);
        return true;
    }

    protected void setupToolbar() {
        if (mToolBar == null) {
            mToolBar = (Toolbar) findViewById(R.id.toolbar);
        }
        setSupportActionBar(mToolBar);
        mToolBar.setNavigationIcon(R.drawable.ic_menu_white);
    }

    public Toolbar getToolbar() {
        return mToolBar;
    }

    public MenuItem getInboxMenuItem() {
        return mInboxMenuItem;
    }

    public ImageView getIvLogo() {
        return mIvLogo;
    }
}
