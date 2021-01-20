package com.example.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

    private Button btLogin;
    private EditText firstname;
    private EditText lastname;
    private EditText email;
    private EditText loginPwd;
    private EditText loginPwdconfirme;

    Button btRegistRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btLogin = findViewById(R.id.btRegisLogin);
        btRegistRegister = findViewById(R.id.btRegistRegister);
        firstname = findViewById(R.id.etFirstName);
        lastname = findViewById(R.id.etLastName);
        email = findViewById(R.id.etEmail);
        loginPwd = findViewById(R.id.etPassword);
        loginPwdconfirme = findViewById(R.id.etRePassword);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        btRegistRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String registerUrl="http://10.0.2.2:8080/accounts/register";
                String registerfirstname = firstname.getText().toString();
                String registerLastname= lastname.getText().toString();
                String registerEmail = email.getText().toString();
                String registerPwd = loginPwd.getText().toString();
                String registerPwdConfirme = loginPwdconfirme.getText().toString();

                registerWithOkHttp(registerUrl,registerfirstname,registerLastname, registerEmail, registerPwd, registerPwdConfirme);
                Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    public void registerWithOkHttp(String url, String registerfirstname, String registerLastname, String registerEmail, String registerPwd, String registerPwdConfirme, Callback callback){
        OkHttpClient client = new OkHttpClient();
        String json = "{\"firstName\" : \""+ registerfirstname +"\", " +
                "\"lastName\" : \""+ registerLastname + "\"," +
                "\"email\" : \""+ registerEmail + "\"," +
                "\"password\" : \""+ registerPwd + "\"," +
                "\"confirmPassword\" : \""+ registerPwdConfirme + "\"}";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void registerWithOkHttp(String url,String registerfirstname,String registerLastname, String registerEmail, String registerPwd, String registerPwdConfirme){
        registerWithOkHttp(url, registerfirstname, registerLastname, registerEmail, registerPwd, registerPwdConfirme, new Callback() {

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
                            Toast.makeText(RegisterActivity.this,"Registration is successful", Toast.LENGTH_SHORT).show();
                            Log.d("Log: ", "Registration is successful.");
                        }else{
                            Toast.makeText(RegisterActivity.this,"Registration failed", Toast.LENGTH_SHORT).show();
                            Log.d("App: ", "Registration failed");
                            Log.d("Response Code: ", String.valueOf(response.code()));
                            Log.d("Response Code Message: ", response.message());
                            Log.d("Response Status: ", String.valueOf(response.isSuccessful()));
                            String str = null;
                            try {
                                str = response.body().string();
                                Log.d("Response Body", str);
                            } catch (IOException e) {}
                        }
                    }
                });
            }
        });
    }
}

