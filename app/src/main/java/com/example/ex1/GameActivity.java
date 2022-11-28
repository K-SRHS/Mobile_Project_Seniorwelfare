package com.example.ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {
    ListView listView;

    protected void onResume(){
        super.onResume();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamelist);

        listView = (ListView)this.findViewById(R.id.listView);

        ArrayList<String> items = new ArrayList<>();
        items.add("game0");
        items.add("game1");
        items.add("game2");
        items.add("game3");
        items.add("game4");
        items.add("game5");
        items.add("game6");
        CustomAdapter adapter = new CustomAdapter(this, 0, items);
        listView.setAdapter(adapter);
        getSupportActionBar().setTitle("게임목록");
    }
    private class CustomAdapter extends ArrayAdapter<String> {
        private ArrayList<String> items;

        public CustomAdapter(Context context, int textViewResourceId, ArrayList<String> objects) {
            super(context, textViewResourceId, objects);
            this.items = objects;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.gameitem, null);
            }

            // ImageView 인스턴스
            ImageView imageView = (ImageView)v.findViewById(R.id.gameimg);

            // 리스트뷰의 아이템에 이미지를 변경한다.
            if("game0".equals(items.get(position)))
                imageView.setImageResource(R.drawable.game0);
            else if("game1".equals(items.get(position)))
                imageView.setImageResource(R.drawable.game1);
            else if("game2".equals(items.get(position)))
                imageView.setImageResource(R.drawable.game2);
            else if("game3".equals(items.get(position)))
                imageView.setImageResource(R.drawable.game3);
            else if("game4".equals(items.get(position)))
                imageView.setImageResource(R.drawable.game4);
            else if("game5".equals(items.get(position)))
                imageView.setImageResource(R.drawable.game5);
            else if("game6".equals(items.get(position)))
                imageView.setImageResource(R.drawable.game6);



            TextView textView = (TextView)v.findViewById(R.id.name);
            if (position == 0) {
                textView.setText("소 골인시키기");
            }
            if (position == 1) {
                textView.setText("연꽃 피우기");
            }
            if (position == 2) {
                textView.setText("아이스크림 쌓기");
            }
            if (position == 3) {
                textView.setText("꿀 모으기");
            }
            if (position == 4) {
                textView.setText("길건너 친구들");
            }
            if (position == 5) {
                textView.setText("블럭 모아 부수기");
            }
            if (position == 6) {
                textView.setText("케이크 만들기");
            }
            Button button = (Button)v.findViewById(R.id.play);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                    if (position == 0) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://g.lisagame.com/game/246/"));
                        startActivity(intent);
                    }if (position == 1) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://g.lisagame.com/game/79/"));
                        startActivity(intent);
                    }if (position == 2) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://g.lisagame.com/game/313/"));
                        startActivity(intent);
                    }if (position == 3) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://g.lisagame.com/game/631/"));
                        startActivity(intent);
                    }if (position == 4) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://g.lisagame.com/game/413/"));
                        startActivity(intent);
                    }if (position == 5) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://g.lisagame.com/game/32/"));
                            startActivity(intent);
                    }if (position == 6) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://g.lisagame.com/game/511/"));
                        startActivity(intent);
                    }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

            return v;
        }
    }
}