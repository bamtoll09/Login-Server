package me.bamtoll.lee.loginserver.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import me.bamtoll.lee.loginserver.Main3Activity;
import me.bamtoll.lee.loginserver.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WriteFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ((Main3Activity) getContext()).displayFab(false);

        View root = inflater.inflate(R.layout.fragment_write, container, false);
        Button writeBtn = root.findViewById(R.id.btn_write);
        writeBtn.setOnClickListener(v -> {
            EditText titleEdit = root.findViewById(R.id.edit_write_title);
            EditText contentsEdit = root.findViewById(R.id.edit_write_contents);

            String title = titleEdit.getText().toString();
            String contents = contentsEdit.getText().toString();

            write(title, contents);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ((Main3Activity) getContext()).displayFab(true);
    }

    public void write(String tit, String con) {
        ((Main3Activity) getContext()).service.write(tit, con).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getContext().getApplicationContext(), "WRITE SUCCESS!", Toast.LENGTH_SHORT).show();
                ((Main3Activity) getContext()).onBackPressed();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext().getApplicationContext(), "WRITE FAIL!", Toast.LENGTH_SHORT).show();
                ((Main3Activity) getContext()).onBackPressed();
            }
        });
    }
}
