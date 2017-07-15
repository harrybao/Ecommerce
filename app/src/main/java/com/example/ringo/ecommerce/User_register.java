package com.example.ringo.ecommerce;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hh on 2017/5/22.
 */

public class User_register {
    private String username="";
    private String password="";
    public String hh="";
    public User_register(String username, String password){
        this.username= username;
        this.password= password;
    }
    public void sendRequestWithOkHttp_Json() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://10.0.2.2:8080/webtt/servlet/Uploadusermessage?username="+username+"&password="+password)
                            .build();
                    Response response = null;
                    response = client.newCall(request).execute();
                    String responseData = "["+response.body().string()+"]";
//                    showResponse(responseData);
//                    parseXMLWithPull(responseData);
                    parseJSONWithJSONObject(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }).start();
    }
    private void parseJSONWithJSONObject(String jsonData) {
        try {
            JSONArray jsonAarry = new JSONArray(jsonData);
            for(int i=0; i<jsonAarry.length();i++){
                JSONObject jsonObject = jsonAarry.getJSONObject(i);
                hh = jsonObject.getString("message");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
