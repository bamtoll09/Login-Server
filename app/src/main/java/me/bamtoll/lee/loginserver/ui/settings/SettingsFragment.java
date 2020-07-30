package me.bamtoll.lee.loginserver.ui.settings;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import me.bamtoll.lee.loginserver.R;
import me.bamtoll.lee.loginserver.retrofit.Settings;
import me.bamtoll.lee.loginserver.retrofit.SettingsService;
import me.bamtoll.lee.loginserver.retrofit.Transceiver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsFragment extends Fragment {

     private SettingsService service;

    private static boolean LOADED = false;
     private TextView textView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        service = Transceiver.getInstance().retrofit.create(SettingsService.class);

        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        textView = root.findViewById(R.id.text_setting_email_auth);

        if (!LOADED) {
            LOADED = true;
            getSettings();
        }

        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        LOADED = false;
    }

    private void getSettings() {
        service.getSettings().enqueue(new Callback<Settings>() {
            @Override
            public void onResponse(Call<Settings> call, Response<Settings> response) {
                if (response.code() == 200) {
                    final Settings settings = response.body();
                    if (settings.isAuthorized())
                        textView.setText(R.string.settings_authed);
                } else {
                    Toast.makeText(getContext().getApplicationContext(), "Something wrong.\nPlease check it.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Settings> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getContext().getApplicationContext(), "Fail to load settings.\nPlease Logging in.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}