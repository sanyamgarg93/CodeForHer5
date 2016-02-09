package com.example.android.reportit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {


    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;

    public MainActivityFragment()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        //perform operation on send button click
        Button sendButton = (Button)view.findViewById(R.id.button);
        sendButton.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View v)
            {
                //Send Media Packet
            }
        });

        //Camera Button

        /*
        ImageButton cameraButton = (ImageButton)view.findViewById(R.id.imageButton);
        cameraButton.setOnClickListener(new ImageButton.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent cameraIntent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(cameraIntent,0);
            }
        });
        */

        imageView = (ImageView)view.findViewById(R.id.imageView);
        ImageButton photoButton = (ImageButton)view.findViewById(R.id.imageButton);
        photoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        //GPS Location
        Button locationButton = (Button)view.findViewById(R.id.show_location);
        locationButton.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View v)
            {
                GPSTracker gps = new GPSTracker(getActivity());

                if (gps.canGetLocation())
                {
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    Toast.makeText(getContext(), "Your location is- \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_SHORT).show();

                }
                else
                {
                    gps.showSettingsAlert();
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
    }

}