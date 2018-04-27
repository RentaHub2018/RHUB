package com.example.dell.fenny.fragments;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.fenny.Models.HouseRegisteration;
import com.example.dell.fenny.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class HaveHome extends Fragment {


    private static final String TAG = "HaveHome";
      private EditText editarea;
      private EditText editrent;
      private TextView editphoto;
      private ImageView myUploadedImageViewer;
      Integer REQUEST_CAMERA = 1,SELECT_FILE = 0;




    public HaveHome() {
        // Required empty public constructor
    }


    private class ImageAdapter extends BaseAdapter {

        private Context adapterContext;

        public ImageAdapter(Context context) {
             adapterContext = context;
        }

        @Override
        public int getCount() {
            return myThumbId.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            ImageView imageView;
            if(convertView == null){
                imageView = new ImageView(adapterContext);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(85,85));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8,8,8,8);
            }else{
                imageView = (ImageView)convertView;
            }
            imageView.setImageResource(myThumbId[i]);
            return imageView;
        }

        @Nullable
        @Override
        public CharSequence[] getAutofillOptions() {
            return new CharSequence[0];
        }

        private Integer[] myThumbId = {
                R.drawable.applogo,
                R.drawable.applogo,
                R.drawable.applogo,
                R.drawable.applogo,
                R.drawable.applogo,
                R.drawable.applogo,
                R.drawable.applogo,
                R.drawable.applogo,
                R.drawable.applogo,
        };
    }


    @SuppressLint("CutPasteId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_have_home, container, false);

        editarea = (EditText)view.findViewById(R.id.editarea);
        editrent = (EditText)view.findViewById(R.id.editrent);
        editphoto = (TextView)view.findViewById(R.id.editphoto);

        myUploadedImageViewer = (ImageView)view.findViewById(R.id.myUploadedImageViewer);
        FloatingActionButton myImageUploadCameraHomeFragment = (FloatingActionButton)view.findViewById(R
                .id.myImageUploadCameraHomeFragment);
        myImageUploadCameraHomeFragment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                SelectImage();
            }
        });


        SelectImage();
         setUpGridLayoutItems(view);
         addSubRooms(view);
         pressedSubmitButton(view);
         //oncreateGridViewItems(v);
         return view;
    }

    private void SelectImage(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add Image");
        final CharSequence[] items={"Camera","Gallery","Cancel"};
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(items[i].equals("Camera")){

                    Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,REQUEST_CAMERA);

                }else if(items[i].equals("Gallery")){

                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore
                            .Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent.createChooser(intent,"Select File"),SELECT_FILE);

                }else if(items[i].equals("Cancel")){
                    dialogInterface.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(resultCode==Activity.RESULT_OK){
            if(requestCode== REQUEST_CAMERA){
                Bundle bundle = data.getExtras();
                final Bitmap bmp =(Bitmap) bundle.get("data");
                myUploadedImageViewer.setImageBitmap(bmp);

            }else if(requestCode==SELECT_FILE){

                Uri selectedImageUri = data.getData();
                myUploadedImageViewer.setImageURI(selectedImageUri);

            }
        }
    }



    public boolean AC_COLOR = true,
            FRIDGE_COLOR = true,
            FURNITURE_COLOR= true,
            HANDICAP_COLOR= true,
            KITCHEN_COLOR= true,
            MACHINE_COLOR= true,
            PARKING_COLOR= true,
            WIFI_COLOR= true,
            TV_COLOR= true;



    public void setUpGridLayoutItems(View  view){
        final ImageButton linearLayoutTV,
                linearLayoutFridge,
                linearLayoutHandicap,
                linearLayoutKitchen,
                linearLayoutWifi,
                linearLayoutMachine,
                linearLayoutAc,
                linearLayoutFurniture,
                linearLayoutParking;
        linearLayoutAc = (ImageButton)view.findViewById(R.id.myGridLayoutAcItem);
        linearLayoutFridge=(ImageButton)view.findViewById(R.id.myGridLayoutFridgeItem);
        linearLayoutFurniture=(ImageButton)view.findViewById(R.id.myGridLayoutFurnitureItem);
        linearLayoutHandicap=(ImageButton)view.findViewById(R.id.myGridLayoutHandicamItem);
        linearLayoutKitchen=(ImageButton)view.findViewById(R.id.myGridLayoutKitchenItem);
        linearLayoutMachine=(ImageButton)view.findViewById(R.id.myGridLayoutWasherMachineItem);
        linearLayoutParking=(ImageButton)view.findViewById(R.id.myGridLayoutCarItem);
        linearLayoutWifi=(ImageButton)view.findViewById(R.id.myGridLayoutWifiItem);
        linearLayoutTV=(ImageButton)view.findViewById(R.id.myGridLayoutTvItem);



        linearLayoutAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: clicked ac");
                if(AC_COLOR == true){
                    linearLayoutAc.setBackgroundResource(R.color.colorLightPrimary);
                    SELECTED_AC=true;
                    AC_COLOR = false;
                }else{
                    linearLayoutAc.setBackgroundResource(R.color.colorTransparent);
                    AC_COLOR = true;
                }            }
        });
        linearLayoutFridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FRIDGE_COLOR == true){
                    SELECTED_FRIDGE=true;
                    linearLayoutFridge.setBackgroundResource(R.color.colorLightPrimary);
                    FRIDGE_COLOR = false;
                }else{
                    linearLayoutFridge.setBackgroundResource(R.color.colorTransparent);
                    FRIDGE_COLOR = true;
                }            }
        });
        linearLayoutFurniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FURNITURE_COLOR == true){
                    SELECTED_FURNITURE=true;
                    linearLayoutFurniture.setBackgroundResource(R.color.colorLightPrimary);
                    FURNITURE_COLOR = false;
                }else{
                    linearLayoutFurniture.setBackgroundResource(R.color.colorTransparent);
                    FURNITURE_COLOR = true;
                }            }
        });
        linearLayoutHandicap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(HANDICAP_COLOR == true){
                    SELECTED_HANDICAP=true;
                    linearLayoutHandicap.setBackgroundResource(R.color.colorLightPrimary);
                    HANDICAP_COLOR = false;
                }else{
                    linearLayoutHandicap.setBackgroundResource(R.color.colorTransparent);
                    HANDICAP_COLOR = true;
                }            }
        });
        linearLayoutKitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(KITCHEN_COLOR == true){
                    SELECTED_KITCHEN=true;
                    linearLayoutKitchen.setBackgroundResource(R.color.colorLightPrimary);
                    KITCHEN_COLOR = false;
                }else{
                    linearLayoutKitchen.setBackgroundResource(R.color.colorTransparent);
                    KITCHEN_COLOR = true;
                }            }
        });
        linearLayoutMachine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MACHINE_COLOR == true){
                    SELECTED_MACHINE=true;
                    linearLayoutMachine.setBackgroundResource(R.color.colorLightPrimary);
                    MACHINE_COLOR = false;
                }else{
                    linearLayoutMachine.setBackgroundResource(R.color.colorTransparent);
                    MACHINE_COLOR = true;
                }
            }
        });
        linearLayoutParking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(PARKING_COLOR == true){
                    SELECTED_PARKING=true;
                    linearLayoutParking.setBackgroundResource(R.color.colorLightPrimary);
                    PARKING_COLOR = false;
                }else{
                    linearLayoutParking.setBackgroundResource(R.color.colorTransparent);
                    PARKING_COLOR = true;
                }
            }
        });
        linearLayoutTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TV_COLOR == true){
                    SELECTED_TV=true;
                    linearLayoutTV.setBackgroundResource(R.color.colorLightPrimary);
                    TV_COLOR = false;
                }else{
                    linearLayoutTV.setBackgroundResource(R.color.colorTransparent);
                    TV_COLOR = true;
                }            }
        });
        linearLayoutWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(WIFI_COLOR == true){
                    SELECTED_WIFI=true;
                    linearLayoutWifi.setBackgroundResource(R.color.colorLightPrimary);
                    WIFI_COLOR = false;
                }else{
                    linearLayoutWifi.setBackgroundResource(R.color.colorTransparent);
                    WIFI_COLOR = true;
                }            }
        });
    }



    public int rooms = 0;
    private int SELECTED_ROOMS;
    private void addSubRooms(View view){
        final TextView myRoomQuantity = (TextView)view.findViewById(R.id.myroomsrequiredTextView);
        ImageButton myIncImageButton = (ImageButton)view.findViewById(R.id
                .myincrementbuttonfragmet);
        myIncImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rooms++;
                String rm = Integer.toString(rooms);
                myRoomQuantity.setText(rm);
            }
        });
        ImageButton myDecImageButton = (ImageButton)view.findViewById(R.id
                .mydecrementbuttonfragmet);
        myDecImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rooms--;
                String rm = Integer.toString(rooms);
                myRoomQuantity.setText(rm);
            }
        });
    }



    private EditText myAreaorSectorEditText,
            myRentAmountEditText,
            myPhoneNumberEditText,
            myDescriptionEditText;
    private Button mySubmitandUploadButton;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    private boolean SELECTED_TV,
            SELECTED_FRIDGE,
            SELECTED_KITCHEN,
            SELECTED_WIFI,
            SELECTED_MACHINE,
            SELECTED_AC,
            SELECTED_FURNITURE,
            SELECTED_PARKING,
            SELECTED_HANDICAP;
    private void pressedSubmitButton(final View view){
        mySubmitandUploadButton = (Button)view.findViewById(R.id.mySubmitHavHomeSubmitButton);
        mySubmitandUploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "clicked submit button", Toast.LENGTH_SHORT).show();
                setupHouseRegisterationDetails(view);
            }
        });
    }
    private void setupHouseRegisterationDetails(View view){

        myAreaorSectorEditText = (EditText)view.findViewById(R.id.editTextAreaSectorHomeFragment);
        myRentAmountEditText = (EditText)view.findViewById(R.id.editTextRentAmountHomeFragment);
        myPhoneNumberEditText = (EditText)view.findViewById(R.id.myEnterPhoneNumberEditTextHomeFragment);
        myDescriptionEditText = (EditText)view.findViewById(R.id.myEnterDescriptionEditTextHomeFragment);

        String area = myAreaorSectorEditText.getText().toString();
        String amount = myRentAmountEditText.getText().toString();
        String phone = myPhoneNumberEditText.getText().toString();
        String description = myDescriptionEditText.getText().toString();
        String room = Integer.toString(rooms);

        uploadDataToFirebase(area,
                amount,
                phone,
                description,
                room,
                SELECTED_TV,
                SELECTED_FRIDGE,
                SELECTED_KITCHEN,
                SELECTED_WIFI,
                SELECTED_MACHINE,
                SELECTED_AC,
                SELECTED_FURNITURE,
                SELECTED_PARKING,
                SELECTED_HANDICAP);
    }

    private void uploadDataToFirebase(String area,
                                      String amount,
                                      String phone,
                                      String description,
                                      String room,
                                      boolean TV,
                                      boolean FRIDGE,
                                      boolean KITCHEN,
                                      boolean WIFI,
                                      boolean MACHINE,
                                      boolean AC,
                                      boolean FURNITURE,
                                      boolean PARKING,
                                      boolean HANDICAP){

        Toast.makeText(getContext(), "uploading to firebase", Toast.LENGTH_SHORT).show();
        DatabaseReference myRef = firebaseDatabase.getReference();
        HouseRegisteration registeration = new HouseRegisteration();

        registeration.setArea(area);
        registeration.setAmount(amount);
        registeration.setPhone(phone);
        registeration.setDescription(description);
        registeration.setRooms(room);

        registeration.setTv(TV);
        registeration.setFridge(FRIDGE);
        registeration.setKitchen(KITCHEN);
        registeration.setWifi(WIFI);
        registeration.setMachine(MACHINE);
        registeration.setAc(AC);
        registeration.setFurniture(FURNITURE);
        registeration.setParking(PARKING);
        registeration.setHandicap(HANDICAP);

        myRef.child("RegisteredHouses").push().setValue(registeration);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Have Home");
    }
}
