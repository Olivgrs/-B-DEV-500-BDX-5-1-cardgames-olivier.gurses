package com.example.dashboard.Services.ImgurModel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.dashboard.Fun.CineActivity;
import com.example.dashboard.HomeActivity;
import com.example.dashboard.News.CovidActivity;
import com.example.dashboard.News.NewsActivity;
import com.example.dashboard.News.WeatherActivity;
import com.example.dashboard.R;
import com.example.dashboard.Services.EpictureMain;
import com.example.dashboard.Services.FacebookListActivity;
import com.example.dashboard.Services.PaypalActivity;
import com.example.dashboard.SettingsActivity;

public class MainActivityEpicture extends AppCompatActivity {
    private Button my_login;
    private ImageView backGround;

    static int statusofToken;


    DrawerLayout drawerLayout;

    String my_username = null;
    String my_accessToken = null;
    String my_refreshToken = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_epicture);


        drawerLayout = findViewById(R.id.drawer_layout);

        my_login = findViewById(R.id.login);
        backGround = findViewById(R.id.backImgur);

        my_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                my_login.setVisibility(View.INVISIBLE);
                backGround.setVisibility(View.INVISIBLE);
                WebView my_authImgur = findViewById(R.id.auth);
                my_authImgur.loadUrl("https://api.imgur.com/oauth2/authorize?client_id=2479bf1d5fdd677&response_type=token&state=Login");
                my_authImgur.getSettings().setJavaScriptEnabled(true);
                my_authImgur.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        if (url.contains("access_token")) {
                            splitUrl(url, view);
                            Intent intent = new Intent(getApplicationContext(), EpictureMain.class);
                            Log.e("AccesToken", my_accessToken);
                            Log.e("RefreshToken", my_refreshToken);
                            Log.e("Username", my_username);
                            intent.putExtra("EXTRA_ACCESS_TOKEN", my_accessToken);
                            intent.putExtra("EXTRA_REFRESH_TOKEN", my_refreshToken);
                            intent.putExtra("EXTRA_USERNAME", my_username);
                            startActivity(intent);
                            statusofToken = 1;
                            finish();
                        } else {
                            statusofToken = 0;
                        }
                        return false;
                    }
                });
            }
        });
    }
    private void splitUrl(String url, WebView view) {
        String[] outerSplit = url.split("\\#")[1].split("\\&");

        int index = 0;

        for (String s : outerSplit) {
            String[] innerSplit = s.split("\\=");

            switch (index) {
                case 0:
                    my_accessToken = innerSplit[1];
                    break;

                case 3:
                    my_refreshToken = innerSplit[1];
                    break;

                case 4:
                    my_username = innerSplit[1];
                    break;
                default:
            }
            index++;
        }
    }

    public static int getStatusofToken()
    {
        return statusofToken;
    }

    //Menu Code
    public void ClickMenu(View view) {
        HomeActivity.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view) {
        HomeActivity.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view) {
        HomeActivity.redirectActivity(this, HomeActivity.class);
    }

    public void ClickTheater(View view){
        HomeActivity.redirectActivity(this, CineActivity.class);
    }

    public void ClcikCovid(View view){
        HomeActivity.redirectActivity(this, CovidActivity.class);
    }

    public void ClickInfos(View view){
        HomeActivity.redirectActivity(this, NewsActivity.class);
    }

    public void ClickWeather(View view){
        HomeActivity.redirectActivity(this, WeatherActivity.class);
    }

    public void ClickImgur(View view){
        if (statusofToken == 0) {
            HomeActivity.redirectActivity(this, MainActivityEpicture.class);
        } else {
            HomeActivity.redirectActivity(this, EpictureMain.class);
        }
    }

    public void ClickFacebook(View view){
        HomeActivity.redirectActivity(this, FacebookListActivity.class);
    }

    public void ClickPaypal(View view){
        HomeActivity.redirectActivity(this, PaypalActivity.class);
    }

    public void ClickSettings(View view) {
        HomeActivity.redirectActivity(this, SettingsActivity.class);
    }

    @Override
    protected void onPause(){
        super.onPause();
        HomeActivity.closeDrawer(drawerLayout);
    }

}