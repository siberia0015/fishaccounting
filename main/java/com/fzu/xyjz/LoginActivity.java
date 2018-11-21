package com.fzu.xyjz;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText phoneText=(EditText)findViewById(R.id.phoneText);
        final EditText passwordText=(EditText)findViewById(R.id.passwordText);

        final Button login=(Button)findViewById(R.id.login);
        final Button smsLogin=(Button)findViewById(R.id.smsLogin);
        final Button register=(Button)findViewById(R.id.register);
        final Button forgetPW=(Button)findViewById(R.id.forgetPW);

        Intent intent=getIntent();
        String username=intent.getStringExtra("username");
        phoneText.setText(username);

        //register
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                LoginActivity.this.finish();
                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        //login
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final String phone=phoneText.getText().toString();
                String password=passwordText.getText().toString();
                if(phoneText.getText().length()!=11){
                    Toast.makeText(LoginActivity.this,"手机号格式错误！",Toast.LENGTH_SHORT).show();
                }else if(passwordText.getText().length()<66||passwordText.getText().length()>13){
                    Toast.makeText(LoginActivity.this,"密码长度必须在6~12位！",Toast.LENGTH_SHORT).show();
                }else {

                    Toast.makeText(LoginActivity.this, "登陆中...", Toast.LENGTH_SHORT).show();
                    AVUser.loginByMobilePhoneNumberInBackground(phone, password, new LogInCallback<AVUser>() {
                        @Override
                        public void done(AVUser avUser, AVException e) {
                            if (e == null) {
                                LoginActivity.this.finish();
                                Toast.makeText(LoginActivity.this, "登陆成功！", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(LoginActivity.this, SecondActivity.class);

                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginActivity.this, "登陆失败！", Toast.LENGTH_SHORT).show();
                                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
/*                    AVUser.logInInBackground(phone, password, new LogInCallback<AVUser>() {
                        @Override
                        public void done(AVUser avUser, AVException e) {
                            if (e == null) {
                                LoginActivity.this.finish();
                                Toast.makeText(LoginActivity.this, "登陆成功！", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(LoginActivity.this, SecondActivity.class);
                                intent.putExtra("username",phone);                                intent.putExtra("objectID",avUser.getObjectId());
                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });*/
                }
            }
        });
        //smsLogin
        smsLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(LoginActivity.this,"敬请期待！",Toast.LENGTH_SHORT).show();
                /*final String phone=phoneText.getText().toString();
                if(phoneText.getText().length()!=11){
                    Toast.makeText(LoginActivity.this,"手机号格式错误！",Toast.LENGTH_SHORT).show();
                }*//*else if() {

                }*//*else {
                    LoginActivity.this.finish();
                    Toast.makeText(LoginActivity.this, "验证码已发送至"+phone, Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(LoginActivity.this, ShortMessageActivity.class);
                    intent.putExtra("username",phone);

                    startActivity(intent);
                }*/
            }
        });

        //forgetPW
        forgetPW.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new AlertDialog.Builder(LoginActivity.this)
                        .setTitle("找回密码")
                        .setMessage("请联系客服qq523260014")
                        .setPositiveButton("好的", null)
                        .show();
            }
        });
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,"qhGj0XwqGtuqejmCz9cgdu75-gzGzoHsz","Wmp0zmNkJduVDXxTGz8JQqaj");
        AVOSCloud.setDebugLogEnabled(true);
    }
}
