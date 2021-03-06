package com.example.findnumbergame;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Task extends AsyncTask<String, Void, String> {
    String sendMsg, receiveMsg;
    String rankUpdateip = "http://sbsbhome.duckdns.org:8090/testPjt/rankUpdate.jsp";
    String rankGetip = "http://sbsbhome.duckdns.org:8090/testPjt/rankGet.jsp";

    Task(String sendMsg) {
        this.sendMsg = sendMsg;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            String str;
            URL url;
            if (sendMsg.equals("update_rank")) {
                sendMsg = "user_name=" + strings[0] + "&score=" + strings[1];
                url = new URL(rankUpdateip);
            } else {
                sendMsg = "user_name=" + strings[0] + "&score=" + strings[1];
                url = new URL(rankGetip);
            }

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

            osw.write(sendMsg);
            osw.flush();

            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                receiveMsg = buffer.toString();
            } else {
                Log.i("통신 결과", conn.getResponseCode() + "에러");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return receiveMsg;
    }
}