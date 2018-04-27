package com.example.dell.fenny.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.fenny.Models.PgRegisteration;
import com.example.dell.fenny.R;
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class PG extends Fragment {



//=============================================================================MAIN=======================================================================================
    public PG() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_pg, container, false);
        setupPgButtonsLined(view);
        setupPgSubmibtutton(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("PG");
    }

    //====================================================================================================================================================================

    private boolean
            SELECTED_MALE,
            SELECTED_FEMALIE,
            SELECTED_SINGLE_SHARING,
            SELECTED_DOUBLE_SHARING,
            SELECTED_TRIPLE_SHARING,
            SELECTED_FOUR_SHARING;

    private void setupPgButtonsLined(View view){

        final Button myMaleButton,
                myFemaleButton,
                mySingleButton,
                myDoubleButton,
                myTripleButton,
                myFourButton,
                myPgSubmitButton;
        myMaleButton = (Button)view.findViewById(R.id.myPgMaleButton);
        myFemaleButton= (Button)view.findViewById(R.id.myPgFeMaleButton);
        mySingleButton = (Button)view.findViewById(R.id.myPgSingleButton);
        myDoubleButton = (Button)view.findViewById(R.id.myPgDoubleButton);
        myTripleButton = (Button)view.findViewById(R.id.myPgTripleButton);
        myFourButton = (Button)view.findViewById(R.id.myPgFourButton);
        myPgSubmitButton = (Button)view.findViewById(R.id.myPgFormSubmitButton);
        myMaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SELECTED_MALE=true;
                myMaleButton.setBackground(getContext().getResources().getDrawable(R.drawable.button_pg_dark));
                myFemaleButton.setBackground(getContext().getResources().getDrawable(R.drawable.button_pg_light));
            }
        });
        myFemaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SELECTED_FEMALIE=true;
                myFemaleButton.setBackground(getContext().getResources().getDrawable(R.drawable.button_pg_dark));
                myMaleButton.setBackground(getContext().getResources().getDrawable(R.drawable.button_pg_light));
            }
        });
        mySingleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SELECTED_SINGLE_SHARING = true;
                mySingleButton.setBackground(getContext().getResources().getDrawable(R.drawable.button_pg_dark));
                myDoubleButton.setBackground(getContext().getResources().getDrawable(R.drawable.button_pg_light));
                myTripleButton.setBackground(getContext().getResources().getDrawable(R.drawable.button_pg_light));
                myFourButton.setBackground(getContext().getResources().getDrawable(R.drawable.button_pg_light));
            }
        });
        myDoubleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SELECTED_DOUBLE_SHARING = true;
                mySingleButton.setBackground(getContext().getResources().getDrawable(R.drawable.button_pg_light));
                myDoubleButton.setBackground(getContext().getResources().getDrawable(R.drawable.button_pg_dark));
                myTripleButton.setBackground(getContext().getResources().getDrawable(R.drawable.button_pg_light));
                myFourButton.setBackground(getContext().getResources().getDrawable(R.drawable.button_pg_light));
            }
        });
        myTripleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SELECTED_TRIPLE_SHARING = true;
                mySingleButton.setBackground(getContext().getResources().getDrawable(R.drawable.button_pg_light));
                myDoubleButton.setBackground(getContext().getResources().getDrawable(R.drawable.button_pg_light));
                myTripleButton.setBackground(getContext().getResources().getDrawable(R.drawable.button_pg_dark));
                myFourButton.setBackground(getContext().getResources().getDrawable(R.drawable.button_pg_light));
            }
        });
        myFourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SELECTED_FOUR_SHARING = true;
                mySingleButton.setBackground(getContext().getResources().getDrawable(R.drawable.button_pg_light));
                myDoubleButton.setBackground(getContext().getResources().getDrawable(R.drawable.button_pg_light));
                myTripleButton.setBackground(getContext().getResources().getDrawable(R.drawable.button_pg_light));
                myFourButton.setBackground(getContext().getResources().getDrawable(R.drawable.button_pg_dark));
            }
        });
        myPgSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    Button myPgSubmitButton;
    EditText myRentAmountEditText;
    private void setupPgSubmibtutton(View view){
        myPgSubmitButton = (Button) view.findViewById(R.id.myPgFormSubmitButton);
        myRentAmountEditText = (EditText)view.findViewById(R.id.myRentEditTextPg);

        myPgSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  amount = myRentAmountEditText.getText().toString();
                Toast.makeText(getContext(), "amount is "+amount, Toast.LENGTH_SHORT).show();

                uploadDataToDatabase(amount,
                        SELECTED_MALE,
                        SELECTED_FEMALIE,
                        SELECTED_SINGLE_SHARING,
                        SELECTED_DOUBLE_SHARING,
                        SELECTED_TRIPLE_SHARING,
                        SELECTED_FOUR_SHARING);
            }
        });
    }
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    private void uploadDataToDatabase(String amount,
                                      boolean MALE,
                                      boolean FEMALE,
                                      boolean SINGLE,
                                      boolean DOUBLE,
                                      boolean TRIPLE,
                                      boolean FOUR){
        DatabaseReference myRef = firebaseDatabase.getReference();
        PgRegisteration registeration = new PgRegisteration();
        registeration.setAmount(amount);
        registeration.setMale(MALE);
        registeration.setFemale(FEMALE);
        registeration.setSingle(SINGLE);
        registeration.setTwo(DOUBLE);
        registeration.setTriple(TRIPLE);
        registeration.setFour(FOUR);
        myRef.child("RegisteredHousesPayingGuest").push().setValue(registeration);

    }

}
