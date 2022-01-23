package com.example.dukan_kholo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawer;
    androidx.appcompat.widget.Toolbar tol;
    NavigationView navbar;
    ActionBarDrawerToggle hamburger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        tol = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        navbar = (NavigationView) findViewById(R.id.navbar);

        hamburger = new ActionBarDrawerToggle(this,drawer, tol,R.string.open,R.string.close);
        drawer.addDrawerListener(hamburger);
        hamburger.syncState();
        tol.setNavigationIcon(R.drawable.ic_baseline_menu_24);


        navbar.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {


                    case R.id.two:
                        Intent a = new Intent(MainActivity.this, add_buisness_idea.class);
                        startActivity(a);
                        break;

                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });


    }
}