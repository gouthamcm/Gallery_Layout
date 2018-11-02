package com.example.goutham.gallery_layout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toolbar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class display extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;
    ImageButton imageButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        toolbar = findViewById(R.id.toolbar);//finding the toolbar using its unique id.
        setSupportActionBar(toolbar);//setting up the toolbar int screen.

        imageButton=findViewById(R.id.share_button);//finds the image view from the xml layout
        Intent i = getIntent();//retrieving the intent which has the position of the image
        final int position = i.getExtras().getInt("name");/*

        getting the position of the image using the unique key/id "name".
        And finally assigning the value to a variable position of int data type.
                */


        final GridAdapter gridAdapter = new GridAdapter(this) {
        };//creating an instance of grid adapter class
        ImageView imageView = findViewById(R.id.imageView);//finds the image in the layout.xml file.
        imageView.setImageResource(gridAdapter.images[position]);/*

         using the grid adapter instance in getting the id of the image at
         position "position" of the images array and displaying the image in the screen.

         */





/*---------------------------------------------------------------------------------------------------------------------------------------------*/




        /*
         The following code is used to send the selected image via
         whatsapp or any other relevant media.

         */
        imageButton.setOnClickListener(new ImageView.OnClickListener() {

           /*
            This block of code runs on clicking the button on the top right corner of the screen.
            */
            @Override
            public void onClick(View v) {


                Bitmap bitmap=BitmapFactory.decodeResource(getResources(),gridAdapter.images[position]);
                File path=Environment.getExternalStorageDirectory();
                File directory=new File(path+"/image/");
                directory.mkdir();

                File file=new File(directory,"image.png");
                OutputStream outputStream=null;
                try {
                    outputStream=new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
                    outputStream.flush();
                    outputStream.close();
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }


                Intent intent=new Intent(Intent.ACTION_SEND);//here the function of intent is to send data to another activity across process boundaries.
                intent.setType("image/*");//sets the data to be sent as image.and "*" means the data can be in any type(e.g.,jpeg,png,..)
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));/*

                this statement attaches the selected image with the intent,
                 which is then sent to the next activity.

                */
                startActivity(Intent.createChooser(intent,"Share image using"));/*

                creates a pop up in which the user can select where to send the image.

                */
            }
        });
    }



}
