package com.diary.android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diary.android.db.Users;

import org.litepal.crud.DataSupport;

import java.util.List;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_register;

    private EditText name_edit;
    private EditText pass_edit;
    private EditText checkPassword_edit;

    private TextView checkPhase_username;

    private LinearLayout checkUserName;
    private LinearLayout checkPassword;
    private LinearLayout checkPasswordAgain;

    private ImageView correct_username;
    private ImageView correct_password;
    private ImageView correct_passwordAgain;

    private Boolean isCorrectUsername = true;
    private Boolean isCorrectPassword = true;
    private Boolean isCorrectPasswordAgain = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btn_register = (Button) findViewById(R.id.btn_register);
        name_edit = (EditText) findViewById(R.id.name_edit);
        checkPassword_edit = (EditText) findViewById(R.id.checkPassword_edit);
        pass_edit = (EditText) findViewById(R.id.pass_edit);
        checkUserName = (LinearLayout) findViewById(R.id.register_checkUserName);
        checkPassword = (LinearLayout) findViewById(R.id.register_checkPassword);
        checkPasswordAgain = (LinearLayout) findViewById(R.id.checkPasswordAgain);
        checkPhase_username = (TextView) findViewById(R.id.check_phase_username);
        correct_username = (ImageView) findViewById(R.id.correct_username);
        correct_password = (ImageView) findViewById(R.id.correct_password);
        correct_passwordAgain = (ImageView) findViewById(R.id.correct_passwordAgain);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                check_username();
                check_password();
                check_passwordAgain();
                if(isCorrectPasswordAgain && isCorrectPassword && isCorrectUsername) {
                    AlertDialog alertDialog2 = new AlertDialog.Builder(this)
                            .setTitle("提示")
                            .setMessage("注册成功")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .create();
                    alertDialog2.show();
                }
                break;
            default:
                break;
        }
    }

    private void check_passwordAgain() {
        String password = pass_edit.getText().toString();
        String passwordAgain = checkPassword_edit.getText().toString();
        if(password.equals(passwordAgain) && !passwordAgain.equals("")){
            isCorrectPasswordAgain = true;
        } else{
            isCorrectPasswordAgain = false;
        }

        if(isCorrectPasswordAgain) {
            correct_passwordAgain.setVisibility(View.VISIBLE);
            checkPasswordAgain.setVisibility(View.GONE);
        } else {
            correct_passwordAgain.setVisibility(View.GONE);
            checkPasswordAgain.setVisibility(View.VISIBLE);
        }
    }

    private void check_password() {
        String password = pass_edit.getText().toString();
        if (password.length() > 12 || password.length() < 6) {
            isCorrectPassword = false;
        } else {
            isCorrectPassword = true;
        }

        if(isCorrectPassword) {
            correct_password.setVisibility(View.VISIBLE);
            checkPassword.setVisibility(View.GONE);
        } else {
            correct_password.setVisibility(View.GONE);
            checkPassword.setVisibility(View.VISIBLE);
        }
    }

    private void check_username() {
        String userName = name_edit.getText().toString();
        if(userName.length() > 6){
            checkPhase_username.setText("用户名长度限制为6位");
            isCorrectUsername = false;
        } else if (userName.equals("")){
            checkPhase_username.setText("请输入用户名");
            isCorrectUsername = false;
        } else {
            List<Users> userNames = DataSupport.select("username")
                    .find(Users.class);
            for (Users user : userNames) {
                if (user.getUsername().equals(userName)) {
                    checkPhase_username.setText("用户名已存在");
                    isCorrectUsername = false;
                }
            }
            isCorrectUsername = true;
        }

        if( !isCorrectUsername ) {
            correct_username.setVisibility(View.GONE);
            checkUserName.setVisibility(View.VISIBLE);
        } else {
            correct_username.setVisibility(View.VISIBLE);
            checkUserName.setVisibility(View.GONE);
        }
    }
}