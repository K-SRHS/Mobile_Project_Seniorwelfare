package com.example.ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        ImageButton game1 = (ImageButton) findViewById(R.id.game1);
        game1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View game1) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://g.regogame.com/game/678/"));
                startActivity(intent);
            }
        });
        ImageButton game2 = (ImageButton) findViewById(R.id.game2);
        game2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View game2) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://g.regogame.com/game/3/"));
                startActivity(intent);
            }
        });
        ImageButton game3 = (ImageButton) findViewById(R.id.game3);
        game3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View game3) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://g.regogame.com/game/689/"));
                startActivity(intent);
            }
        });
    }
}