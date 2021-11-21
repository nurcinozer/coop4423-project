package com.example.coop4423projectassignment.Listeners;

import com.example.coop4423projectassignment.Models.DetailApiResponse;

public interface OnDetailsApiListener {
    void onResponse(DetailApiResponse response);
    void onError(String message);
}
