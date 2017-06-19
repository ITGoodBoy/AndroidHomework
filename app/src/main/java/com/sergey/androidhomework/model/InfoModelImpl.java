package com.sergey.androidhomework.model;

import android.os.AsyncTask;

import java.util.Random;

/**
 * Created by Sergey on 19.06.2017.
 */

public class InfoModelImpl implements InfoModel {

    @Override
    public void retrieveInfo(final MyAction<String> data) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                try {
                    Thread.sleep(5000);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
                return "some string";
            }
            @Override
            protected void onPostExecute(String s) {
                if (fakeRandomException()) {
                    try {
                        throw new NullPointerException();
                    } catch (Exception e) {
                        data.onError(e);
                    }
                } else data.onSuccess(s);
            }
        }.execute();
    }

    private boolean fakeRandomException() {
        int random50percent = new Random().nextInt(2);

        return random50percent == 0;
    }
}


