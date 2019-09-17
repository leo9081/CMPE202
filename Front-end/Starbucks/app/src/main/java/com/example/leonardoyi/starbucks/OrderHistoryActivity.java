package com.example.leonardoyi.starbucks;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.leonardoyi.starbucks.Restapi.OperationListener;
import com.example.leonardoyi.starbucks.Restapi.Order;
import com.example.leonardoyi.starbucks.Restapi.Retrofits;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class OrderHistoryActivity extends AppCompatActivity{

    ArrayList<Order> orderArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymenthistory);
        ButterKnife.bind(this);


        GetAllorders getAllorders = new GetAllorders();
        getAllorders.executeOnExecutor(android.os.AsyncTask.THREAD_POOL_EXECUTOR);


    }


    public void init() {
        TableLayout ll = (TableLayout) findViewById(R.id.displayLinear);

        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setTextSize(20);
        tv0.setText(" No ");
        tv0.setTextColor(Color.BLACK);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setTextSize(20);
        tv1.setText(" cardNUmnber ");
        tv1.setTextColor(Color.BLACK);
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setTextSize(20);
        tv2.setText(" date ");
        tv2.setTextColor(Color.BLACK);
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(this);
        tv3.setTextSize(20);
        tv3.setText(" price ");
        tv3.setTextColor(Color.BLACK);
        tbrow0.addView(tv3);
        ll.addView(tbrow0);
        for (int i = 0; i < orderArrayList.size(); i++) {
            TableRow tbrow = new TableRow(this);
            TextView t1v = new TextView(this);
            t1v.setTextSize(15);
            t1v.setText("" + i);
            t1v.setTextColor(Color.BLACK);
            t1v.setGravity(Gravity.CENTER);
            tbrow.addView(t1v);
            TextView t2v = new TextView(this);
            t2v.setTextSize(15);
            t2v.setText(orderArrayList.get(i).getCardNum());
            t2v.setTextColor(Color.BLACK);
            t2v.setGravity(Gravity.CENTER);
            tbrow.addView(t2v);
            TextView t3v = new TextView(this);
            t3v.setTextSize(15);
            t3v.setText(orderArrayList.get(i).getPaymentTime());
            t3v.setTextColor(Color.BLACK);
            t3v.setGravity(Gravity.CENTER);
            tbrow.addView(t3v);
            TextView t4v = new TextView(this);
            t4v.setTextSize(15);
            t4v.setText(orderArrayList.get(i).getCost().toString());
            t4v.setTextColor(Color.BLACK);
            t4v.setGravity(Gravity.CENTER);
            tbrow.addView(t4v);
            ll.addView(tbrow);
        }
    }


    class GetAllorders extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... params) {
            Retrofits.getInstance().setListerner(getOrderListener).getPaymentHistory();
            return null;
        }
    }

    OperationListener getOrderListener = new OperationListener() {
        @Override
        public void onSuccess(ArrayList returnString) {
            orderArrayList = returnString;
            init();
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
