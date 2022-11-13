package com.example.ex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

//MainActivity는 메인 THRED
public class MainActivity extends AppCompatActivity {
    ArrayList<NewsVO> array;
    RecyclerView list;
    NewsAdapter adapter;
    //초기값 설정
    String query="수능";
    int start=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //NaverAPI에 search메서드가 static으로 정의되어있어 클래스를 생성하지않고 사용


        getSupportActionBar().setTitle("뉴스검색");



        list=findViewById(R.id.list);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        list.setLayoutManager(manager);

        array=new ArrayList<NewsVO>();
        //클래스 생성
        new NaverThread().execute();

        FloatingActionButton btnmore=findViewById(R.id.btnmore);
        btnmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start += 10;
                new NaverThread().execute();
            }
        });
    }
    //naver접속 위한 thread 정의
    //BACK THREAD
    class NaverThread extends AsyncTask<String, String, String>{
        @Override
        protected String doInBackground(String... strings) {
            //System.out.println(".....................................");
            return NaverAPI.search(query,start);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        //결과출력
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            System.out.println(s);


            try {

                //total값 가져오기
                int total=new JSONObject(s).getInt("total");
                System.out.println("total" + total);

                JSONArray jArray=new JSONObject(s).getJSONArray("items");
                for(int i=0; i<jArray.length(); i++){
                    JSONObject obj=jArray.getJSONObject(i);
                    NewsVO vo=new NewsVO();
                    vo.setTitle(obj.getString("title"));
                    vo.setContent(obj.getString("description"));
                    vo.setLink(obj.getString("link"));
                    array.add(vo);
                    System.out.println("결과............." + vo.getTitle());

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            adapter=new NewsAdapter(array,MainActivity.this);
            list.setAdapter(adapter);
            list.scrollToPosition(start);



        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem search=menu.findItem(R.id.search);
        SearchView searchView=(SearchView)search.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                query=query;
                start=1;
                array.clear();
                new NaverThread().execute();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                query=newText;
                start=1;
                array.clear();
                new NaverThread().execute();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}