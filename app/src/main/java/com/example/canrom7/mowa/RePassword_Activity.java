package com.example.canrom7.mowa;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.canrom7.mowa.DBbean.User;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

public class RePassword_Activity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG="RePassword_Activity===>";
    private static final int  SEND=0x1;
    private EditText editrePasswordnname;
    private EditText editrePassword;
    private EditText editrePasswordphone;
    private EditText editrePasswordyanzhengma;
    private Button buttonsend;
    private Button buttonrePassword;
    private Thread mThread;
    private User mUser;
    private BmobSMS mBmobSMS;
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
                        buttonsend.setClickable(false);
                        buttonsend.setText(str+"秒后获取");
                    }else {
                        buttonsend.setClickable(true);
                        buttonsend.setText("获取验证码");
                    }
                }
            }
            return true;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_password_);
        //隐藏状态栏
        hideStatusBar();
        this.buttonrePassword = (Button) findViewById(R.id.button_rePassword);
        this.buttonsend = (Button) findViewById(R.id.button_send);
        this.editrePasswordyanzhengma = (EditText) findViewById(R.id.edit_rePassword_yanzhengma);
        this.editrePasswordphone = (EditText) findViewById(R.id.edit_rePassword_phone);
        this.editrePassword = (EditText) findViewById(R.id.edit_rePassword);
        this.editrePasswordnname = (EditText) findViewById(R.id.edit_rePassword_nname);
        //注册点击事件
        this.buttonrePassword.setOnClickListener(this);
        this.buttonsend.setOnClickListener(this);
    }

    @Override
    //点击事件监听
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_rePassword: {//点击修改密码按钮事件
                userRePassword();
                break;
            }
            case R.id.button_send:{//点击获取验证码事件
                sendMsg();
                break;
            }
        }
    }

    //重置密码按钮业务
    private void userRePassword() {
        if (!TextUtils.isEmpty(editrePasswordphone.getText())){//判断手机号码是否为空
            if (!TextUtils.isEmpty(editrePassword.getText())){//判断新密码是否为空
                if (editrePassword.getText().length()<6){
                    toast("密码必须超过6个字符");
                    return;
                }
                pass=editrePassword.getText().toString();
                if (!TextUtils.isEmpty(editrePasswordyanzhengma.getText())){//判断验证码是否为空
                    smsCode=editrePasswordyanzhengma.getText().toString();
                    //调用验证方法
                    smsCodeProving();
                }else {
                    toast("验证码不允许为空");
                }
            }else {
                toast("新密码不允许为空");
            }
        }else {
            toast("手机号码不允许为空");
        }
    }
    //发送短信业务
    private void sendMsg() {
        if (TextUtils.isEmpty(editrePasswordphone.getText())){
            Toast.makeText(RePassword_Activity.this, "请输入手机号码！", Toast.LENGTH_SHORT).show();
            return;
        }else {
            if (editrePasswordphone.getText().length()!=11){
                Toast.makeText(RePassword_Activity.this, "手机号码不正确！", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        this.mUser =new User();
        phone=editrePasswordphone.getText().toString();
        mBmobSMS.requestSMSCode(phone,"repass", new QueryListener<Integer>() {
                    @Override
                    public void done(Integer smsId,BmobException ex) {
                        if(ex==null){//验证码发送成功
                            Log.e(TAG, "done:验证码发送成功== "+smsId);
                            startThrad();
                            Toast.makeText(RePassword_Activity.this, "短信已获取", Toast.LENGTH_SHORT).show();
                        }else {
                            Log.e(TAG, "done:验证码发送失败== "+smsId);
                            Toast.makeText(RePassword_Activity.this, "系统忙，请重新获取", Toast.LENGTH_SHORT).show();
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

    //短信验证码回调
    private void smsCodeProving() {

        mUser.resetPasswordBySMSCode(smsCode,pass, new UpdateListener() {
            @Override
            public void done(BmobException e){
                if(e==null){//短信验证码已验证成功
                    Log.e("验证短信：", "验证通过");
                    Toast.makeText(RePassword_Activity.this, "密码修改成功！", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(RePassword_Activity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                    Log.e("验证短信", "验证失败：code ="+e.getErrorCode()+",msg = "+e.getLocalizedMessage());
                }
            }
        });

    }

    private void toast(String info){
        Toast.makeText(RePassword_Activity.this, info, Toast.LENGTH_SHORT).show();
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
    //释放资源
    protected void onDestroy() {
        mThread=null;
        mBmobSMS=null;
        super.onDestroy();
    }
}
