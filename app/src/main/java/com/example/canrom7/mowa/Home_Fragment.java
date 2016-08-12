package com.example.canrom7.mowa;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Canrom7 on 2016/8/7 .
 * Mailbox canrom7@163.com .
 * 主页
 */
public class Home_Fragment extends Fragment implements View.OnClickListener{
    private Context mContext;
    private View mFragment_View;
    private View mWeather_View;
    private TextView mBraderText;
    private ImageView mAddButton;
    public Home_Fragment(){}
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext=inflater.getContext();
        mFragment_View=LayoutInflater.from(mContext).inflate(R.layout.layout_note_edite,null);
        mWeather_View=mFragment_View.findViewById(R.id.weather_layout);
        mBraderText= (TextView) mFragment_View.findViewById(R.id.textview_braderCast);
        mAddButton= (ImageView) mFragment_View.findViewById(R.id.button_addNote);
        mAddButton.setOnClickListener(this);
        return mFragment_View;
    }
    @Override
    //所有点击事件
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_addNote:{//添加笔记按钮
                Animation animation= AnimationUtils.loadAnimation(mContext,R.anim.rotate_anima);
                mAddButton.startAnimation(animation);
                break;
            }
        }
    }

}
