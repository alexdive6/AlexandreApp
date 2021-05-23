package com.github.alexandre.dive.presentation.model;

import java.util.List;

public class PokemonResponse {
    private Integer count;
    private String next;
    private List <com.github.alexandre.dive.presentation.model.Pokemon> results;
    public Integer getCount() {
        return count;
    }
    public String getNext() {
        return next;
    }
    public List<Pokemon> getResults() {
        return results;
    }
}