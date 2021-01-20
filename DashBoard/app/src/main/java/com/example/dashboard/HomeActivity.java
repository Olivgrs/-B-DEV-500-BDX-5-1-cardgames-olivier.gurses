package com.example.dashboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.dashboard.Fun.CineActivity;
import com.example.dashboard.News.CovidActivity;
import com.example.dashboard.News.SplashScreenNews;
import com.example.dashboard.News.WeatherActivity;
import com.example.dashboard.Services.EpictureMain;
import com.example.dashboard.Services.FacebookListActivity;
import com.example.dashboard.Services.ImgurModel.MainActivityEpicture;
import com.example.dashboard.Services.PaypalActivity;

public class HomeActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;

    static CardView Cinema, Weather,
                Infos, Covid, Facebook,
                Paypal, Imgur;

    static LinearLayout CinemaL, WeatherL,
            InfosL, CovidL, FacebookL,
            PaypalL, ImgurL;

    ImageView img;

    static int cinemaStatus, weatherStatus,
            newsStatus, covidStatus, facebookStatus, paypalStatus, ImgurStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawer_layout);

        Cinema = findViewById(R.id.theaterCase);
        Weather = findViewById(R.id.weatherCase);
        Infos = findViewById(R.id.newsCase);
        Covid = findViewById(R.id.covidCase);
        Facebook = findViewById(R.id.facebookCase);
        Paypal = findViewById(R.id.paypalCase);
        Imgur = findViewById(R.id.imgurCase);
        img = findViewById(R.id.settingWidget);

        CinemaL = findViewById(R.id.cineLayout);
        WeatherL = findViewById(R.id.weatherLayout);
        InfosL = findViewById(R.id.infosLayout);
        CovidL = findViewById(R.id.covidLayout);
        FacebookL = findViewById(R.id.facebookLayout);
        PaypalL = findViewById(R.id.paypalLayout);
        ImgurL = findViewById(R.id.imgurLayout);

        Cinema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickTheater(view);
            }
        });
        Weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickWeather(view);
            }
        });
        Infos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickInfos(view);
            }
        });
        Covid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickCovid(view);
            }
        });
        Facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickFacebook(view);
            }
        });
        Paypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickPaypal(view);
            }
        });
        Imgur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickImgur(view);
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickSettings(view);
            }
        });

       setVisibilityWidget();
    }


    //setting setup

    public void setVisibilityWidget()
    {
        if (SettingsActivity.getSwitchCineStatus() == 1) {
            Cinema.setVisibility(View.VISIBLE);
            CinemaL.setVisibility(View.VISIBLE);
            cinemaStatus = 1;
        } else {
            Cinema.setVisibility(View.GONE);
            CinemaL.setVisibility(View.GONE);
            cinemaStatus = 0;
        }
        if (SettingsActivity.getSwitchWeatherStatus() == 1) {
            Weather.setVisibility(View.VISIBLE);
            WeatherL.setVisibility(View.VISIBLE);
            weatherStatus = 1;
        } else {
            Weather.setVisibility(View.GONE);
            WeatherL.setVisibility(View.GONE);
            weatherStatus = 0;
        }
        if (SettingsActivity.getSwitchNewsStatus() == 1) {
            Infos.setVisibility(View.VISIBLE);
            InfosL.setVisibility(View.VISIBLE);
            newsStatus = 1;
        } else {
            Infos.setVisibility(View.GONE);
            InfosL.setVisibility(View.GONE);
            newsStatus = 0;
        }
        if (SettingsActivity.getSwitchCovidStatus() == 1) {
            Covid.setVisibility(View.VISIBLE);
            CovidL.setVisibility(View.VISIBLE);
            covidStatus = 1;
        } else {
            Covid.setVisibility(View.GONE);
            CovidL.setVisibility(View.GONE);
            covidStatus = 0;
        }
        if (SettingsActivity.getSwitchfacebookStatus() == 1) {
            Facebook.setVisibility(View.VISIBLE);
            FacebookL.setVisibility(View.VISIBLE);
            facebookStatus = 1;
        } else {
            Facebook.setVisibility(View.GONE);
            FacebookL.setVisibility(View.GONE);
            facebookStatus = 0;
        }
        if (SettingsActivity.getSwitchPaypalStatus() == 1) {
            Paypal.setVisibility(View.VISIBLE);
            PaypalL.setVisibility(View.VISIBLE);
            paypalStatus = 1;
        } else {
            Paypal.setVisibility(View.GONE);
            PaypalL.setVisibility(View.GONE);
            paypalStatus = 0;
        }
        if (SettingsActivity.getSwitchImgurStatus() == 1) {
            Imgur.setVisibility(View.VISIBLE);
            ImgurL.setVisibility(View.VISIBLE);
            ImgurStatus = 1;
        } else {
            Imgur.setVisibility(View.GONE);
            ImgurL.setVisibility(View.GONE);
            ImgurStatus = 0;
        }
    }


    public static int getSwitchCinemaStatusHome()
    {
        return cinemaStatus;
    }
    public static int getSwitchWeatherStatusHome()
    {
        return weatherStatus;
    }
    public static int getSwitchNewsStatusHome()
    {
        return newsStatus;
    }
    public static int getSwitchCovidStatusHome()
    {
        return covidStatus;
    }
    public static int getSwitchFacebookStatusHome()
    {
        return facebookStatus;
    }
    public static int getSwitchPaypalStatusHome()
    {
        return paypalStatus;
    }
    public static int getSwitchImgurStatusHome()
    {
        return ImgurStatus;
    }


    //The Menu code
    public void ClickMenu(View view) {
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer((GravityCompat.START));
    }

    public void ClickLogo(View view){
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickHome(View view) {
        recreate();
    }

    public void ClickTheater(View view) {
        redirectActivity(this, CineActivity.class);
    }

    public void ClickInfos(View view) {
        redirectActivity(this, SplashScreenNews.class);
    }

    public void ClickWeather(View view) {
        redirectActivity(this, WeatherActivity.class);
    }

    public void ClickImgur(View view) {
        if (MainActivityEpicture.getStatusofToken() == 0) {
            redirectActivity(this, MainActivityEpicture.class);
        } else {
            redirectActivity(this, EpictureMain.class);
        }
    }

    public void ClickFacebook(View view) {
        redirectActivity(this, FacebookListActivity.class);
    }

    public void ClickPaypal(View view) {
        redirectActivity(this, PaypalActivity.class);
    }

    public void ClickSettings(View view) {
        redirectActivity(this, SettingsActivity.class);
    }

    public void ClickCovid(View view) {
        redirectActivity(this, CovidActivity.class);
    }


    public static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity, aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);

    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
}