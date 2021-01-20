package com.example.dashboard.Services;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dashboard.Fun.CineActivity;
import com.example.dashboard.HomeActivity;
import com.example.dashboard.News.CovidActivity;
import com.example.dashboard.News.NewsActivity;
import com.example.dashboard.News.WeatherActivity;
import com.example.dashboard.R;
import com.example.dashboard.Services.ImgurModel.MainActivityEpicture;
import com.example.dashboard.SettingsActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class EpictureMain extends AppCompatActivity {


    DrawerLayout drawerLayout;

    String my_accessToken = null;
    String my_refreshToken = null;
    String my_username = null;

    static LinearLayout CinemaL, WeatherL, CovidL,
            InfosL, FacebookL,
            PaypalL, ImgurL;

    static int  cinemaStatus, weatherStatus,
            newsStatus, covidStatus, facebookStatus, paypalStatus, ImgurStatus;

    ImageView fav;

    private static final String TAG = MainActivityEpicture.class.getSimpleName();
    private OkHttpClient httpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_epicture_main);


        drawerLayout = findViewById(R.id.drawer_layout);
        fav = findViewById(R.id.fav);

        Intent my_intent = getIntent();
        if (my_intent != null) {
            my_accessToken = my_intent.getStringExtra("EXTRA_ACCESS_TOKEN");
            my_refreshToken = my_intent.getStringExtra("EXTRA_REFRESH_TOKEN");
            my_username = my_intent.getStringExtra("EXTRA_USERNAME");
        }

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EpictureMain.this, FavouriteImgur.class));
            }
        });

       uploadImage();

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
    public void uploadImage() {
        httpClient = new OkHttpClient.Builder().build();

        Request request = new Request.Builder()
                .url("https://api.imgur.com/3/gallery/user/rising/0.json")
                .header("Authorization","Client-ID 2479bf1d5fdd677")
                .header("User-Agent","Epicture")
                .build();

        httpClient.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e)
            {
                Log.e(TAG, "An error has occurred " + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                try
                {
                    JSONObject data = new JSONObject(response.body().string());
                    JSONArray items = data.getJSONArray("data");
                    final List<Photo> photos = new ArrayList<>();

                    for (int i = 0; i < items.length(); i++)
                    {
                        JSONObject item = items.getJSONObject(i);
                        EpictureMain.Photo photo = new EpictureMain.Photo();
                        if(item.getBoolean("is_album")) {
                            photo.id = item.getString("cover");
                        } else {
                            photo.id = item.getString("id");
                        }
                        photo.title = item.getString("title");

                        photos.add(photo);

                        runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                render(photos);
                            }
                        });
                    }

                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    private static class PhotoVH extends RecyclerView.ViewHolder {
        ImageView photo;
        TextView title;

        public PhotoVH(View itemView) {
            super(itemView);
        }
    }

    private void render(final List<Photo> photos)
    {
        RecyclerView recyclerView = findViewById(R.id.rv_of_photos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.Adapter<EpictureMain.PhotoVH> adapter = new RecyclerView.Adapter<EpictureMain.PhotoVH>()
        {
            @NonNull
            @Override
            public EpictureMain.PhotoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {
                EpictureMain.PhotoVH vh = new EpictureMain.PhotoVH(getLayoutInflater().inflate(R.layout.list_image_imgur, null));
                vh.photo = vh.itemView.findViewById(R.id.photo);
                vh.title = vh.itemView.findViewById(R.id.title);
                return vh;
            }

            @Override
            public void onBindViewHolder(@NonNull EpictureMain.PhotoVH holder, int position)
            {
                Picasso.get()
                        .load("https://i.imgur.com/" + photos.get(position).id + ".jpg")
                        .into(holder.photo);
                holder.title.setText(photos.get(position).title);
            }

            @Override
            public int getItemCount()
            {
                return photos.size();
            }
        };

        recyclerView.setAdapter(adapter);
    }

    private static class Photo {
        String id;
        String title;
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
        HomeActivity.redirectActivity(this, CovidActivity.class);
    }

    public void ClickInfos(View view){
        HomeActivity.redirectActivity(this, NewsActivity.class);
    }

    public void ClickWeather(View view){
        HomeActivity.redirectActivity(this, WeatherActivity.class);
    }

    public void ClickImgur(View view){
        recreate();
    }

    public void ClickFacebook(View view){
        HomeActivity.redirectActivity(this, FacebookListActivity.class);
    }
    public void ClickPaypal(View view){
        HomeActivity.redirectActivity(this, PaypalActivity.class);
    }
    @Override
    protected void onPause(){
        super.onPause();
        HomeActivity.closeDrawer(drawerLayout);
    }





}
