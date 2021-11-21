package com.example.coop4423projectassignment;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.coop4423projectassignment.Listeners.OnDetailsApiListener;
import com.example.coop4423projectassignment.Listeners.OnSearchApiListener;
import com.example.coop4423projectassignment.Models.DetailApiResponse;
import com.example.coop4423projectassignment.Models.SearchApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void searchMovies(OnSearchApiListener listener, String movie_name) {
        getMovies getMovies = retrofit.create(RequestManager.getMovies.class);
        Call<SearchApiResponse> call = getMovies.callMovies("b9e67e7f965f7776662779e9c42b8d65", "en-US", movie_name, 1);

        call.enqueue(new Callback<SearchApiResponse>() {
            @Override
            public void onResponse(Call<SearchApiResponse> call, Response<SearchApiResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, "Couldn't fetch data!", Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<SearchApiResponse> call, Throwable t) {
                listener.onError(t.getMessage());
            }
        });
    }

    public void searchMovieDetails(OnDetailsApiListener listener, Integer movie_id) {
        getMovieDetails getMovieDetails = retrofit.create(RequestManager.getMovieDetails.class);
        Call<DetailApiResponse> call = getMovieDetails.callMovieDetails(movie_id, "b9e67e7f965f7776662779e9c42b8d65");

        call.enqueue(new Callback<DetailApiResponse>() {
            @Override
            public void onResponse(Call<DetailApiResponse> call, Response<DetailApiResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, "Couldn't fetch data!", Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.onResponse(response.body());
                System.out.println(response);
            }

            @Override
            public void onFailure(Call<DetailApiResponse> call, Throwable t) {
                listener.onError(t.getMessage());
            }
        });
    }

    public interface getMovies {
//      https://api.themoviedb.org/3/search/movie?api_key={api_key}&query=Jack+Reacher

        @GET("search/movie?")
        Call<SearchApiResponse> callMovies(
                @Query("api_key") String apiKey,
                @Query("language") String language,
                @Query("query") String movie_name,
                @Query("page") int page
        );
    }

    public interface getMovieDetails {
//      https://api.themoviedb.org/3/movie/{movie_id}?api_key=<<api_key>>&language=en-US
//      https://api.themoviedb.org/3/movie/75780?api_key=b9e67e7f965f7776662779e9c42b8d65&language=en-US

        @GET("movie/{movie_id}")
        Call<DetailApiResponse> callMovieDetails(
                @Path("movie_id") int movie_id,
                @Query("api_key") String apiKey
        );

    }
}
