package com.fzu.xyjz;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.RequestMobileCodeCallback;
import com.avos.avoscloud.SignUpCallback;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final AVUser user=AVUser.getCurrentUser();
        final Button register=(Button)findViewById(R.id.tryRegister);
        final Button goBack=(Button)findViewById(R.id.goBack);
        final Button getVerificationCode=(Button)findViewById(R.id.getVerificationCode);
        final EditText phone=(EditText)findViewById(R.id.phone);
        final EditText password=(EditText)findViewById(R.id.password);
        final EditText VerificationCode=(EditText)findViewById(R.id.verificationCode);
        //getVerificationCode
        getVerificationCode.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(phone.getText().length()!=11){
                    Toast.makeText(RegisterActivity.this,"手机号格式错误！",Toast.LENGTH_SHORT).show();
                }else if(password.getText().length()<6||password.getText().length()>13){
                    Toast.makeText(RegisterActivity.this,"密码长度必须在6~12位！",Toast.LENGTH_SHORT).show();
                }/*else if(){

                }*/else {
                    AVOSCloud.requestSMSCodeInBackground(phone.getText().toString(), new RequestMobileCodeCallback() {
                        @Override
                        public void done(AVException e) {
                            // 发送失败可以查看 e 里面提供的信息
                            if(e==null) {
                                Toast.makeText(RegisterActivity.this, "短信已发送至" + phone.getText(), Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(RegisterActivity.this, "发送短信验证码失败" + phone.getText(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });
        //register
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(phone.getText().length()!=11){
                    Toast.makeText(RegisterActivity.this,"手机号格式错误！",Toast.LENGTH_SHORT).show();
                }else if(password.getText().length()<6){
                    Toast.makeText(RegisterActivity.this,"密码长度必须大于6！",Toast.LENGTH_SHORT).show();
                }/*else if(){

                }*/else{
                    AVUser.signUpOrLoginByMobilePhoneInBackground(phone.getText().toString(), VerificationCode.getText().toString(), new LogInCallback<AVUser>() {
                        @Override
                        public void done(AVUser avUser, AVException e) {
                            // 如果 e 为空就可以表示登录成功了，并且 user 是一个全新的用户
/*                            new AlertDialog.Builder(RegisterActivity.this)
                                    .setTitle("注册成功")
                                    .setMessage("注册成功")
                                    .setPositiveButton("好的", null)
                                    .show();*/
                            avUser.setPassword(password.getText().toString());
                            avUser.saveInBackground();
                            RegisterActivity.this.finish();
                            Intent intent=new Intent(RegisterActivity.this, SecondActivity.class);
                            startActivity(intent);
                        }
                    });

                    /*Toast.makeText(RegisterActivity.this,phone.getText(),Toast.LENGTH_SHORT).show();
                    AVUser user = new AVUser();// 新建 AVUser 对象实例
                    user.setUsername(phone.getText().toString());// 设置用户名
                    user.setPassword(password.getText().toString());// 设置密码
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(AVException e) {
                            if(e==null){
                                Log.d("saved","success!");// 保存成功之后，objectId 会自动从服务端加载到本地
                                RegisterActivity.this.finish();
                                Toast.makeText(RegisterActivity.this,"注册成功！",Toast.LENGTH_SHORT).show();
                                //Intent intent=new Intent(RegisterActivity.this, ShortMessageActivity.class);跳转到短信验证码页面
                                Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
                                intent.putExtra("phone",phone.getText());
                                startActivity(intent);
                            }else {
                                Toast.makeText(RegisterActivity.this,"注册失败！",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });*/
                }
            }
        });
        //goBack
        goBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
