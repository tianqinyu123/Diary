package com.diary.android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.diary.android.db.Users;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "LoginActivity";

    private boolean isLoginSuccess = true;

    private Button btn_login;
    private Button btn_register;
    private Button btn_createDatabase;

    private EditText name_edit;
    private EditText pass_edit;

    private LinearLayout check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        check = (LinearLayout) findViewById(R.id.check);
        name_edit = (EditText) findViewById(R.id.name_edit);
        pass_edit = (EditText) findViewById(R.id.pass_edit);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_createDatabase = (Button) findViewById(R.id.btn_createDatabase);
        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        btn_createDatabase.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if( checkUser() ) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.btn_register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_createDatabase:
                LitePal.getDatabase();
                break;
            default:
                break;
        }
    }

    private boolean checkUser() {
        if( isLoginSuccess ) {
            check.setVisibility(View.GONE);
        }
        String username = name_edit.getText().toString();
        String password = pass_edit.getText().toString();
        if(username.equals("") || password.equals("")){
            isLoginSuccess = false;
            check.setVisibility(View.VISIBLE);
            return false;
        }else {
            List<Users> users = DataSupport.select("username", "password", "userID")
                    .find(Users.class);
            for (Users user : users) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {

                    Log.d(TAG, user.getUsername());
                    Log.d(TAG, user.getPassword());
                    Log.d(TAG, user.getUserID());

                    AlertDialog alertDialog2 = new AlertDialog.Builder(this)
                            .setTitle("提示")
                            .setMessage("登陆成功")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .create();
                    alertDialog2.show();
                } else {
                    isLoginSuccess = false;
                }
            }
            if( !isLoginSuccess ) {
                check.setVisibility(View.VISIBLE);
                return false;
            }
        }
        return true;
    }
}