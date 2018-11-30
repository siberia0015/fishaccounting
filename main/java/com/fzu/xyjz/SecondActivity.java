package com.fzu.xyjz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        AVUser user=AVUser.getCurrentUser();
        TextView phone=(TextView)findViewById(R.id.phone);
        final Button goBack=(Button)findViewById(R.id.goBack);
        final Button personalInfo=(Button)findViewById(R.id.personalInfo);
        phone.setText(user.getString("nickname"));
        final AVUser avUser=AVUser.getCurrentUser();
        personalInfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SecondActivity.this.finish();
                Intent intent=new Intent(SecondActivity.this,PersonalInfoActivity.class);
                startActivity(intent);
            }
        });
        goBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SecondActivity.this.finish();
                startActivity(new Intent(SecondActivity.this, LoginActivity.class));
                Toast.makeText(SecondActivity.this, "返回", Toast.LENGTH_SHORT).show();
            }
        });



    }

}
