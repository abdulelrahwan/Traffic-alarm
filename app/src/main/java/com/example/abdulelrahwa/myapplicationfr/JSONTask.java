package com.example.abdulelrahwa.myapplicationfr;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Abdul Elrahwa on 2018-01-28.
 */

public class JSONTask extends AsyncTask<String, String, String> {
    int valueint;
    MainActivity ac;



    public JSONTask(MainActivity ac) {
        this.ac = ac;
    }

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();

            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            String finalJson = buffer.toString();
            JSONObject parentObject = new JSONObject(finalJson);
            JSONArray parentArray = parentObject.getJSONArray("rows");
            JSONObject finalObject = parentArray.getJSONObject(0);


            String secondaryJson = finalObject.toString();
            JSONObject secondParentObject = new JSONObject(secondaryJson);
            JSONArray secondaryArray = secondParentObject.getJSONArray("elements");
            JSONObject secondaryObject = secondaryArray.getJSONObject(0);

            String teritaryJSON = secondaryObject.toString();
            JSONObject teritaryParentObject = new JSONObject(teritaryJSON);
            JSONObject teritaryObject = teritaryParentObject.getJSONObject("duration");
            String finalDuration = teritaryObject.toString();
            String value = finalDuration.substring(9,11);
            valueint = Integer.parseInt(value);



//                String realJSON = teritaryObject.toString();
//                JSONObject realParentObject = new JSONObject(realJSON);
//                JSONObject realObject = realParentObject.getJSONObject("text");

//                String realJson = teritaryObject.toString();
//                JSONObject realParentObject = new JSONObject(realJson);
//                JSONObject realObject = realParentObject.getJSONObject("text");



            return value;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
        }
        finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }
    protected void onPostExecute(String result){
        super.onPostExecute(result);
        //tvData.setText(result);
        ac.setAlarm(valueint);
    }
}