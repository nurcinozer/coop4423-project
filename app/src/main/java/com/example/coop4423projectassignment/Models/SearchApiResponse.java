package com.example.coop4423projectassignment.Models;

import java.util.List;

public class SearchApiResponse {
    List<SearchArrayObject> results = null;

    public List<SearchArrayObject> getResults() {
        return results;
    }

    public void setResults(List<SearchArrayObject> results) {
        this.results = results;
    }
}
