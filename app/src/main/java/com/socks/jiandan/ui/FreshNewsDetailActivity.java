package com.socks.jiandan.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.socks.jiandan.R;
import com.socks.jiandan.model.FreshNews;
import com.socks.jiandan.ui.fragment.FreshNewsDetailFragment;
import com.socks.jiandan.utils.ShareUtil;
import com.socks.jiandan.utils.SystemBarTintManager;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class FreshNewsDetailActivity extends AppCompatActivity {

    @InjectView(R.id.vp)
    ViewPager vp;
    int position;
    ArrayList<FreshNews> FreshNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresh_news_detail);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintColor(getResources().getColor(R.color.StatusBarColor));
        }
        ButterKnife.inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        FreshNews = (ArrayList<FreshNews>) getIntent().getSerializableExtra
                ("FreshNews");

        position = getIntent().getIntExtra("position", 0);
        vp.setAdapter(new FreshNewsDetailAdapter(getSupportFragmentManager(), FreshNews));
        vp.setCurrentItem(position);

    }


    private class FreshNewsDetailAdapter extends FragmentPagerAdapter {

        private ArrayList<FreshNews> freshNewses;

        public FreshNewsDetailAdapter(FragmentManager fm, ArrayList<FreshNews> freshNewses) {
            super(fm);
            this.freshNewses = freshNewses;
        }

        @Override
        public Fragment getItem(int position) {
            // position = position;
            return FreshNewsDetailFragment.getInstance(freshNewses.get(position));
        }

        @Override
        public int getCount() {
            return freshNewses.size();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fresh_news_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_comment:
                Intent intent = new Intent(this, CommentList4FreshNewsActivity.class);
                intent.putExtra("id", FreshNews.get(vp.getCurrentItem()).getId());
                startActivity(intent);
                return true;
            case R.id.action_share:
                ShareUtil.shareText(this, FreshNews.get(vp.getCurrentItem()).getTitle() + " " + FreshNews.get(vp.getCurrentItem()).getUrl());
                return true;
        }
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
