package com.example.canrom7.mowa;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

public class Register_Activity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG="Register_Activity===>";
    private static final int  SEND=0x1;
    private static final int NOREGISTER=0x2;
    private Context mContext=this;
    private android.widget.TextView textTitleregisterlogo;
    private android.widget.EditText editregisternname;
    private android.widget.EditText editregisterpass;
    private android.widget.EditText editregisterphone;
    private android.widget.EditText editregisteryzm;
    private android.widget.Button buttonlogin;
    private android.widget.Button butoonsend;
    private Thread mThread;
    private BmobSMS mBmobSMS;
    private User mUser;
    private String name;
    private String pass;
    private String phone;
    private String smsCode;
    //消息处理
    private Handler mHandler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case SEND:{
                    String str= msg.obj.toString();

                    if (!"1".equals(str)){
                        butoonsend.setClickable(false);
                        butoonsend.setText(str+"秒后获取");
                    }else {
                        butoonsend.setClickable(true);
                        butoonsend.setText("获取验证码");
                    }
                }
            }
            return true;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);
        this.buttonlogin = (Button) findViewById(R.id.button_register);
        //隐藏状态栏
        hideStatusBar();
        this.editregisteryzm = (EditText) findViewById(R.id.edit_register_yanzhengma);
        this.editregisterphone = (EditText) findViewById(R.id.edit_register_phone);
        this.editregisterpass = (EditText) findViewById(R.id.edit_register_pass);
        this.editregisternname = (EditText) findViewById(R.id.edit_register_nname);
        this.textTitleregisterlogo = (TextView) findViewById(R.id.textTitle_register_logo);
        this.butoonsend= (Button) findViewById(R.id.button_send);
        this.mBmobSMS=new BmobSMS();

        //设置注册事件监听
        this.buttonlogin.setOnClickListener(this);
        this.butoonsend.setOnClickListener(this);
    }

    //全部点击事件
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_register: {//点击注册按钮事件
                userRegister();
                break;
            }
            case R.id.button_send:{//点击获取验证码事件
                    sendMsg();
                break;
            }
        }
    }
    //向用户发送验证码
    private void sendMsg() {
        if (TextUtils.isEmpty(editregisterphone.getText())){
            Toast.makeText(Register_Activity.this, "请输入手机号码！", Toast.LENGTH_SHORT).show();
            return;
        }else {
            if (editregisterphone.getText().length()!=11){
                Toast.makeText(Register_Activity.this, "手机号码不正确！", Toast.LENGTH_SHORT).show();
                return;
            }
        }
       this.mUser =new User();
        this.name=editregisternname.getText().toString();
       this.pass=editregisterpass.getText().toString();
        this.phone=editregisterphone.getText().toString();
        mUser.setMobilePhoneNumber(phone);//设置手机号码（必填）
        mUser.setUsername(name); //设置用户名，如果没有传用户名，则默认为手机号码
        mUser.setPassword(pass);//设置用户密码
        mBmobSMS.requestSMSCode(phone,"Mowa", new QueryListener<Integer>() {
            @Override
            public void done(Integer smsId,BmobException ex) {
                if(ex==null){//验证码发送成功
                    Log.e(TAG, "done:验证码发送成功== "+smsId);
                    startThrad();
                    Toast.makeText(Register_Activity.this, "短信已获取", Toast.LENGTH_SHORT).show();
                }else {
                    Log.e(TAG, "done:验证码发送失败== "+smsId);
                    Toast.makeText(Register_Activity.this, "系统忙，请重新获取", Toast.LENGTH_SHORT).show();
                }
            }
        }
        );

    }
    //开启线程定时阻塞短信发送按钮
    private void startThrad() {
        mThread=new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=59;i>0;i--){
                    try {
                        Thread.sleep(1000);
                        Message msg=new Message();
                        msg.what=SEND;
                        msg.obj=i;
                        mHandler.sendMessage(msg);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        mThread.start();
    }

    //注册验证业务
    private void userRegister() {
        if (!TextUtils.isEmpty(editregisternname.getText())){//判断用户名是否为空
            if (!TextUtils.isEmpty(editregisterpass.getText())){//判断密码是否为空
                if (editregisterpass.getText().length()<6){
                    toast("密码必须超过6个字符");
                    return;
                }
                if (!TextUtils.isEmpty(editregisterphone.getText())){//判断电话号码是否为空
                    if (editregisterphone.getText().length()!=11){
                        Toast.makeText(Register_Activity.this, "电话号码非法", Toast.LENGTH_SHORT).show();
                    }else {
                        if (TextUtils.isEmpty(editregisteryzm.getText())){
                            Toast.makeText(Register_Activity.this, "请输入接收的验证码", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        smsCode=editregisteryzm.getText().toString();
                        smsCodeProving();
                    }
                }else {//电话号码为空
                    Toast.makeText(Register_Activity.this, "电话号码不允许为空", Toast.LENGTH_SHORT).show();
                }
            }else {//密码为空
                Toast.makeText(Register_Activity.this, "请输入密码", Toast.LENGTH_SHORT).show();
            }
        }else {//用户名为空
            Toast.makeText(Register_Activity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
        }
    }
    //短信验证码回调
    private void smsCodeProving() {
        mUser.signOrLogin(smsCode, new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e){
                if(e==null){//短信验证码已验证成功
                    Log.e("验证短信：", "验证通过");
                    Log.e(TAG, "done:短信验证接收对象： "+user.toString() );
                    mUser=user;
                    Toast.makeText(Register_Activity.this, "帐号注册成功！", Toast.LENGTH_SHORT).show();
                    Intent intent=getIntent();
                    intent.putExtra("userName",mUser.getUsername());
                    Register_Activity.this.setResult(SEND,intent);
                    Log.d(TAG, "done: 启动登录页面"+mUser.toString());
                    finish();
                }else{
                    Toast.makeText(Register_Activity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                    Log.e("验证短信", "验证失败：code ="+e.getErrorCode()+",msg = "+e.getLocalizedMessage());
                }
            }
        });
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

    @Override
    //设置返回键监听事件
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            final AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
            builder.setTitle("提示");
            builder.setMessage("确定退出注册？");
            builder.setCancelable(false);
            builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent=getIntent();
                    Register_Activity.this.setResult(NOREGISTER,intent);
                    finish();
                }
            });
            builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(Register_Activity.this, "取消退出", Toast.LENGTH_SHORT).show();
                }
            });
            builder.show();
        }
        return false;
    }
    private void toast(String info){
        Toast.makeText(Register_Activity.this, info, Toast.LENGTH_SHORT).show();
    }

    @Override
    //资源关闭
    protected void onDestroy() {
        mThread=null;
        mBmobSMS=null;
        super.onDestroy();
    }
}
