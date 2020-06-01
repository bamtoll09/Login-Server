package me.bamtoll.lee.loginserver.ui.home;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import me.bamtoll.lee.loginserver.Main3Activity;
import me.bamtoll.lee.loginserver.R;
import me.bamtoll.lee.loginserver.retrofit.Post;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FrameLayout frameLayout;
    private ListView listView;
    private PostAdapter postAdapter;
    private ArrayList<me.bamtoll.lee.loginserver.retrofit.Post> posts = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        frameLayout = root.findViewById(R.id.frame_home);
        listView = root.findViewById(R.id.list);
        postAdapter = new PostAdapter(container.getContext(), R.layout.item_post, posts);
        listView.setAdapter(postAdapter);
        new Thread(new Runnable() {
            @Override
            public void run() {
//                for (int i=0; i<300; ++i)
//                    posts.add(new Post("title" + i, "conconconconc" + i, SimpleDateFormat.getDateTimeInstance().format(new Date()), "Me"));
                getAllPosts();
                postAdapter.notifyDataSetChanged();
            }
        }).run();

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Bundle bundle = new Bundle();
            bundle.putString("title", ((Post) parent.getItemAtPosition(position)).getTitle());
            bundle.putString("contents", ((Post) parent.getItemAtPosition(position)).getContents());
            bundle.putString("writer", ((Post) parent.getItemAtPosition(position)).getWriter());
            bundle.putString("date", ((Post) parent.getItemAtPosition(position)).getDate());

            addContentsFragment(bundle);
        });

        return root;
    }

    public void addContentsFragment(Bundle bundle) {
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ContentsFragment cf = new ContentsFragment();
        cf.setArguments(bundle);
        ft.add(R.id.frame_home, cf).addToBackStack("Contents");
        ft.commit();
    }

    public void addWriteFragment() {
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.frame_home, new WriteFragment()).addToBackStack("Write");
        ft.commit();
    }

    public void getAllPosts() {
        ((Main3Activity) getContext()).service.getAll().enqueue(new Callback<List<me.bamtoll.lee.loginserver.retrofit.Post>>() {
            @Override
            public void onResponse(Call<List<me.bamtoll.lee.loginserver.retrofit.Post>> call, Response<List<me.bamtoll.lee.loginserver.retrofit.Post>> response) {
                postAdapter.addAll(response.body());
                Toast.makeText(getContext().getApplicationContext(), "Success to get Posts", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<me.bamtoll.lee.loginserver.retrofit.Post>> call, Throwable t) {
                Toast.makeText(getContext().getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}