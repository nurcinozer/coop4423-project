package com.example.coop4423projectassignment.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coop4423projectassignment.Listeners.OnMovieClickListener;
import com.example.coop4423projectassignment.MainActivity;
import com.example.coop4423projectassignment.Models.SearchArrayObject;
import com.example.coop4423projectassignment.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeViewHolder> {
    Context context;
    List<SearchArrayObject> list;
    OnMovieClickListener listener;

    public HomeRecyclerAdapter(Context context, List<SearchArrayObject> list, OnMovieClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeViewHolder(LayoutInflater.from(context).inflate(R.layout.home_movies_list, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textView_movie.setText(list.get(position).getOriginal_title());
        holder.textView_popularity.setText("Popularity: " + Float.toString(list.get(position).getPopularity()));
        holder.textView_movie.setSelected(true);
        if (!TextUtils.isEmpty(list.get(position).getPoster_path()))
            //Picasso.get().load("https://image.tmdb.org/t/p/w780/" + list.get(position).getPoster_path()).into(holder.imageView_poster);
            Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w780/" + list.get(position).getPoster_path())
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(holder.imageView_poster);

        holder.home_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onMovieClicked(list.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class HomeViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView_poster;
    TextView textView_movie, textView_popularity;
    CardView home_container;

    public HomeViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView_poster = itemView.findViewById(R.id.imageView_poster);
        textView_movie = itemView.findViewById(R.id.textView_movie);
        textView_popularity = itemView.findViewById(R.id.textView_popularity);
        home_container = itemView.findViewById(R.id.home_container);
    }
}
