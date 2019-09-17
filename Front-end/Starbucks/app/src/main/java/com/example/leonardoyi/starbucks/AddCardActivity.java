package com.example.leonardoyi.starbucks;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;

import com.example.leonardoyi.starbucks.Restapi.OperationListener;
import com.example.leonardoyi.starbucks.Restapi.Retrofits;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddCardActivity extends AppCompatActivity {

    @BindView(R.id.newCardNumber)TextInputEditText newCardNumber;
    @BindView(R.id.newCardCode)TextInputEditText newCardCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcard);
        ButterKnife.bind(this);
    }



    @OnClick(R.id.submitCard)
    public void submitCardInfo(){
        String cardNumber = newCardNumber.getText().toString();
        String cardCode = newCardCode.getText().toString();

        if(cardNumber.equalsIgnoreCase("")
                || cardCode.equalsIgnoreCase("") ){

        }else{
            String[] Params = new String[2];
            Params[0] = cardNumber;
            Params[1] = cardCode;
            AddCardOperation addCardOperation = new AddCardOperation();
            addCardOperation.executeOnExecutor(android.os.AsyncTask.THREAD_POOL_EXECUTOR, Params);
        }

    }

    class AddCardOperation extends AsyncTask<String, Void, Void>{


        @Override
        protected Void doInBackground(String... params) {
            Retrofits.getInstance().setListerner(AddCardListener).addCard(params[0],params[1]);
            return null;
        }
    }

    OperationListener AddCardListener = new OperationListener() {
        @Override
        public void onSuccess(ArrayList returnString) {
            finish();
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
