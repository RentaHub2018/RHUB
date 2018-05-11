package com.example.dell.fenny;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.fenny.fragments.AboutUs;
import com.example.dell.fenny.fragments.BlockedUser;
import com.example.dell.fenny.fragments.Chat;
import com.example.dell.fenny.fragments.EditPreference;
import com.example.dell.fenny.fragments.Home;
import com.example.dell.fenny.fragments.Logout;
import com.example.dell.fenny.fragments.PostRequirement;
import com.example.dell.fenny.fragments.RateUs;
import com.example.dell.fenny.fragments.ScreenMainFragment;
import com.example.dell.fenny.fragments.Share;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /* private FirebaseAuth firebaseAuth;
      private Button buttonLogout;
      private TextView textViewUseremail;
      private DatabaseReference databaseReference;
      private EditText editTextName ,editTextAddress;
      private Button buttonSave;*/
    private ShareActionProvider mShareActionProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

     /*  firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() ==null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }

        FirebaseUser user= firebaseAuth.getCurrentUser();
        textViewUseremail = (TextView)findViewById(R.id.textViewUserEmail);
        textViewUseremail.setText("Welcome" +user.getEmail());
        databaseReference = FirebaseDatabase.getInstance().getReference();
        editTextAddress =(EditText) findViewById(R.id.editTextAddress);
        editTextName = (EditText) findViewById(R.id.editTextName);

        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);

        buttonLogout.setOnClickListener(this);
        buttonSave.setOnClickListener(this);*/


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        displaySelectedScreen(R.id.nav_home);


    }

    /*  private void saveUserInformation(){
         String name = editTextName.getText().toString().trim();
         String address= editTextAddress.getText().toString().trim();

         UserInformation userInformation = new UserInformation(name,address);

         FirebaseUser user = firebaseAuth.getCurrentUser();
         databaseReference.child(user.getUid()).setValue(userInformation);
         Toast.makeText(this, "Information Saved...",Toast.LENGTH_LONG).show();

     }*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);

      return true;
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_item_share) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void displaySelectedScreen(int itemId){

        //Creating fragment object
        Fragment fragment = null;
        //Initializing the fragment which is selectedI

        switch(itemId){
            case R.id.nav_home:
                fragment = new ScreenMainFragment();
                break;
            case R.id.nav_postrequirement:
                fragment = new PostRequirement();
                break;
            case R.id.nav_editpreference:
                fragment = new EditPreference();
                break;
            case R.id.nav_chat:
                fragment = new Chat();
                break;
            case R.id.nav_blockeduser:
                fragment = new BlockedUser();
                break;
            case R.id.nav_aboutus:
                fragment = new AboutUs();
                break;
            case R.id.nav_rateus:
                fragment = new RateUs();
                break;
            case R.id.nav_Logout:
                fragment = new Logout();
                break;
        }

        //replacing the fragment
        if(fragment != null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragmentholder,fragment);
            ft.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displaySelectedScreen(item.getItemId());
        // Handle navigation view item clicks here.
      /*  int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_editpreference) {

        } else if (id == R.id.nav_chat) {

        } else if (id == R.id.nav_blockeduser) {

        } else if (id == R.id.nav_aboutus) {

        } else if (id == R.id.nav_rateus) {

        }else if (id == R.id.nav_Logout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START); */
        return true;
    }



  /* @Override
    public void onClick(View view) {
        if(view == buttonLogout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }

     if(view == buttonSave){
        saveUserInformation();
    }
}*/
}
