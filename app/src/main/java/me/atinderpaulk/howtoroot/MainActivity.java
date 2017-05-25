package me.atinderpaulk.howtoroot;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        // Defining the navigation bar and linking it to the nav drawer in activity_main
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header_view = navigationView.getHeaderView(0);

        // Making the header of the nav drawer, which is a linear layout, the home button
        LinearLayout header = (LinearLayout) header_view.findViewById(R.id.header_main);
        header.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Opening the home screen when header is tapped
                MainActivity.this.startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(Gravity.START);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // All the conditions for the items in the menu in the nav drawer
        if (id == R.id.nav_device) {
            MainActivity.this.startActivity(new Intent(MainActivity.this, DeviceListActivity.class));
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
            MainActivity.this.startActivity(new Intent(MainActivity.this, AboutActivity.class));
        }
        else if (id == R.id.nav_settings) {
            // Restarts settings activity when menu option is tapped
            MainActivity.this.startActivity(new Intent(MainActivity.this, SettingsActivity.class));
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
