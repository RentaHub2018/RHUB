package com.example.dell.fenny.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.fenny.MainActivity;
import com.example.dell.fenny.R;
import com.example.dell.fenny.UserInformation;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import bolts.Task;

public class Logout extends Fragment {
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;
    private Button myLogoutButtonLogoutFragment;
    private DatabaseReference databaseReference;
    private EditText editTextName, editTextAddress;
    private Button buttonSave;


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();

        View view = inflater.inflate(R.layout.fragment_logout, container, false);


        Button button = (Button) view.findViewById(R.id.myLogoutButtonLogoutFragment);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //signing out
                mAuth.signOut();
                //navigating back to login activity
                Intent intent = new Intent(getActivity(), MainActivity.class);
                //use getActivity() instead of Activity.this for FRAGMENTS
                startActivity(intent);
                // Toast.makeText(getActivity(), "Logout button clicked! "+mAuth.getCurrentUser(), Toast.LENGTH_SHORT).show();
            }

        });

        databaseReference = FirebaseDatabase.getInstance().getReference();
        editTextAddress = (EditText) view.findViewById(R.id.editTextAddress);
        editTextName = (EditText) view.findViewById(R.id.editTextName);
        buttonSave = (Button) view.findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Save Data
                if (view == buttonSave) {
                    saveUserInformation();
                }
            }
        });

        return view;
    }


    private void saveUserInformation() {
        String name = editTextName.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();

        UserInformation userInformation = new UserInformation(name, address);

        FirebaseUser user = mAuth.getCurrentUser();
        databaseReference.child(user.getUid()).setValue(userInformation);
        Toast.makeText(getActivity(), "Information Saved...", Toast.LENGTH_LONG).show();
    }
}
