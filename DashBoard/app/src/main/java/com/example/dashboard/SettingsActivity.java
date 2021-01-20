package com.example.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.example.dashboard.Fun.CineActivity;
import com.example.dashboard.News.CovidActivity;
import com.example.dashboard.News.NewsActivity;
import com.example.dashboard.Services.FacebookListActivity;
import com.example.dashboard.Services.ImgurModel.MainActivityEpicture;
import com.example.dashboard.Services.PaypalActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SettingsActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    static Switch cinemaSwitch, weatherSwitch,
    newsSwitch, covidSwitch, facebookSwitch, paypalSwitch, ImgurSwitch;
    static int cinemaStatus, weatherStatus,
            newsStatus, covidStatus, facebookStatus, paypalStatus, ImgurStatus;

    static LinearLayout CinemaL, WeatherL,
            InfosL, CovidL, FacebookL,
            PaypalL, ImgurL;

    ImageView Home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        cinemaSwitch = findViewById(R.id.CineSwitch);
        weatherSwitch = findViewById(R.id.WeatherSwitch);
        newsSwitch = findViewById(R.id.NewsSwitch);
        covidSwitch = findViewById(R.id.CovidSwitch);
        facebookSwitch = findViewById(R.id.FacebookSwitch);
        paypalSwitch = findViewById(R.id.PaypalSwitch);
        ImgurSwitch = findViewById(R.id.ImgurSwitch);
        drawerLayout = findViewById(R.id.drawer_layout);

        CinemaL = findViewById(R.id.cineLayout);
        WeatherL = findViewById(R.id.weatherLayout);
        InfosL = findViewById(R.id.infosLayout);
        CovidL = findViewById(R.id.covidLayout);
        FacebookL = findViewById(R.id.facebookLayout);
        PaypalL = findViewById(R.id.paypalLayout);
        ImgurL = findViewById(R.id.imgurLayout);

        Home = findViewById(R.id.Home);

        setVisibilityWidgetModel();
        getSwitchStatus();
        setVisibilityWidget();

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickHome(view);
            }
        });
    }
    //Type code here











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

    public void setVisibilityWidgetModel()
    {
        cinemaSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cinemaStatus = 1;
                } else {
                    // The toggle is disabled
                    cinemaStatus = 0;
                }
                Log.d("CinemaStatus : ", String.valueOf(cinemaStatus));
                CinemaChecker(String.valueOf(cinemaStatus));
            }
        });
        weatherSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    weatherStatus = 1;
                } else {
                    // The toggle is disabled
                    weatherStatus = 0;
                }
                Log.d("WeatherStatus : ", String.valueOf(weatherStatus));
                WeatherChecker(String.valueOf(weatherStatus));
            }
        });
        newsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    newsStatus = 1;
                } else {
                    // The toggle is disabled
                    newsStatus = 0;
                }
                Log.d("NewsStatus : ", String.valueOf(newsStatus));
                NewsChecker(String.valueOf(newsStatus));
            }
        });
        covidSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    covidStatus = 1;
                } else {
                    // The toggle is disabled
                    covidStatus = 0;
                }
                Log.d("CovidStatus : ", String.valueOf(covidStatus));
                CovidChecker(String.valueOf(covidStatus));
            }
        });
        facebookSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Log.d("isChecked : ", String.valueOf(isChecked));
                    facebookStatus = 1;
                } else {
                    // The toggle is disabled
                    facebookStatus = 0;
                }
                Log.d("FacebookStatus : ", String.valueOf(facebookStatus));
                FacebookChecker(String.valueOf(facebookStatus));
            }
        });
        paypalSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    paypalStatus = 1;
                } else {
                    // The toggle is disabled
                    paypalStatus = 0;
                }
                Log.d("PaypalStatus : ", String.valueOf(paypalStatus));
                PaypalChecker(String.valueOf(paypalStatus));
            }
        });
        ImgurSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ImgurStatus = 1;
                } else {
                    // The toggle is disabled
                    ImgurStatus = 0;
                }
                Log.d("ImgurStatus : ", String.valueOf(ImgurStatus));
                ImgurChecker(String.valueOf(ImgurStatus));
            }
        });
    }

    public void getSwitchStatus()
    {
        if(HomeActivity.getSwitchCinemaStatusHome() == 1) {
            cinemaSwitch.setChecked(true);
        } else {
            cinemaSwitch.setChecked(false);
        }
        if(HomeActivity.getSwitchWeatherStatusHome() == 1) {
            weatherSwitch.setChecked(true);
        } else {
            weatherSwitch.setChecked(false);
        }
        if(HomeActivity.getSwitchNewsStatusHome() == 1) {
            newsSwitch.setChecked(true);
        } else {
            newsSwitch.setChecked(false);
        }
        if(HomeActivity.getSwitchCovidStatusHome() == 1) {
            covidSwitch.setChecked(true);
        } else {
            covidSwitch.setChecked(false);
        }
        if(HomeActivity.getSwitchFacebookStatusHome() == 1) {
            facebookSwitch.setChecked(true);
        } else {
            facebookSwitch.setChecked(false);
        }
        if(HomeActivity.getSwitchPaypalStatusHome() == 1) {
            paypalSwitch.setChecked(true);
        } else {
            paypalSwitch.setChecked(false);
        }
        if(HomeActivity.getSwitchImgurStatusHome() == 1) {
            ImgurSwitch.setChecked(true);
        } else {
            ImgurSwitch.setChecked(false);
        }

    }

    public static int getSwitchCineStatus()
    {
        return cinemaStatus;
    }
    public static int getSwitchWeatherStatus()
    {
        return weatherStatus;
    }
    public static int getSwitchNewsStatus()
    {
        return newsStatus;
    }
    public static int getSwitchCovidStatus()
    {
        return covidStatus;
    }
    public static int getSwitchfacebookStatus()
    {
        return facebookStatus;
    }
    public static int getSwitchPaypalStatus()
    {
        return paypalStatus;
    }
    public static int getSwitchImgurStatus()
    {
        return ImgurStatus;
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
        recreate();
    }

    public void ClickImgur(View view){
        HomeActivity.redirectActivity(this, MainActivityEpicture.class);
    }

    public void ClickFacebook(View view){
        HomeActivity.redirectActivity(this, FacebookListActivity.class);
    }

    public void ClickPaypal(View view){
        HomeActivity.redirectActivity(this, PaypalActivity.class);
    }


    public void ClickSettings(View view){
        HomeActivity.redirectActivity(this, SettingsActivity.class);
    }

    @Override
    protected void onPause(){
        super.onPause();
        HomeActivity.closeDrawer(drawerLayout);
    }

    //Handler handler = new Handler(){
    //    @Override
    //    public void handleMessage(Message msg) {
    //        super.handleMessage(msg);
    //        Bundle data = msg.getData();
    //        String val = data.getString("value");
    //    }
    //};

    public void Facebook(String facebook, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();

        String json = "{\"Facebook\" : \""+ facebook +"\"}";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("http://10.0.2.2:8080/accounts/ManageWidgetFacebook")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void Cinema(String cinema, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();

        String json = "{\"Cinema\" : \""+ cinema +"\"}";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("http://10.0.2.2:8080/accounts/ManageWidgetCinema")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void Weather(String weather, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();

        String json = "{\"Weather\" : \""+ weather +"\"}";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("http://10.0.2.2:8080/accounts/ManageWidgetWeather")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void Newss(String news, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();

        String json = "{\"News\" : \""+ news +"\"}";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("http://10.0.2.2:8080/accounts/ManageWidgetNews")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void Covid(String covid, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();

        String json = "{\"Covid\" : \""+ covid +"\"}";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("http://10.0.2.2:8080/accounts/ManageWidgetCovid")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void Paypal(String paypal, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();

        String json = "{\"Paypal\" : \""+ paypal +"\"}";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("http://10.0.2.2:8080/accounts/ManageWidgetPaypal")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void Imgur(String imgur, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();

        String json = "{\"Imgur\" : \""+ imgur +"\"}";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("http://10.0.2.2:8080/accounts/ManageWidgetImgur")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void FacebookChecker(String facebook){
        Facebook(facebook, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("Error: ", " no responses");
                Log.d("Error message: ", e.getMessage());
                call.cancel();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final boolean ResponseCode = response.isSuccessful();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (ResponseCode == true){
                            Toast.makeText(SettingsActivity.this,"setting is successful", Toast.LENGTH_SHORT).show();
                            Log.d("Log: ", "setting is successful.");
                            try {
                                Log.d("Response body: ", response.body().string());
                            } catch (IOException e) {}
                        }else{
                            Toast.makeText(SettingsActivity.this,"setting failed", Toast.LENGTH_SHORT).show();
                            Log.d("App: ", "Login failed");
                            Log.d("Response Code: ", String.valueOf(response.code()));
                            Log.d("Response Code Message: ", response.message());
                            Log.d("Response Status: ", String.valueOf(response.isSuccessful()));
                            try {
                                Log.d("Response body: ", response.body().string());
                            } catch (IOException e) {}
                        }
                    }
                });
            }
        });
    }

    public void CinemaChecker(String cinema){
        Cinema(cinema, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("Error: ", " no responses");
                Log.d("Error message: ", e.getMessage());
                call.cancel();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final boolean ResponseCode = response.isSuccessful();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (ResponseCode == true){
                            Toast.makeText(SettingsActivity.this,"setting is successful", Toast.LENGTH_SHORT).show();
                            Log.d("Log: ", "setting is successful.");
                            try {
                                Log.d("Response body: ", response.body().string());
                            } catch (IOException e) {}
                        }else{
                            Toast.makeText(SettingsActivity.this,"setting failed", Toast.LENGTH_SHORT).show();
                            Log.d("App: ", "Login failed");
                            Log.d("Response Code: ", String.valueOf(response.code()));
                            Log.d("Response Code Message: ", response.message());
                            Log.d("Response Status: ", String.valueOf(response.isSuccessful()));
                            try {
                                Log.d("Response body: ", response.body().string());
                            } catch (IOException e) {}
                        }
                    }
                });
            }
        });
    }

    public void WeatherChecker(String weather){
        Weather(weather, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("Error: ", " no responses");
                Log.d("Error message: ", e.getMessage());
                call.cancel();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final boolean ResponseCode = response.isSuccessful();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (ResponseCode == true){
                            Toast.makeText(SettingsActivity.this,"setting is successful", Toast.LENGTH_SHORT).show();
                            Log.d("Log: ", "setting is successful.");
                            try {
                                Log.d("Response body: ", response.body().string());
                            } catch (IOException e) {}
                        }else{
                            Toast.makeText(SettingsActivity.this,"setting failed", Toast.LENGTH_SHORT).show();
                            Log.d("App: ", "Login failed");
                            Log.d("Response Code: ", String.valueOf(response.code()));
                            Log.d("Response Code Message: ", response.message());
                            Log.d("Response Status: ", String.valueOf(response.isSuccessful()));
                            try {
                                Log.d("Response body: ", response.body().string());
                            } catch (IOException e) {}
                        }
                    }
                });
            }
        });
    }

    public void NewsChecker(String news){
        Newss(news, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("Error: ", " no responses");
                Log.d("Error message: ", e.getMessage());
                call.cancel();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final boolean ResponseCode = response.isSuccessful();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (ResponseCode == true){
                            Toast.makeText(SettingsActivity.this,"setting is successful", Toast.LENGTH_SHORT).show();
                            Log.d("Log: ", "setting is successful.");
                            try {
                                Log.d("Response body: ", response.body().string());
                            } catch (IOException e) {}
                        }else{
                            Toast.makeText(SettingsActivity.this,"setting failed", Toast.LENGTH_SHORT).show();
                            Log.d("App: ", "Login failed");
                            Log.d("Response Code: ", String.valueOf(response.code()));
                            Log.d("Response Code Message: ", response.message());
                            Log.d("Response Status: ", String.valueOf(response.isSuccessful()));
                            try {
                                Log.d("Response body: ", response.body().string());
                            } catch (IOException e) {}
                        }
                    }
                });
            }
        });
    }

    public void CovidChecker(String covid){
        Covid(covid, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("Error: ", " no responses");
                Log.d("Error message: ", e.getMessage());
                call.cancel();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final boolean ResponseCode = response.isSuccessful();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (ResponseCode == true){
                            Toast.makeText(SettingsActivity.this,"setting is successful", Toast.LENGTH_SHORT).show();
                            Log.d("Log: ", "setting is successful.");
                            try {
                                Log.d("Response body: ", response.body().string());
                            } catch (IOException e) {}
                        }else{
                            Toast.makeText(SettingsActivity.this,"setting failed", Toast.LENGTH_SHORT).show();
                            Log.d("App: ", "Login failed");
                            Log.d("Response Code: ", String.valueOf(response.code()));
                            Log.d("Response Code Message: ", response.message());
                            Log.d("Response Status: ", String.valueOf(response.isSuccessful()));
                            try {
                                Log.d("Response body: ", response.body().string());
                            } catch (IOException e) {}
                        }
                    }
                });
            }
        });
    }

    public void PaypalChecker(String paypal){
        Paypal(paypal, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("Error: ", " no responses");
                Log.d("Error message: ", e.getMessage());
                call.cancel();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final boolean ResponseCode = response.isSuccessful();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (ResponseCode == true){
                            Toast.makeText(SettingsActivity.this,"setting is successful", Toast.LENGTH_SHORT).show();
                            Log.d("Log: ", "setting is successful.");
                            try {
                                Log.d("Response body: ", response.body().string());
                            } catch (IOException e) {}
                        }else{
                            Toast.makeText(SettingsActivity.this,"setting failed", Toast.LENGTH_SHORT).show();
                            Log.d("App: ", "Login failed");
                            Log.d("Response Code: ", String.valueOf(response.code()));
                            Log.d("Response Code Message: ", response.message());
                            Log.d("Response Status: ", String.valueOf(response.isSuccessful()));
                            try {
                                Log.d("Response body: ", response.body().string());
                            } catch (IOException e) {}
                        }
                    }
                });
            }
        });
    }

    public void ImgurChecker(String imgur){
        Imgur(imgur, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("Error: ", " no responses");
                Log.d("Error message: ", e.getMessage());
                call.cancel();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final boolean ResponseCode = response.isSuccessful();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (ResponseCode == true){
                            Toast.makeText(SettingsActivity.this,"setting is successful", Toast.LENGTH_SHORT).show();
                            Log.d("Log: ", "setting is successful.");
                            try {
                                Log.d("Response body: ", response.body().string());
                            } catch (IOException e) {}
                        }else{
                            Toast.makeText(SettingsActivity.this,"setting failed", Toast.LENGTH_SHORT).show();
                            Log.d("App: ", "Login failed");
                            Log.d("Response Code: ", String.valueOf(response.code()));
                            Log.d("Response Code Message: ", response.message());
                            Log.d("Response Status: ", String.valueOf(response.isSuccessful()));
                            try {
                                Log.d("Response body: ", response.body().string());
                            } catch (IOException e) {}
                        }
                    }
                });
            }
        });
    }
}