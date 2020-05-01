package me.bamtoll.lee.loginserver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editId, editPw;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                    Toast.makeText(getApplicationContext(), "It sent!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
