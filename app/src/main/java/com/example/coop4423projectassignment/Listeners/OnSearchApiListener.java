package com.example.coop4423projectassignment.Listeners;

import com.example.coop4423projectassignment.Models.SearchApiResponse;

public interface OnSearchApiListener {
    void onResponse(SearchApiResponse response);
    void onError(String message);
}
