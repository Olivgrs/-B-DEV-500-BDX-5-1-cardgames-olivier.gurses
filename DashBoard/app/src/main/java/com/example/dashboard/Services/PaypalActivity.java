package com.example.dashboard.Services;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dashboard.Fun.CineActivity;
import com.example.dashboard.HomeActivity;
import com.example.dashboard.News.CovidActivity;
import com.example.dashboard.Services.AdapterPaypal.CoffeAdapter;
import com.example.dashboard.Services.ConfigPaypal.Config;
import com.example.dashboard.Services.ImgurModel.MainActivityEpicture;
import com.example.dashboard.Services.PaypalModel.Coffe;
import com.example.dashboard.News.NewsActivity;
import com.example.dashboard.News.WeatherActivity;
import com.example.dashboard.R;
import com.example.dashboard.SettingsActivity;
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PaypalActivity extends AppCompatActivity {

    //Coffee
    ImageView ivLogo;
    Animation animLogoMove, animTransition;
    TextView txtCoffeHome;
    RelativeLayout relativeMain;

    HorizontalInfiniteCycleViewPager viewPager;
    List<Coffe> listCoffe = new ArrayList<>();

    static LinearLayout CinemaL, WeatherL, CovidL,
            InfosL, FacebookL,
            PaypalL, ImgurL;

    static int  cinemaStatus, weatherStatus,
            newsStatus, covidStatus, facebookStatus, paypalStatus, ImgurStatus;

    //paypal
   private static final int PAYPAL_REQUEST_CODE = 7171;
    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(Config.PAYPAL_CLIENT_ID);
    Button btnPayNow;
    EditText edtAmount;
    String  amount = "";

    //Menu
    DrawerLayout drawerLayout;

    @Override
    protected void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypal);

        //menu
        drawerLayout = findViewById(R.id.drawer_layout);

        //Paypal service
        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);

        btnPayNow = (Button) findViewById(R.id.btnPayNow);
        edtAmount = (EditText) findViewById(R.id.edtAmount);

        btnPayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processPayment();
            }
        });

        //Coffee
        ivLogo = findViewById(R.id.iv_logo);
        viewPager = findViewById(R.id.view_pager);
        relativeMain = findViewById(R.id.relative_back);
        txtCoffeHome = findViewById(R.id.tv_titlecoffee);

        animLogoMove = AnimationUtils.loadAnimation(this, R.anim.logo_move);
        animTransition = AnimationUtils.loadAnimation(this, R.anim.transition);

        initData();

        CoffeAdapter adapter = new CoffeAdapter(this, listCoffe);
        viewPager.setAdapter(adapter);

        ivLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivLogo.setVisibility(View.VISIBLE);
                ivLogo.setAnimation(animLogoMove);

                txtCoffeHome.setVisibility(View.INVISIBLE);
                btnPayNow.setVisibility(View.VISIBLE);


                viewPager.setVisibility(View.VISIBLE);
                viewPager.startAnimation(animTransition);
            }
        });

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
    //tape your code Here

    //coffee

    private void initData() {
        listCoffe.add(new Coffe("Tiramisu", "Cappucino",
                getString(R.string.dummy),
                R.drawable.cup_capucino, R.drawable.back_cappu, R.drawable.coffee,
                Color.parseColor("#5d6d1b")));

        listCoffe.add(new Coffe("Greentea", "Latte",
                getString(R.string.dummy),
                R.drawable.cup_greentea, R.drawable.back_green, R.drawable.tea,
                Color.parseColor("#5d6d1b")));

        listCoffe.add(new Coffe("Mochacino", "Choco",
                getString(R.string.dummy),
                R.drawable.cup_mocha, R.drawable.back_mocha, R.drawable.choco,
                Color.parseColor("#b38868")));
    }

    //Paypal
    private void processPayment() {
        amount = "5";
        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(String.valueOf(amount)), "USD",
                "Price for your coffe Home Coffee", PayPalPayment.PAYMENT_INTENT_SALE);
        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payPalPayment);
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PAYPAL_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirmation != null) {
                    try {
                        String paymentDetails = confirmation.toJSONObject().toString(4);startActivity(new Intent(this, PaymentDetails.class)
                                .putExtra("PaymentDetails", paymentDetails)
                                .putExtra("PaymentAmount", amount)
                        );
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
            }
        } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
        }
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
        recreate();
    }

    public void ClickSettings(View view){
        HomeActivity.redirectActivity(this, SettingsActivity.class);
    }

    @Override
    protected void onPause(){
        super.onPause();
        HomeActivity.closeDrawer(drawerLayout);
    }
}