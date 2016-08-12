package com.example.canrom7.mowa;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Canrom7 on 2016/8/7 .
 * Mailbox canrom7@163.com .
 */
public class Find_Fragment extends Fragment implements View.OnClickListener{
    private Context mContext;
    private View mFragment_View;
    private View mFriendView;
    private View mIMinfoView;
    private View mShoucangView;
    private View mSixinView;
    private View mSetTingView;
    private View MowaView;
    public Find_Fragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext=inflater.getContext();
        mFragment_View=LayoutInflater.from(mContext).inflate(R.layout.layout_note_seting,null);
        return mFragment_View;
    }

    @Override
    public void onStart() {
        super.onStart();
        mFriendView=mFragment_View.findViewById(R.id.friend_layout);
        mIMinfoView=mFragment_View.findViewById(R.id.Layout_iminfo);
        mShoucangView=mFragment_View.findViewById(R.id.layout_shoucang);
        mSixinView=mFragment_View.findViewById(R.id.layout_sixin);
        mSetTingView=mFragment_View.findViewById(R.id.layout_steting);
        MowaView=mFragment_View.findViewById(R.id.layout_guanyu);
        mFriendView.setOnClickListener(this);
        mIMinfoView.setOnClickListener(this);
        mShoucangView.setOnClickListener(this);
        mSixinView.setOnClickListener(this);
        mSetTingView.setOnClickListener(this);
        MowaView.setOnClickListener(this);
    }

    @Override
    //所有点击事件
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.friend_layout:{//点击朋友圈事件
            }
        }
    }

}
