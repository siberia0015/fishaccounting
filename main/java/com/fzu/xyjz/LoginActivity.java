package com.fzu.xyjz;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.CloudQueryCallback;
import com.avos.avoscloud.DeleteCallback;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.SaveCallback;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText phoneText = (EditText) findViewById(R.id.phoneText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);

        final Button login = (Button) findViewById(R.id.login);
        final Button smsLogin = (Button) findViewById(R.id.smsLogin);
        final Button register = (Button) findViewById(R.id.register);
        final Button forgetPW = (Button) findViewById(R.id.forgetPW);
        final Button toBook = (Button) findViewById(R.id.toBook);
        final Button test = (Button) findViewById(R.id.test);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        phoneText.setText(username);

        //register
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.finish();
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        //login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String phone = phoneText.getText().toString();
                String password = passwordText.getText().toString();
                if (phoneText.getText().length() != 11) {
                    Toast.makeText(LoginActivity.this, "手机号格式错误！", Toast.LENGTH_SHORT).show();
                } else if (passwordText.getText().length() < 6 || passwordText.getText().length() > 13) {
                    Toast.makeText(LoginActivity.this, "密码长度必须在6~12位！", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(LoginActivity.this, "登陆中...", Toast.LENGTH_SHORT).show();
                    AVUser.loginByMobilePhoneNumberInBackground(phone, password, new LogInCallback<AVUser>() {
                        @Override
                        public void done(AVUser avUser, AVException e) {
                            if (e == null) {
                                LoginActivity.this.finish();
                                Toast.makeText(LoginActivity.this, "登陆成功！", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, SecondActivity.class);

                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginActivity.this, "登陆失败！", Toast.LENGTH_SHORT).show();
                                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        //smsLogin
        smsLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "敬请期待！", Toast.LENGTH_SHORT).show();
            }
        });

        //forgetPW
        forgetPW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(LoginActivity.this)
                        .setTitle("找回密码")
                        .setMessage("请联系客服qq523260014")
                        .setPositiveButton("好的", null)
                        .show();
            }
        });
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserBean user=new UserBean();
                ArrayList<Integer> book_id=new ArrayList<Integer>();
                book_id.add(1);
                book_id.add(2);
                book_id.add(4);
                user.combineRecordCloud(book_id,"新账簿");
                new AlertDialog.Builder(LoginActivity.this)
                        .setTitle("测试成功")
                        .setMessage("CQL测试")
                        .setPositiveButton("好的", null)
                        .show();
            }
        });
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this, "qhGj0XwqGtuqejmCz9cgdu75-gzGzoHsz", "Wmp0zmNkJduVDXxTGz8JQqaj");
        AVOSCloud.setDebugLogEnabled(true);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Login Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
