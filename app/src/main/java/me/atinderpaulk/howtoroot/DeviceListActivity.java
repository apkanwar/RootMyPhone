package me.atinderpaulk.howtoroot;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.support.v7.widget.DefaultItemAnimator;

import java.util.ArrayList;
import java.util.List;

public class DeviceListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<Device> devicesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_device_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_device_list);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        // Defining the navigation bar and linking it to the nav drawer in activity_main
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_device_list);
        navigationView.setNavigationItemSelectedListener(this);
        View header_view = navigationView.getHeaderView(0);

        // Making the header of the nav drawer, which is a linear layout, the home button
        LinearLayout header = (LinearLayout) header_view.findViewById(R.id.header_main);
        header.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Opening the home screen when header is tapped
                DeviceListActivity.this.startActivity(new Intent(DeviceListActivity.this, MainActivity.class));

            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_device_list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        DeviceListAdapter mAdapter = new DeviceListAdapter(DeviceListActivity.this, devicesList);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DevicesListDecorator(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        //Adding Items to List
        devicesList.add(new Device("OnePlus One", R.drawable.opo_image_small));
        devicesList.add(new Device("Asus Zenfone 2 (Z00A)", R.drawable.zenfone2_image_small));
        devicesList.add(new Device("Samsung Galaxy S4", R.drawable.zenfone2_image_small));

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // All the conditions for the items in the menu in the nav drawer
        if (id == R.id.nav_device) {
            DeviceListActivity.this.startActivity(new Intent(DeviceListActivity.this, DeviceListActivity.class));
        }
        else if (id == R.id.nav_request) {
            // Creates a new intent to send a email to the developer for a device request
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto","apkanwar73@gmail.com", null));
            // Part message and sybject already placed in email
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Request for Root my Phone");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Please enter you device name and model# below and we will add it to our device list ASAP.\n" +
                    "----------------------------------------------------------------\n\n");
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
        }
        else if (id == R.id.nav_about) {
            // Starts about activity from the Main Activity, when menu option is tapped
            DeviceListActivity.this.startActivity(new Intent(DeviceListActivity.this, AboutActivity.class));
        }
        else if (id == R.id.nav_settings) {
            // Restarts settings activity when menu option is tapped
            DeviceListActivity.this.startActivity(new Intent(DeviceListActivity.this, SettingsActivity.class));
        }
        else if (id == R.id.nav_share) {
            // Creates an intent which allows the user to share the app with their friends
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Check out www.github.com");
            startActivity(Intent.createChooser(sharingIntent, "Share via"));
        }
        else if (id == R.id.nav_feedback) {
            // Opens a link to the google play store app page so the user can give feedback.
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.android.chrome")));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
