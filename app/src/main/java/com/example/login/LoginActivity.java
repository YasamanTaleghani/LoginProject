package com.example.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    public static final int SIGNUP_REQUEST_CODE = 0;
    public static final String EXTRA_USER_NAME = "com.example.login.UserName";
    public static final String EXTRA_PASSWORD = "com.example.login.password";
    public static final String BUNDLE_USER_NAME = "Bundle_user_Name";
    public static final String BUNDLE_PASSWORD = "Bundle_password";
    public static final String BUNDLE_M_SIGNED_UP_USER_NAME = "mSignedUpUserName";
    public static final String BUNDLE_M_SIGNED_UP_PASS_WORD = "mSignedUpPassWord";
    private EditText mLoginUsername;
    private EditText mLoginPassword;
    private Button mLogin;
    private Button mSignup;
    private String mSignedUpUserName;
    private String mSignedUpPassWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (savedInstanceState!=null) {
            Toast.makeText(this,"notNuluu",Toast.LENGTH_LONG);
            mSignedUpUserName = savedInstanceState.getString(BUNDLE_M_SIGNED_UP_PASS_WORD);
            mSignedUpPassWord = savedInstanceState.getString(BUNDLE_M_SIGNED_UP_PASS_WORD);
        }

        findView();
        setListeners();

    }

    private void findView(){
        mLoginUsername = findViewById(R.id.editText_username);
        mLoginPassword = findViewById(R.id.editText_password);
        mLogin = findViewById(R.id.btn_login);
        mSignup = findViewById(R.id.btn_tosignupLayout);
    }

    private void setListeners(){

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mLoginUsername.getText().toString().length()==0 ||
                            mLoginPassword.getText().toString().length()==0) {

                    View parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, "نام کاربری و رمز عبور را کامل وارد کنید", Snackbar.LENGTH_LONG)
                            .setAction("CLOSE", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            })
                            .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                            .show();

                } else if (mSignedUpUserName == null || mSignedUpPassWord == null){

                    View parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, "نام کاربری و رمز عبور شما به ثبت نرسیده است", Snackbar.LENGTH_LONG)
                            .setAction("CLOSE", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            })
                            .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                            .show();

                }else if (mSignedUpUserName.equals(mLoginUsername.getText().toString()) &&
                        mSignedUpPassWord.equals(mLoginPassword.getText().toString()) ){

                    View parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, "نام کاربری و رمز عبور شما صحیح است", Snackbar.LENGTH_LONG)
                            .setAction("CLOSE", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            })
                            .setActionTextColor(getResources().getColor(android.R.color.holo_green_light ))
                            .show();

                } else{
                    View parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, "نام کاربری و رمز عبور شما اشتباه است", Snackbar.LENGTH_LONG)
                            .setAction("CLOSE", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            })
                            .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                            .show();
                }
            }
        });

        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mLoginUsername.getText().toString().trim().length()>0
                        && mLoginPassword.getText().toString().trim().length()>0){
                    String userName = mLoginUsername.getText().toString();
                    String passWord = mLoginPassword.getText().toString();
                    Intent intent = new Intent(LoginActivity.this,
                            SignupActivity.class);
                    intent.putExtra(EXTRA_USER_NAME,userName);
                    intent.putExtra(EXTRA_PASSWORD,passWord);
                    startActivityForResult(intent,SIGNUP_REQUEST_CODE);
                } else {
                    Intent intent = new Intent(LoginActivity.this,
                            SignupActivity.class);
                    startActivityForResult(intent,SIGNUP_REQUEST_CODE);
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString(BUNDLE_USER_NAME,mLoginUsername.getText().toString());
        outState.putString(BUNDLE_PASSWORD,mLoginPassword.getText().toString());
        outState.putString(BUNDLE_M_SIGNED_UP_USER_NAME,mSignedUpUserName);
        outState.putString(BUNDLE_M_SIGNED_UP_PASS_WORD,mSignedUpPassWord);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != SignupActivity.RESULT_OK || data == null)
            return;

        if (requestCode == SIGNUP_REQUEST_CODE) {

            mSignedUpUserName = data.getStringExtra(SignupActivity.SINGED_UP_USERNAME);
            mSignedUpPassWord = data.getStringExtra(SignupActivity.SIGNED_UP_PASSWORD);
            mLoginUsername.setText(mSignedUpUserName);
            mLoginPassword.setText(mSignedUpPassWord);

        }
    }

}