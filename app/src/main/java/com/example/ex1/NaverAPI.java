package com.example.ex1;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.net.*;
import java.io.*;
public class NaverAPI {
    public static final String BASE_URL = "https://openapi.naver.com/v1/search/news.json?query=";
    public static String search(String query, int start) {
        String clientId = "7GrEUKj3HB3eKIyOvolk";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "zKpDPpuPQ6";//애플리케이션 클라이언트 시크릿값";
        HttpURLConnection con;
        try {
            String text = URLEncoder.encode(query, "UTF-8");
            String apiURL = BASE_URL + text; // json 결과
            apiURL += "&start" + start;

            URL url = new URL(apiURL);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());
            return response.toString();
        } catch (Exception e) {
            System.out.println("error..............." + e.toString());
            return e.toString();

        }
    }

}
