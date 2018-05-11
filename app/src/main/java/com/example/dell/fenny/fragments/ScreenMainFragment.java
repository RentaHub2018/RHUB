package com.example.dell.fenny.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dell.fenny.Models.HouseRegisteration;
import com.example.dell.fenny.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ScreenMainFragment extends Fragment {
    private RecyclerView recyclerView;
    public DatabaseReference databaseReferenceForDetails;
    public FirebaseRecyclerAdapter<HouseRegisteration,ScreenMainFragment.HouseRegisterationViewHolder> myrecAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_screen, container, false);
        setupRecyclerView(view);
        return view;

    }
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private void setupRecyclerView(View view){

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference().child("HouseRegisteration");

        recyclerView = (RecyclerView)view.findViewById(R.id.fragmentMainScreenRecyclerView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        databaseReferenceForDetails = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("RegisteredHouses");

        DatabaseReference TemporaryReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("RegisteredHouses");

        Query query = TemporaryReference.orderByKey();
        FirebaseRecyclerOptions recyclerOptions = new FirebaseRecyclerOptions
                .Builder<HouseRegisteration>()
                .setQuery(
                query,
                HouseRegisteration.class
        ).build();

        myrecAdapter = new FirebaseRecyclerAdapter<HouseRegisteration, ScreenMainFragment.HouseRegisterationViewHolder>(recyclerOptions) {
            @Override
            protected void onBindViewHolder(@NonNull ScreenMainFragment.HouseRegisterationViewHolder holder, int position, @NonNull HouseRegisteration model) {
                holder.setArea(model.getArea());
                holder.setRent(model.getAmount());
                holder.setImage(model.getImage());
            }

            @NonNull
            @Override
            public ScreenMainFragment.HouseRegisterationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_main_screen_recycler_single_item, parent, false);
                return new ScreenMainFragment.HouseRegisterationViewHolder(view);
            }
        };
        recyclerView.setAdapter(myrecAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Home");

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public class HouseRegisterationViewHolder extends RecyclerView.ViewHolder {
        private View myView;
        public HouseRegisterationViewHolder(View itemView) {
            super(itemView);
            myView = itemView;
        }
        public void setArea(String area){
            final TextView areaTextView = (TextView)myView.findViewById(R.id.cardViewMainFragmentAreaTextView);
            areaTextView.setText(area);
        }
        public void setRent(String rent){
            final TextView rentTextView = (TextView)myView.findViewById(R.id.cardViewMainFragmentRentTextView);
            rentTextView.setText(rent);
        }
        public void setImage(final String imageuri){
            final ImageView imageView = (ImageView)myView.findViewById(R.id.cardViewMainFragmentImageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), ""+imageuri, Toast.LENGTH_SHORT).show();
                }
            });
            if(imageuri != null){
                storageReference.child(imageuri).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Glide.with(getContext()).load(uri).into(imageView);
                    }
                });
            }

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        myrecAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        myrecAdapter.stopListening();
    }
}
