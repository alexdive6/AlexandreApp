package com.github.alexandre.dive.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;
import com.github.alexandre.dive.R;
import com.github.alexandre.dive.Singletons;
import com.github.alexandre.dive.presentation.controller.MainController;
import com.github.alexandre.dive.presentation.model.Pokemon;
import java.util.List;

public class mainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListAdapter listAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller = new MainController(this, Singletons.getGson(), Singletons.getsharedPreferencesInstance(getApplicationContext()));
        controller.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                listAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    public void showList(List <Pokemon> pokemonList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new ListAdapter(pokemonList, new ListAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(Pokemon item) {
                controller.onItemClick(item);
            }
        });
        recyclerView.setAdapter(listAdapter);
    }

    public void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }

    public void navigateToDetails(Pokemon pokemon) {
        System.out.println(pokemon);
        Intent myIntent = new Intent(mainActivity.this, DetailActivity.class);
        myIntent.putExtra("pokemonKey", Singletons.getGson().toJson(pokemon));
        mainActivity.this.startActivity(myIntent);
    }
}