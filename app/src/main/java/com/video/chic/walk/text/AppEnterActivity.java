package com.video.chic.walk.text;

import android.app.MediaRouteButton;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.video.chic.walk.R;
import com.video.chic.walk.utils.HasNetWork;
import com.video.chic.walk.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;

import static com.video.chic.walk.utils.SPUtil.SP_ISFIRST_TIME;

public class AppEnterActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    private static final int[] introPics = {
            R.drawable.g1,
            R.drawable.g2,
            R.drawable.g3
    };
    public  RelativeLayout mSplashLayout;
    public ViewPager mViewPager;
    public ImageView mEnterView;
    private int currentIndex;
    private ImageView[] dotImages;
    LinearLayout mDotsLayout;
    private boolean mFirstStart = false;
    private View mIvSplash;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);  //无title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);  //全屏
        setContentView(R.layout.activity_splash);
        mSplashLayout=findViewById(R.id.splash_layout);
        mViewPager = findViewById(R.id.viewpager);
        mEnterView = findViewById(R.id.enter_view);
        mDotsLayout = findViewById(R.id.ll);
        mIvSplash = findViewById(R.id.mrlBigImage);
        mFirstStart = SPUtil.getPrefBoolean(getApplicationContext(),SP_ISFIRST_TIME,true);
        init();
    }

    private void init() {
        if (mFirstStart) {
            mIvSplash.setVisibility(View.GONE);
            mSplashLayout.setVisibility(View.VISIBLE);
            List<View> views = new ArrayList<View>();
            LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.FILL_PARENT);
            for (int i = 0; i < introPics.length; i++) {
                ImageView iv = new ImageView(this);
                WindowManager wm = (WindowManager) this
                        .getSystemService(Context.WINDOW_SERVICE);
                mParams.weight = wm.getDefaultDisplay().getWidth();
                mParams.height = wm.getDefaultDisplay().getHeight();
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                iv.setLayoutParams(mParams);
                iv.setImageResource(introPics[i]);
                views.add(iv);
            }
            mViewPager.setAdapter(new SplashViewPagerAdapter(views));
            mViewPager.setOnPageChangeListener(this);
            mEnterView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!HasNetWork.isNetworkAvailable(AppEnterActivity.this)){
                        HasNetWork.showNoWifiDialog(AppEnterActivity.this);
                        return;
                    }
                    SPUtil.setPrefBoolean(getApplicationContext(), SP_ISFIRST_TIME, false);
                    startActivity(new Intent(AppEnterActivity.this, activity.class));
                    AppEnterActivity.this.finish();
                }
            });
            initDots();
        }else{
            mIvSplash.setVisibility(View.VISIBLE);
            mSplashLayout.setVisibility(View.GONE);

        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        setCurDot(i);
        if(i==introPics.length-1){
            mEnterView.setVisibility(View.VISIBLE);
        }else{
            mEnterView.setVisibility(View.GONE);
        }
    }

    private void setCurDot(int i) {
        if(i<0||i>introPics.length||currentIndex == i){
            return;
        }
        dotImages[i].setEnabled(true);
        dotImages[currentIndex].setEnabled(false);
        currentIndex = 0;
        dotImages[currentIndex].setEnabled(true);
    }
    private void initDots() {
        dotImages = new ImageView[introPics.length];
        for(int i=0; i<introPics.length; i++){
            dotImages[i]=(ImageView)mDotsLayout.getChildAt(i);
            dotImages[i].setEnabled(false);
        }
        currentIndex = 0;
        dotImages[currentIndex].setEnabled(true);
    }

        @Override
    public void onPageScrollStateChanged(int i) {

    }
}
