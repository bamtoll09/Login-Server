package me.bamtoll.lee.loginserver;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import me.bamtoll.lee.loginserver.retrofit.LoginService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Retrofit retrofit;
    Gson gson;
    LoginService service;

    EditText editId, editPw;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editId = (EditText) findViewById(R.id.edit_id);
        editPw = (EditText) findViewById(R.id.edit_pw);
        btnSubmit = (Button) findViewById(R.id.btn_submit);

        gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://" + DATA.URL + "/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = retrofit.create(LoginService.class);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editId.getText().toString().trim();
                String pw = editId.getText().toString().trim();

                Intent intent = new Intent(getApplicationContext(), Main3Activity.class);
                startActivity(intent);

                if (id.equals("") || pw.equals("")) {
                    Toast.makeText(getApplicationContext(), "Check your ID or Password", Toast.LENGTH_SHORT).show();
                } else { // Form is Corrected
                    submit(id, pw);
                    Toast.makeText(getApplicationContext(), "It sent!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void submit(String id, String pw) {
        service.login(id, pw).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getApplicationContext(), "Loginned", Toast.LENGTH_SHORT).show();
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
