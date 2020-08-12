package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    public static final String BUNDLE_USER_SIGNUP = "Bundle_user_signup";
    public static final String BUNDLE_PASSWORD_SIGNUP = "BUNDLE_PASSWORD_signup";
    public static final String SINGED_UP_USERNAME = "Singed_UP_Username";
    public static final String SIGNED_UP_PASSWORD = "signed_up_password";
    private EditText mUserNameSignup;
    private EditText mPasswordSignup;
    private Button mButtonSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setTitle("Sign up");

        findView();
        setListeners();

        Intent intent = getIntent();
        String name = intent.getStringExtra(LoginActivity.EXTRA_USER_NAME);
        String user = intent.getStringExtra(LoginActivity.EXTRA_PASSWORD);
        if (name!=null && user!=null) {
            mUserNameSignup.setText(name);
            mPasswordSignup.setText(user);
        }

    }

    private void findView() {
        mUserNameSignup = findViewById(R.id.editText_username_signup);
        mPasswordSignup = findViewById(R.id.editText_password_signup);
        mButtonSignup = findViewById(R.id.btn_signup);
    }

    private void setListeners() {

        mButtonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUserNameSignup.getText().toString().trim().length()>0 &&
                        mPasswordSignup.getText().toString().trim().length()>0) {

                    setShownAnswerResult();

                }
            }
        });

    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(BUNDLE_USER_SIGNUP,mUserNameSignup.getText().toString());
        outState.putString(BUNDLE_PASSWORD_SIGNUP,mPasswordSignup.getText().toString());
    }

    private void setShownAnswerResult(){
        Intent intent = new Intent();
        intent.putExtra(SINGED_UP_USERNAME,mUserNameSignup.getText().toString());
        intent.putExtra(SIGNED_UP_PASSWORD,mPasswordSignup.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }

}