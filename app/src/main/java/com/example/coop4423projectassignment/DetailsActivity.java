package com.example.coop4423projectassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.coop4423projectassignment.Listeners.OnDetailsApiListener;
import com.example.coop4423projectassignment.Models.DetailApiResponse;
import com.example.coop4423projectassignment.Models.Genres;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    TextView textView_movie_plot, textView_movie_name, textView_movie_release, textView_movie_category, textView_movie_score;
    ImageView imageView_movie_poster;
    RequestManager manager;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        textView_movie_plot = findViewById(R.id.textView_movie_plot);
        textView_movie_name = findViewById(R.id.textView_movie_name);
        textView_movie_release = findViewById(R.id.textView_movie_release);
        textView_movie_category = findViewById(R.id.textView_movie_category);
        textView_movie_score = findViewById(R.id.textView_movie_score);
        imageView_movie_poster = findViewById(R.id.imageView_movie_poster);

        manager = new RequestManager(this);

        Integer movie_id = getIntent().getIntExtra("data", 1);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Please wait...");
        dialog.show();

        manager.searchMovieDetails(listener, movie_id);
    }

    private OnDetailsApiListener listener = new OnDetailsApiListener() {
        @Override
        public void onResponse(DetailApiResponse response) {
            dialog.dismiss();
            if (response.equals(null)) {
                Toast.makeText(DetailsActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                return;
            }
            showResults(response);
        }

        @Override
        public void onError(String message) {
            dialog.dismiss();
            Toast.makeText(DetailsActivity.this, "Error!", Toast.LENGTH_SHORT).show();
        }
    };

    private void showResults(DetailApiResponse response) {
        textView_movie_name.setText(response.getTitle());
        textView_movie_release.setText("Release date: " + response.getRelease_date());
        textView_movie_score.setText("Score: " + response.getVote_average());
        List<String> genres = new ArrayList<>();
        for(Genres genre : response.getGenres()) {
            genres.add(genre.getName());
        }
        textView_movie_category.setText("Categories: " + genres.toString().substring(1,genres.toString().length()-1));
        textView_movie_plot.setText("Overview: " + response.getOverview());

        try {
            // Picasso.get().load("https://image.tmdb.org/t/p/w780/" + response.getBackdrop_path()).into(imageView_movie_poster);
            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w780/" + response.getBackdrop_path())
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(imageView_movie_poster);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}