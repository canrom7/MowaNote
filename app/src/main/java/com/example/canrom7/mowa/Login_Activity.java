package com.example.canrom7.mowa;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.canrom7.mowa.DBbean.User;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/*
* 登录页
* 状态：完成
*/
public class Login_Activity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG="Login_Activity";
    private static final int USER=0X1;
    private   String userName;
    private   String userPass;
    //上下文
    Context mContext;
    private User mUser;
    private android.widget.EditText editloginname;
    private android.widget.EditText editloginpass;
    private android.widget.Button buttonlogin;
    private android.widget.TextView button_zhuce;
    private android.widget.TextView button_repass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        mContext=Login_Activity.this;
        //隐藏状态栏
        hideStatusBar();
        this.button_zhuce = (TextView) findViewById(R.id.button_zhuce);
        this.buttonlogin = (Button) findViewById(R.id.button_login);
        this.button_repass= (TextView) findViewById(R.id.button_repass);
        //设置登录事件监听
        this.buttonlogin.setOnClickListener(this);
        //设置注册事件监听
        this.button_zhuce.setOnClickListener(this);
        //设置找回密码监听
        this.button_repass.setOnClickListener(this);
        this.editloginpass = (EditText) findViewById(R.id.edit_loginpass);
        this.editloginname = (EditText) findViewById(R.id.edit_loginname);
    }

    @Override
    //全部点击事件
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_login:{//点击登录按钮事件
                userLogin();
                break;
            }
            case R.id.button_zhuce:{//点击注册超链接事件
                startRegisterActivity(mContext);
                break;
            }
            case R.id.button_repass:{//点击找回密码事件
                rePassword();
                break;
            }
        }
    }

    //重置密码业务
    private void rePassword() {
        Intent intent=new Intent(mContext,RePassword_Activity.class);
        startActivity(intent);
    }

    //用户登录业务
    private void userLogin() {
        if (TextUtils.isEmpty(editloginname.getText())){
            toast("用户名不允许为空");
            return;
        }
        if (TextUtils.isEmpty(editloginpass.getText())){
            toast("密码不允许为空");
            return;
        }
        userName=editloginname.getText().toString();
        userPass=editloginpass.getText().toString();
        mUser=new User();
        mUser.setUsername(userName);
        mUser.setPassword(userPass);
        mUser.login(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e==null){
                    toast("登录成功");
                    mUser=user;
                    SharedPreferences preferences =getSharedPreferences("User",MODE_PRIVATE);
                    SharedPreferences.Editor editor= preferences.edit();
                    editor.putBoolean("isLogin",true);
                    editor.putString("userId",mUser.getObjectId());
                    editor.commit();
                    startMainActivity(mContext);
                    finish();
                    Log.d(TAG, "done: 登录成功输出用户信息"+mUser.toString());
                }else {
                    toast("登录失败，检查用户名密码");
                }
            }
        });
    }


    //启动注册activity
    public void startRegisterActivity(Context context){
        Intent intent=new Intent(context,Register_Activity.class);
        startActivityForResult(intent,USER);
    }

    @Override
    //注册activity数据返回回调
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult: activity回调，回传数据"+requestCode);
        switch (resultCode){
            case USER:{
                    userName=data.getStringExtra("userName");
                    editloginname.setText(userName);
                break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //设置隐藏状态栏
    private void hideStatusBar() {
        //去标题栏
        getSupportActionBar().hide();
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
        Toast.makeText(Login_Activity.this, str, Toast.LENGTH_SHORT).show();
    }
    //登录成功启动主页
    private void startMainActivity(Context context){
        Intent intent=new Intent(context,MainActivity.class);
        startActivity(intent);
    }
    @Override
    //返回键事件处理
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            final AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
            builder.setTitle("提示");
            builder.setMessage("确定退出登录？");
            builder.setCancelable(false);
            builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                   toast("退出");
                    finish();
                }
            });
            builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(Login_Activity.this, "取消退出", Toast.LENGTH_SHORT).show();
                }
            });
            builder.show();
        }
        return false;
    }
}
