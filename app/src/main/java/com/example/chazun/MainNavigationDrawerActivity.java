package com.example.chazun;

import static com.example.chazun.R.id.LogOutMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainNavigationDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation_drawer);
        this.toolbar = findViewById(R.id.drawerToolbar);
        setSupportActionBar(toolbar);
        this.drawerLayout = findViewById(R.id.mainDrawer);
        this.navigationView = findViewById(R.id.navigationView);
        this.actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        this.drawerLayout.addDrawerListener(actionBarDrawerToggle);
        this.navigationView.setNavigationItemSelectedListener(this);
        this.actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        this.actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == LogOutMenu) {
            Intent logOut = new Intent(getApplicationContext(),SignInActivity.class);
            startActivity(logOut);
            finish();
        }
        return true;
    }
}