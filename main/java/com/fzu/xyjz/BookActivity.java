package com.fzu.xyjz;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static android.R.attr.path;

public class BookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        Button goBack = (Button) findViewById(R.id.goBack2PersonalInfo);
        Button tryCombine = (Button) findViewById(R.id.tryCombine);
        final TextView a_id = (TextView) findViewById(R.id.book_a);
        final TextView b_id = (TextView) findViewById(R.id.book_b);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookActivity.this, PersonalInfoActivity.class);
                startActivity(intent);
            }
        });
        tryCombine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*//预计实现方法：把目标账簿中所有账目的账簿ID改成第一个账簿的账簿ID
                //创建一个DatabaseHelper对象
                DatabaseHelper dbHelper = new DatabaseHelper(BookActivity.this, "test_db");
                //取得一个只读的数据库对象
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                db.openOrCreateDatabase(String dbPath, SQLiteDatabase.CursorFactory factory);
                db.execSQL("update io set book_id = " + a_id + "where book_id =" + b_id);
                db.execSQL("delete from book where book_id = " + b_id);
                db.execSQL("update book set bookName = "+ newName + "where book_id = "+a_id);
                db.close();*/
                Toast.makeText(BookActivity.this, "合并成功！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
