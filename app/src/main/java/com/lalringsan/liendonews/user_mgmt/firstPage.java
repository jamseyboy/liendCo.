package com.lalringsan.liendonews.user_mgmt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.lalringsan.liendonews.R;
import com.lalringsan.liendonews.onlineStore;

public class firstPage extends AppCompatActivity {
    Button myliveShopBtn,myMusicBtn,myOnlineStoreBtn,myStoryTellerBtn,myOthersBtn,myVideosBtn,myLiteratureBtn,myNewsBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        myLiteratureBtn=findViewById(R.id.literatueBtn);
        myMusicBtn=findViewById(R.id.musicBtn);
        myOnlineStoreBtn=findViewById(R.id.onlineStoreBtn);
        myliveShopBtn=findViewById(R.id.liveShopBtn);
        myStoryTellerBtn=findViewById(R.id.storyTellerBtn);
        myOthersBtn=findViewById(R.id.otherBtn);
        myVideosBtn=findViewById(R.id.videosBtn);
        myNewsBtn=findViewById(R.id.newsBtn);

      final Intent onlineStoreintent = new Intent( this, onlineStore.class);

        myOnlineStoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(onlineStoreintent);
            }
        });
    }

}