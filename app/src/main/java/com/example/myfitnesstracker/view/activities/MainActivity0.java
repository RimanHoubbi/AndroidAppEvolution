package com.example.myfitnesstracker.view.activities;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.example.myfitnesstracker.R;
import com.example.myfitnesstracker.databinding.ActivityMain0Binding;
import com.example.myfitnesstracker.viewmodel.MainViewModel;

public class MainActivity0 extends LocalizationActivity {

    private ActivityMain0Binding binding;
    private AppBarConfiguration appBarConfiguration;
    MainViewModel viewModel;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain0Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.nav_host_fragment);

        NavController navController =navHostFragment.getNavController();
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);

        SharedPreferences settings = getSharedPreferences("mysettings", Context.MODE_PRIVATE);
        if (settings.getString("lang","de").equals("en")){
            setLanguage("en");
        }else{
            setLanguage("de");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
       NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment );
       return NavigationUI.navigateUp(navController,appBarConfiguration) || onSupportNavigateUp();
    }
}