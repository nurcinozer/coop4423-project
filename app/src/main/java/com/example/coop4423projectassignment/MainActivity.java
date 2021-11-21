package com.example.coop4423projectassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.coop4423projectassignment.Adapters.HomeRecyclerAdapter;
import com.example.coop4423projectassignment.Listeners.OnMovieClickListener;
import com.example.coop4423projectassignment.Listeners.OnSearchApiListener;
import com.example.coop4423projectassignment.Models.SearchApiResponse;

public class MainActivity extends AppCompatActivity implements OnMovieClickListener {
    SearchView search_view;
    RecyclerView recycler_view_home;
    HomeRecyclerAdapter adapter;
    RequestManager manager;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String str = "Welcome to MyApp";
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();

        search_view = findViewById(R.id.search_view);
        recycler_view_home = findViewById(R.id.recycler_view_home);

        dialog = new ProgressDialog(this);
        manager = new RequestManager(this);

        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Please wait...");
                dialog.show();
                manager.searchMovies(listener, query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.equals("")){
                    Toast.makeText(MainActivity.this, "You should enter at least one letter!", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }

    private final OnSearchApiListener listener = new OnSearchApiListener() {
        @Override
        public void onResponse(SearchApiResponse response) {
            dialog.dismiss();
            if (response == null) {
                Toast.makeText(MainActivity.this, "No data available!", Toast.LENGTH_SHORT).show();
                return;
            }
            showResult(response);
        }

        @Override
        public void onError(String message) {
            dialog.dismiss();
            Toast.makeText(MainActivity.this, "An Error Occurred!", Toast.LENGTH_SHORT).show();
        }
    };

    private void showResult(SearchApiResponse response) {
        recycler_view_home.setHasFixedSize(true);
        recycler_view_home.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        adapter = new HomeRecyclerAdapter(this, response.getResults(), this);
        recycler_view_home.setAdapter(adapter);
    }

    @Override
    public void onMovieClicked(Integer id) {
        startActivity(new Intent(MainActivity.this, DetailsActivity.class)
        .putExtra("data", id));
    }
}