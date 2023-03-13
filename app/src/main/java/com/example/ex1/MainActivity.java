package com.example.ex1;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

//MainActivity는 메인 THRED
public class MainActivity extends AppCompatActivity {
    ArrayList<NewsVO> array = null;
    RecyclerView list;
    NewsAdapter adapter;
    String query="노인";    //초기값 설정
    int start=0;
    int display = 0;
    private long lastTimeBackPressed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //NaverAPI에 search메서드가 static으로 정의되어있어 클래스를 생성하지않고 사용
        ImageButton buttonchat = (ImageButton) findViewById(R.id.wlehpage);
        buttonchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View chat) {
                Intent intent = new Intent(MainActivity.this, GoogleMap.class);
                startActivity(intent);
            }
        });
        ImageButton buttongame = (ImageButton) findViewById(R.id.gamepage);
        buttongame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View game) {
                Intent intent = new Intent(MainActivity.this,GameActivity.class);
                startActivity(intent);
            }
        });



        getSupportActionBar().setTitle("뉴스페이지");

        list=findViewById(R.id.list);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        list.setLayoutManager(manager);

        array=new ArrayList<NewsVO>();
        //클래스 생성
        new NaverThread().execute();

        ImageButton btnmore=findViewById(R.id.btnmore);
        btnmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start += 10;//더보기 클릭시 넘어가는 뉴스개수
                display +=10;
                new NaverThread().execute();
            }
        });
        RecyclerDecoration_Height decoration_height = new RecyclerDecoration_Height(20);
        list.addItemDecoration(decoration_height);


    }

    @Override
    public void onBackPressed()
    {
        if(System.currentTimeMillis() - lastTimeBackPressed < 1500){
            finish();
            return;
        }
        Toast.makeText(this,"한번더 뒤로가기 클릭시 앱을 종료합니다.",Toast.LENGTH_SHORT).show();
        lastTimeBackPressed = System.currentTimeMillis();

    }

    //naver접속 위한 thread 정의
    //BACK THREAD
    class NaverThread extends AsyncTask<String, String, String>{
        @Override
        protected String doInBackground(String... strings) {
            //System.out.println(".....................................");
            return NaverAPI.search(query,start,display);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        //결과출력
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //System.out.println(s);


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
                    System.out.println("결과............." + vo.getLink());


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
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.requestFocusFromTouch();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                query=query;
                start=0;
                display=0;
                array.clear();
                new NaverThread().execute();
              return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                query=newText;
                start=0;
                display=0;
                array.clear();
                new NaverThread().execute();
              return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

}