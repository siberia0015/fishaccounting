package com.fzu.xyjz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVUser;

public class ChangeInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);
        final AVUser user=AVUser.getCurrentUser();
        final EditText nickname=(EditText) findViewById(R.id.newNickname);
        final EditText sex=(EditText)findViewById(R.id.newSex);
        final EditText city=(EditText)findViewById(R.id.newCity);
        final EditText birthday=(EditText)findViewById(R.id.newBirthday);
        Button goBack=(Button)findViewById(R.id.goBack2PersonalInfo);
        Button confirmInfoChange=(Button)findViewById(R.id.confirmInfoChange);
        nickname.setText(user.getString("nickname"));
        sex.setText(user.getString("sex"));
        city.setText(user.getString("city"));
        birthday.setText(user.getString("birthday"));

        confirmInfoChange.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                user.put("nickname",nickname.getText().toString());
                user.put("sex",sex.getText().toString());
                user.put("city",city.getText().toString());
                user.put("birthday",birthday.getText().toString());
                user.saveInBackground();
                Toast.makeText(ChangeInfoActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
            }
        });
        goBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(ChangeInfoActivity.this, PersonalInfoActivity.class);
                startActivity(intent);
            }
        });
    }
}
