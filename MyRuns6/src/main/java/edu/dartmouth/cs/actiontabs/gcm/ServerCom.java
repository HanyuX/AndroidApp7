package edu.dartmouth.cs.actiontabs.gcm;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import edu.dartmouth.cs.actiontabs.DataBaseHelper;
import edu.dartmouth.cs.actiontabs.databaseItem;

/**
 * Created by xuehanyu on 5/16/16.
 */
public class ServerCom {
    public static String post(String endpoint, Context context)
            throws IOException {
        URL url;
        try {
            url = new URL(endpoint);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("invalid url: " + endpoint);
        }
        StringBuilder bodyBuilder = new StringBuilder();
        ArrayList<databaseItem> data = (ArrayList<databaseItem>)new DataBaseHelper(context).allItems();

        // constructs the POST body using the parameters
        bodyBuilder.append("data=");
        for(int i = 0 ; i < data.size() ; ++i){
            if(i != 0)
                bodyBuilder.append("&");
            databaseItem item = data.get(i);
            bodyBuilder.append(item.getID()).append(",")
                        .append(item.getInputType()).append(",")
                        .append(item.getActivityType()).append(",")
                        .append(item.getDate()).append(",")
                        .append(item.getDuration()).append(",")
                        .append(item.getDistance()).append(",")
                        .append(item.getAvgSpeed()).append(",")
                        .append(item.getCalories()).append(",")
                        .append(item.getClimb()).append(",")
                        .append(item.getHeartRate()).append(",")
                        .append(item.getComment());
        }
        String body = bodyBuilder.toString();
        Log.d("xue",body);
        byte[] bytes = body.getBytes();
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setFixedLengthStreamingMode(bytes.length);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded;charset=UTF-8");
            // post the request
            OutputStream out = conn.getOutputStream();
            out.write(bytes);
            out.close();
            // handle the response
            int status = conn.getResponseCode();
            Log.d("TAGG", "" + status);
            if (status != 200) {
                throw new IOException("Post failed with error code " + status);
            }

            // Get Response
            InputStream is = conn.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\n');
            }
            rd.close();
            return response.toString();

        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
}
