package com.example.leonardoyi.starbucks;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.example.leonardoyi.starbucks.Restapi.OperationListener;
import com.example.leonardoyi.starbucks.Restapi.Retrofits;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.username) TextInputEditText usernaem;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.signinbutton) Button signinbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.signinbutton)
    public void userSignIn() {
        String[] Params = new String[2];
        Params[0] = usernaem.getText().toString();
        Params[1] = password.getText().toString();

        String[] msg = {"userName_cannot_be_blank","password_cannot_be_blank"};

        UserLogin userLogin = new UserLogin();

        userLogin.executeOnExecutor(android.os.AsyncTask.THREAD_POOL_EXECUTOR, Params);

    }

    class UserLogin extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            Retrofits.getInstance().setListerner(userLoginListerner).userLogin(params[0],params[1]);
            return null;
        }
    }

    public void loginSuccess(){
        Intent i = new Intent(this,CardActivity.class);
        startActivity(i);
    }

    OperationListener userLoginListerner = new OperationListener() {

        @Override
        public void onSuccess(ArrayList returnString) {
            loginSuccess();
        }

        @Override
        public void onError(int errorCode, String message) {

        }

        @Override
        public void onPreExecution() {

        }

        @Override
        public void onPostExecution() {

        }

        @Override
        public void onOperationProgressUpdate(String... updateParams) {

        }
    };

}
