package me.bamtoll.lee.loginserver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import me.bamtoll.lee.loginserver.retrofit.interceptor.AddCookiesInterceptor;
import me.bamtoll.lee.loginserver.retrofit.interceptor.CookiePreference;
import me.bamtoll.lee.loginserver.retrofit.LoginService;
import me.bamtoll.lee.loginserver.retrofit.interceptor.ReceiveCookiesInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    OkHttpClient client;
    Retrofit retrofit;
    Gson gson;
    LoginService service;

    ProgressDialog loadingDialog;

    EditText editId, editPw;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        CookiePreference.create(getApplicationContext());

        client = new OkHttpClient();
        client.interceptors().add(new AddCookiesInterceptor());
        client.interceptors().add(new ReceiveCookiesInterceptor());
        gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://" + DATA.URL + "/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        service = retrofit.create(LoginService.class);

        editId = (EditText) findViewById(R.id.edit_id);
        editPw = (EditText) findViewById(R.id.edit_pw);
        btnSubmit = (Button) findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editId.getText().toString().trim();
                String pw = editId.getText().toString().trim();

                if (id.equals("") || pw.equals("")) {
                    Toast.makeText(getApplicationContext(), "Check your ID or Password", Toast.LENGTH_SHORT).show();
                } else { // Form is Corrected
                    showLoadingDialog();

                    submit(id, pw);
                    Toast.makeText(getApplicationContext(), "It sent!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loadingDialog = new ProgressDialog(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (loadingDialog.isShowing())
                    loadingDialog.dismiss();
            }
        }, 2000);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (loadingDialog.isShowing())
            loadingDialog.dismiss();
    }

    void showLoadingDialog() {
        loadingDialog.setMessage("Please wait...");
        loadingDialog.create();
        loadingDialog.show();
    }

    void submit(String id, String pw) {
        service.login(id, pw).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getApplicationContext(), "Loginned", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
                Log.e("sadgfarg", t.getMessage());
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
