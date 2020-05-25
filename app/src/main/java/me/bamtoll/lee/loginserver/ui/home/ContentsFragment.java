package me.bamtoll.lee.loginserver.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import me.bamtoll.lee.loginserver.Main3Activity;
import me.bamtoll.lee.loginserver.R;

public class ContentsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Toast.makeText(getContext(), , Toast.LENGTH_SHORT).show();

        ((Main3Activity) getContext()).displayFab(false);

        View root = inflater.inflate(R.layout.fragment_contents, container, false);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();


        ((Main3Activity) getContext()).displayFab(true);
    }
}
