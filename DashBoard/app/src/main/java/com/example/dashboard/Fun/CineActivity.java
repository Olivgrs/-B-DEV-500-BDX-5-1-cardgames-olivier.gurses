package com.example.dashboard.Fun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dashboard.Fun.CineAdapter.MovieAdapter;
import com.example.dashboard.Fun.CineModel.MovieModelClass;
import com.example.dashboard.HomeActivity;
import com.example.dashboard.News.CovidActivity;
import com.example.dashboard.Services.EpictureMain;
import com.example.dashboard.Services.FacebookListActivity;
import com.example.dashboard.Services.ImgurModel.MainActivityEpicture;
import com.example.dashboard.Services.PaypalActivity;
import com.example.dashboard.News.NewsActivity;
import com.example.dashboard.News.WeatherActivity;
import com.example.dashboard.R;
import com.example.dashboard.SettingsActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CineActivity extends AppCompatActivity {


    static LinearLayout CinemaL, WeatherL, CovidL,
            InfosL, FacebookL,
            PaypalL, ImgurL;

    static int  cinemaStatus, weatherStatus,
            newsStatus, covidStatus, facebookStatus, paypalStatus, ImgurStatus;

    TextView topRated, Popular, NowPlaying, Upcoming,
    TvAiring, TvOn, PopularTv, RatedTv;

    DrawerLayout drawerLayout;
    private static String JSON_URL = "https://api.themoviedb.org/3/movie/popular?api_key=c8613554cdc2bab5a805f28ece5b8a41";
    private static String title = "title";

    List<MovieModelClass> movieList;
    RecyclerView recyclerView;
    ImageView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cine);

        drawerLayout = findViewById(R.id.drawer_layout);

        movieList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        header = findViewById(R.id.header_cine);

        Glide.with(this)
                .load(R.drawable.ic_movie)
                .into(header);

        GetData getData = new GetData();
        getData.execute();

        //setting setup

        CinemaL = findViewById(R.id.cineLayout);
        WeatherL = findViewById(R.id.weatherLayout);
        InfosL = findViewById(R.id.infosLayout);
        CovidL = findViewById(R.id.covidLayout);
        FacebookL = findViewById(R.id.facebookLayout);
        PaypalL = findViewById(R.id.paypalLayout);
        ImgurL = findViewById(R.id.imgurLayout);

        topRated = findViewById(R.id.topRated);
        Popular = findViewById(R.id.Popular);
        NowPlaying = findViewById(R.id.nowPlaying);
        Upcoming = findViewById(R.id.Upcoming);

        TvAiring = findViewById(R.id.TvAiring);
        TvOn = findViewById(R.id.TvOn);
        PopularTv = findViewById(R.id.TvPopular);
        RatedTv = findViewById(R.id.TvRated);

        topRated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSON_URL = "https://api.themoviedb.org/3/movie/top_rated?api_key=c8613554cdc2bab5a805f28ece5b8a41";
                title = "title";
                startActivity(new Intent(CineActivity.this, CineActivity.class));

            }
        });
        Popular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSON_URL = "https://api.themoviedb.org/3/movie/popular?api_key=c8613554cdc2bab5a805f28ece5b8a41";
                title = "title";
                startActivity(new Intent(CineActivity.this, CineActivity.class));

            }
        });
        NowPlaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSON_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=c8613554cdc2bab5a805f28ece5b8a41";
                title = "title";
                startActivity(new Intent(CineActivity.this, CineActivity.class));

            }
        });
        Upcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = "title";
                JSON_URL = "https://api.themoviedb.org/3/movie/upcoming?api_key=c8613554cdc2bab5a805f28ece5b8a41";
                startActivity(new Intent(CineActivity.this, CineActivity.class));
            }
        });

        TvAiring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSON_URL = "https://api.themoviedb.org/3/tv/airing_today?api_key=c8613554cdc2bab5a805f28ece5b8a41";
                title = "name";
                startActivity(new Intent(CineActivity.this, CineActivity.class));
            }
        });

        TvOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSON_URL = "https://api.themoviedb.org/3/tv/on_the_air?api_key=c8613554cdc2bab5a805f28ece5b8a41";
                title = "name";
                startActivity(new Intent(CineActivity.this, CineActivity.class));
            }
        });

        PopularTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSON_URL = "https://api.themoviedb.org/3/tv/popular?api_key=c8613554cdc2bab5a805f28ece5b8a41";
                title = "name";
                startActivity(new Intent(CineActivity.this, CineActivity.class));
            }
        });

       RatedTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSON_URL = "https://api.themoviedb.org/3/tv/top_rated?api_key=c8613554cdc2bab5a805f28ece5b8a41";
                title = "name";
                startActivity(new Intent(CineActivity.this, CineActivity.class));
            }
        });

        setVisibilityWidget();
    }
    //tape your code Here



    public class GetData extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {

            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL(JSON_URL);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream is = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);

                    int data = isr.read();
                    while (data != -1) {
                        current += (char) data;
                        data = isr.read();
                    }
                    return current;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {

            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for (int i = 0 ; i < jsonArray.length(); i++) {

                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    MovieModelClass model = new MovieModelClass();
                    model.setId(jsonObject1.getString("vote_average"));
                    model.setName(jsonObject1.getString(title));
                    model.setImg(jsonObject1.getString("poster_path"));
                    model.setDescription(jsonObject1.getString("overview"));

                    movieList.add(model);
                }
            } catch (JSONException e){
                e.printStackTrace();
            }

            PutDataIntoRecyclerView(movieList);
        }
    }

    private void PutDataIntoRecyclerView(List<MovieModelClass> movieList) {

        MovieAdapter adapter = new MovieAdapter(this, movieList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        recyclerView.setAdapter(adapter);
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
        recreate();

    }

    public void ClickCovid(View view){
        HomeActivity.redirectActivity(this, CovidActivity.class);
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