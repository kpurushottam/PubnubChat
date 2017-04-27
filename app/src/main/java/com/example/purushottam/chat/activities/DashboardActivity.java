package com.example.purushottam.chat.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.purushottam.chat.R;
import com.example.purushottam.chat.adapters.NavContactAdapter;
import com.example.purushottam.chat.modals.NavContact;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
            NavContactAdapter.OnNavContactActionListener {

    private DrawerLayout mDrawer;
    private RecyclerView mRecyclerNavContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_menu);
        navigationView.setNavigationItemSelectedListener(this);

        // load navigation fragment
        /*getSupportFragmentManager().beginTransaction()
                .add(R.id.container_fragment, new Fragment(),
                        Fragment.TAG)
                .commit();*/

        setupNavContact();
    }

    private void setupNavContact() {
        mRecyclerNavContact = (RecyclerView) findViewById(R.id.recycler);
        mRecyclerNavContact.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerNavContact.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        List<NavContact> contacts = new ArrayList<>();
        double lat = 28.38;
        double lng = 77.12;
        for(int i=0; i<10; i++) {
            contacts.add(new NavContact("Name : " + i, "About None", lat+i, lng+i));
        }
        mRecyclerNavContact.setAdapter(new NavContactAdapter(this, contacts));
    }

    public void alert(String message) {
        if(TextUtils.isEmpty(message)) {
            Snackbar.make(DashboardActivity.this.findViewById(R.id.container_coordinator),
                    "Invalid location", Snackbar.LENGTH_LONG).show();
        } else {
            //super.alert(message);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_contacts) {
            mDrawer.openDrawer(Gravity.RIGHT);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onVisibilityChecked(NavContact contact, boolean visible, boolean enableZoom) {
//        Fragment mapFg = (Fragment) getSupportFragmentManager().findFragmentByTag(Fragment.TAG);

//        if(mapFg != null) {
//            mapFg.onUserStatusChanged(contact, visible, enableZoom);
//        }
//        if(visible) {
//            mDrawer.closeDrawer(Gravity.RIGHT);
//        }
    }

    @Override
    public void onShowContactDetails(NavContact contact) {

    }
}
