package com.example.dashboard.News;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.dashboard.Fun.CineActivity;
import com.example.dashboard.HomeActivity;
import com.example.dashboard.News.ModelCovid.GlobalResponse;
import com.example.dashboard.News.NetworkCovid.ApiClientPrivate;
import com.example.dashboard.News.NetworkCovid.ApiInterface;
import com.example.dashboard.R;
import com.example.dashboard.Services.EpictureMain;
import com.example.dashboard.Services.FacebookListActivity;
import com.example.dashboard.Services.ImgurModel.MainActivityEpicture;
import com.example.dashboard.Services.PaypalActivity;
import com.example.dashboard.SettingsActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CovidHomeActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;

    TextView cases, todayCases, deaths, todayDeaths, recoved, active, critical, affectedCountries;
    Button countrytracker;

    static LinearLayout CinemaL, WeatherL, CovidL,
            InfosL, FacebookL,
            PaypalL, ImgurL;

    static int  cinemaStatus, weatherStatus,
            newsStatus, covidStatus, facebookStatus, paypalStatus, ImgurStatus;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_home);


        drawerLayout = findViewById(R.id.drawer_layout);

        cases = findViewById(R.id.tvCase);
        todayCases = findViewById(R.id.tv_TodayCases);
        deaths = findViewById(R.id.tvDeaths);
        todayDeaths = findViewById(R.id.tvTodaysDeaths);
        recoved = findViewById(R.id.tvRecovered);
        active = findViewById(R.id.tvActive);
        critical = findViewById(R.id.tvCritical);
        affectedCountries = findViewById(R.id.tvAffectedCountries);
        countrytracker = findViewById(R.id.countrytracker);

        countrytracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CovidHomeActivity.this , AllCountriesActivity.class));
            }
        });

        apiCall();

        //setting setup

        CinemaL = findViewById(R.id.cineLayout);
        WeatherL = findViewById(R.id.weatherLayout);
        InfosL = findViewById(R.id.infosLayout);
        CovidL = findViewById(R.id.covidLayout);
        FacebookL = findViewById(R.id.facebookLayout);
        PaypalL = findViewById(R.id.paypalLayout);
        ImgurL = findViewById(R.id.imgurLayout);
        setVisibilityWidget();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void apiCall() {

        ApiInterface apiInterface = null ;
        apiInterface = ApiClientPrivate.getApiClient().create(ApiInterface.class);

        Call<GlobalResponse> call = apiInterface.globalResponse();

        call.enqueue(new Callback<GlobalResponse>() {
            @Override
            public void onResponse(Call<GlobalResponse> call, Response<GlobalResponse> response) {

                if (response.isSuccessful()){

                    String stCase = String.valueOf(response.body().getCases());
                    String sttodayCases = String.valueOf(response.body().getTodayCases());
                    String stDeaths = String.valueOf(response.body().getDeaths());
                    String stTodasDeath = String.valueOf(response.body().getTodayDeaths());
                    String stRecocered = String.valueOf(response.body().getRecovered());
                    String stActive = String.valueOf(response.body().getActive());
                    String stCritical = String.valueOf(response.body().getCritical());
                    String stCountires = String.valueOf(response.body().getAffectedCountries());

                    cases.setText(stCase);
                    todayCases.setText(sttodayCases);
                    deaths.setText(stDeaths);
                    todayDeaths.setText(stTodasDeath);
                    recoved.setText(stRecocered);
                    active.setText(stActive);
                    critical.setText(stCritical);
                    affectedCountries.setText(stCountires);

                }
            }

            @Override
            public void onFailure(Call<GlobalResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }


    //setting setup

    public void setVisibilityWidget()
    {
        if (SettingsActivity.getSwitchCineStatus() == 1) {
            CinemaL.setVisibility(View.VISIBLE);
            cinemaStatus = 1;
        } else {
            CinemaL.setVisibility(View.GONE);
            cinemaStatus = 0;
        }
        if (SettingsActivity.getSwitchWeatherStatus() == 1) {
            WeatherL.setVisibility(View.VISIBLE);
            weatherStatus = 1;
        } else {
            WeatherL.setVisibility(View.GONE);
            weatherStatus = 0;
        }
        if (SettingsActivity.getSwitchNewsStatus() == 1) {
            InfosL.setVisibility(View.VISIBLE);
            newsStatus = 1;
        } else {
            InfosL.setVisibility(View.GONE);
            newsStatus = 0;
        }
        if (SettingsActivity.getSwitchCovidStatus() == 1) {
            CovidL.setVisibility(View.VISIBLE);
            covidStatus = 1;
        } else {
            CovidL.setVisibility(View.GONE);
            covidStatus = 0;
        }
        if (SettingsActivity.getSwitchfacebookStatus() == 1) {
            FacebookL.setVisibility(View.VISIBLE);
            facebookStatus = 1;
        } else {
            FacebookL.setVisibility(View.GONE);
            facebookStatus = 0;
        }
        if (SettingsActivity.getSwitchPaypalStatus() == 1) {
            PaypalL.setVisibility(View.VISIBLE);
            paypalStatus = 1;
        } else {
            PaypalL.setVisibility(View.GONE);
            paypalStatus = 0;
        }
        if (SettingsActivity.getSwitchImgurStatus() == 1) {
            ImgurL.setVisibility(View.VISIBLE);
            ImgurStatus = 1;
        } else {
            ImgurL.setVisibility(View.GONE);
            ImgurStatus = 0;
        }
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

    public void ClickCovid(View view){
        recreate();
    }

    public void ClickInfos(View view){

        HomeActivity.redirectActivity(this, NewsActivity.class);
    }

    public void ClickWeather(View view){
        HomeActivity.redirectActivity(this, WeatherActivity.class);
    }

    public void ClickImgur(View view){
        if (MainActivityEpicture.getStatusofToken() == 0) {
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