package com.github.alexandre.dive.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.github.alexandre.dive.R;
import com.github.alexandre.dive.Singletons;
import com.github.alexandre.dive.presentation.controller.DetailController;
import com.github.alexandre.dive.presentation.model.Pokemon;

public class DetailActivity extends AppCompatActivity {
    private DetailController controller;
    private TextView name;
    private TextView height;
    private TextView weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        String pokemonJson = intent.getStringExtra("pokemonKey");
        Pokemon pokemon = Singletons.getGson().fromJson(pokemonJson, Pokemon.class);
        controller = new DetailController(this, Singletons.getGson(), Singletons.getsharedPreferencesInstance(getApplicationContext()), pokemon);
        controller.onStart();
    }

    public void showDetail(Pokemon pokemon) {
        name = findViewById(R.id.namePokemon);
        name.setText(pokemon.getName());
        weight = findViewById(R.id.weightPokemon);
        weight.setText(Integer.toString(pokemon.getWeight()));
        height = findViewById(R.id.heightPokemon);
        height.setText(Integer.toString(pokemon.getHeight()));
    }

    public void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }
}