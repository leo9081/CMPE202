package com.example.leonardoyi.starbucks;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leonardoyi.starbucks.Restapi.Card;
import com.example.leonardoyi.starbucks.Restapi.OperationListener;
import com.example.leonardoyi.starbucks.Restapi.Retrofits;
import com.example.leonardoyi.starbucks.Restapi.UserProfile;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CardActivity extends AppCompatActivity {

    @BindView(R.id.userName)
    TextView userName;

    @BindView(R.id.cardNumber)
    TextView cardNumber;

    @BindView(R.id.codeNumber)
    TextView codeNumber;

    @BindView(R.id.balance)
    TextView balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        userName.setText("Welcome: " + UserProfile.getInstance().getLastName());

        refreshPage();

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshPage();
    }

    public void refreshPage(){
        cardNumber.setText("Number: " + Card.getInstance().getCardNum());
        codeNumber.setText("Code: " + Card.getInstance().getCardCode());
        balance.setText("$ " + Double.toString(Card.getInstance().getBalance()));
    }

    @OnClick(R.id.fab)
    public void addCard(){
        Intent i = new Intent(this,AddCardActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.payment)
    public void payforDrink(){
        double balance = Card.getInstance().getBalance();
        if(balance < 1.5){
            Toast.makeText(this,"Balance is not enough",Toast.LENGTH_LONG).show();
        }else {
            MakePaymentOperation makePaymentOperation = new MakePaymentOperation();
            makePaymentOperation.executeOnExecutor(android.os.AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    @OnClick(R.id.history)
    public void ordersHistory(){
        Intent i = new Intent(this, OrderHistoryActivity.class);
        startActivity(i);
    }

    class MakePaymentOperation extends AsyncTask<String,Void,Void>{

        @Override
        protected Void doInBackground(String... params) {
            Retrofits.getInstance().setListerner(paymentListener).makePayment();
            return null;
        }
    }

    OperationListener paymentListener  = new OperationListener() {
        @Override
        public void onSuccess(ArrayList returnString) {
            double balance = Card.getInstance().getBalance();
            Card.getInstance().setBalance(balance - 1.5);
            Toast.makeText(getBaseContext(),"Payment Successful",Toast.LENGTH_LONG).show();
            refreshPage();
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
