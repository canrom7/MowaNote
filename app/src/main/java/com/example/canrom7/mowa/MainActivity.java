package com.example.canrom7.mowa;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.canrom7.mowa.AnimationUtils.ZoomOutPageTransformer;
import com.example.canrom7.mowa.DBbean.User;
import com.example.canrom7.mowa.adpter.FragmenAdapter;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

/*
*   基类Activity
* 负责登录状态判断,数据操作
*/
public class MainActivity extends AppCompatActivity implements
        ViewPager.OnPageChangeListener,
        RadioGroup.OnCheckedChangeListener
{
    private static final String Bmob_APPID="bb8493b19d9d36a9e39ae394bffa1e4d";
    private static final String TAG="MainActivity===>";
    //上下文
    public Context mContext;
    private boolean isLogin=false;
    private User mUser=null;
    private String userId;
    private FragmentManager mFragmentManager;
    private List<Fragment> mFragments;
    private LayoutInflater mInflater;
    private ViewPager mViewPager;
    private RadioGroup mRadioGroup;
    private RadioButton mRadioButtonList,mRadioButtonEdite,mRadioButtonFind;
    private TextView mTitleView;
    private Fragment mFragment_lise,mFragment_home,mFragment_find;
    private int pagerNumber;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=MainActivity.this;
         setContentView(R.layout.activity_main);
        //隐藏状态栏
        hideStatusBar();
        //初始化Bomb_Sdk
        Bmob.initialize(this,Bmob_APPID);
        //初始化数据，检查登录状态
        initData();
    }

    @Override
    protected void onStart() {
        //判断登录
        isLogein();
        super.onStart();
        //初始化View
        initView();
    }

    private void isLogein() {
        //判断登录状态
        if (!isLogin){
            startLoginActivity(mContext);
            finish();
        }else {
            //判断用户是否为空
            if (mUser==null){
                BmobQuery<User> query = new BmobQuery<User>();
                query.getObject(userId, new QueryListener<User>(){
                    @Override
                    public void done(User user, BmobException e) {
                        if (e==null){
                            mUser=user;
                        }else {
                            toast("网络异常");
                        }
                    }
                });
            }
        }

    }

    //初始化View视图
    private void initView() {
        //Fragment管理器初始化
        mFragmentManager=getSupportFragmentManager();
        //初始化视图填充器
        mInflater=LayoutInflater.from(mContext);
        mFragments=new ArrayList<>();
        mTitleView= (TextView) findViewById(R.id.textView_Title);
        mViewPager= (ViewPager) findViewById(R.id.viewPager_mian);
        mRadioGroup= (RadioGroup) findViewById(R.id.radioGriup_Meun);
        mRadioButtonList= (RadioButton)mRadioGroup.findViewById(R.id.button_List);
        mRadioButtonEdite= (RadioButton) mRadioGroup.findViewById(R.id.button_Edie);
        mRadioButtonFind= (RadioButton)mRadioGroup.findViewById(R.id.button_Find);
        mRadioGroup.setOnCheckedChangeListener(MainActivity.this);
        mFragment_home=new Home_Fragment();
        mFragment_lise=new List_Fragment();
        mFragment_find=new Find_Fragment();
        mFragments.add(mFragment_lise);
        mFragments.add(mFragment_home);
        mFragments.add(mFragment_find);
        mViewPager.setAdapter(new FragmenAdapter(mFragmentManager,mFragments));
        //设置pager改变监听
        mViewPager.addOnPageChangeListener(this);
        //设置viewpager动画
        mViewPager.setPageTransformer(true,new ZoomOutPageTransformer());
        mViewPager.setCurrentItem(1);
    }

    //初始化数据
    private void initData(){
        SharedPreferences preferences=getSharedPreferences("User",MODE_PRIVATE);
        isLogin=preferences.getBoolean("isLogin",false);
        Log.d(TAG, "initData: 是否登录==》"+isLogin);
        userId=preferences.getString("userId",null);
    }
    @Override
    //ViewPager页面被选中
    public void onPageSelected(int position) {
    switch (position){
        case 0:{//选中笔记列表视图
            mRadioButtonList.setChecked(true);
            mTitleView.setText("列 表");

            pagerNumber=0;
            Log.d(TAG, "选中笔记列表视图: "+pagerNumber);
            break;
        }
        case 1:{//选中笔记编辑视图
            mRadioButtonEdite.setChecked(true);
            mTitleView.setText("Mowa");

            pagerNumber=1;
            Log.d(TAG, "选中笔记编辑视图: "+pagerNumber);
            break;
        }
        case 2:{//选中个人发现视图
            mRadioButtonFind.setChecked(true);
            mTitleView.setText("发 现");
            pagerNumber=2;
            Log.d(TAG, "选中个人发现视图: "+pagerNumber);
            break;
        }
    }
    }


    @Override
    //ViewPager页面滚动
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    //ViewPager页面滚动状态变化
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    //底部菜单栏监听
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.button_List:{
                mViewPager.setCurrentItem(0);
                break;
            }
            case R.id.button_Edie:{
                mViewPager.setCurrentItem(1);
                break;
            }
            case R.id.button_Find:{
                mViewPager.setCurrentItem(2);

                break;
            }
        }
    }

    //启动登录activity
    public void startLoginActivity(Context context){
        Intent intent=new Intent(context,Login_Activity.class);
        startActivity(intent);
    }
    //设置隐藏状态栏
    private void hideStatusBar() {
        //判断设备版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        //通知栏所需颜色
        tintManager.setStatusBarTintResource(R.color.actionBarStyle);
    }
    //SDK—19设置状态栏
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
    //封装toast
    private void toast(String str){
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
    @Override
    //返回键事件处理
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            final AlertDialog.Builder builder=new AlertDialog.Builder(mContext,5);
            builder.setTitle("提示");
            builder.setMessage(" 退出 Mowa ？");
            builder.setCancelable(false);
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    toast("退出Mowa");
                    finish();
                }
            });
            builder.show();
        }
        return false;
    }
}
