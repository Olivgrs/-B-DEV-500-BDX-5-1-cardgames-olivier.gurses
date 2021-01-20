package com.example.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private ImageButton btRegister;
    private ImageView circle1;
    private EditText email;
    private EditText password;
    public static boolean is_Faild = true;

    Button btLogin;
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btRegister = findViewById(R.id.btRegister);
        btLogin = findViewById(R.id.btLogin);
        email = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);

        tvLogin     = findViewById(R.id.tvLogin);
        circle1     = findViewById(R.id.circle1);

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginUrl = "http://10.0.2.2:8080/accounts/authenticate";
                String loginEmail = email.getText().toString();
                String loginPassword = password.getText().toString();
                loginWithOkHttp(loginUrl, loginEmail, loginPassword);
                Intent i = new Intent(MainActivity.this, HomeActivity.class);
                if (is_Faild == true)
                    LoginFaild(i);
                else if (is_Faild != true){
                    Log.d("success class name: ", i.getComponent().getClassName());
                    start();
                }
            }
        });
    }

    public void LoginFaild(Intent intent) {
        ComponentName componentName = new ComponentName(getApplicationContext(), MainActivity.class);
        intent.putExtra("className", intent.getComponent().getClassName());
        Log.d("faild class name: ", intent.getComponent().getClassName());
        intent.setComponent(componentName);
        super.startActivity(intent);
    }

    private void start() {
        if (getIntent().getExtras() != null && getIntent().getExtras().getString("className") != null) {
            String className = getIntent().getExtras().getString("className");
            getIntent().removeExtra("className");
            if (className != null && !className.equals(getApplicationContext().getClass().getName())) {
                try {
                    ComponentName componentName = new ComponentName(getApplicationContext(), Class.forName(className));
                    startActivity(getIntent().setComponent(componentName));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        finish();
    }

    public void loginWithOkHttp(String url, String loginEmail, String loginPassword, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        String json = "{\"email\" : \""+ loginEmail +"\", " +
                "\"password\" : \""+ loginPassword + "\"}";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }


    public void loginWithOkHttp(String url, String loginEmail, String loginPassword){
        loginWithOkHttp(url, loginEmail, loginPassword, new Callback() {

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
                            Toast.makeText(MainActivity.this,"Login is successful", Toast.LENGTH_SHORT).show();
                            Log.d("Log: ", "Login is successful.");
                        }else{
                            is_Faild = false;
                            Toast.makeText(MainActivity.this,"Login failed", Toast.LENGTH_SHORT).show();
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
