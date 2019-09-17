package com.example.leonardoyi.starbucks.Restapi;

import java.util.ArrayList;

public interface OperationListener {

    public void onSuccess(@SuppressWarnings("rawtypes") ArrayList returnString);

    public void onError(int errorCode, String message);

    public void onPreExecution();

    public void onPostExecution();

    public void onOperationProgressUpdate(String... updateParams);

}
