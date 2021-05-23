package com.github.alexandre.dive.presentation.model;

public class PokemonDetailResponse {
    private com.github.alexandre.dive.presentation.model.Pokemon pokemon;
    private int weight;
    private int height;
    public Pokemon getPokemon() {
            return pokemon;
    }
    public int getWeight() {
        return weight;
    }
    public int getHeight() {
        return height;
    }
}