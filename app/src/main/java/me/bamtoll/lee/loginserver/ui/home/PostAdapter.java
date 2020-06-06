package me.bamtoll.lee.loginserver.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import me.bamtoll.lee.loginserver.R;

public class PostAdapter extends ArrayAdapter<me.bamtoll.lee.loginserver.retrofit.Post> {

    public PostAdapter(@NonNull Context context, int resource, @NonNull List<me.bamtoll.lee.loginserver.retrofit.Post> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View root = convertView;

        if (root == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            root = inflater.inflate(R.layout.item_post, parent, false);
        }
        final me.bamtoll.lee.loginserver.retrofit.Post post = this.getItem(position);
        if (post != null) {
            final TextView titleText = root.findViewById(R.id.text_post_title);
            final TextView writerText = root.findViewById(R.id.text_post_writer);
            final TextView dateText = root.findViewById(R.id.text_post_date);

            titleText.setText(post.getTitle());
            writerText.setText(post.getWriter());
            dateText.setText(post.getDate());
        }

        return root;
    }
}
