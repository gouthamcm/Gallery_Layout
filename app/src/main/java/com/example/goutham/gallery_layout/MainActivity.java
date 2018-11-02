package com.example.goutham.gallery_layout;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    GridView gridView; // Defining the grid view in java class


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        gridView=findViewById(R.id.gridView);  /*
                Finds the grid view from the xml layout of the current activity
        */


        gridView.setAdapter(new GridAdapter(this));/*

        An adapter acts as a bridge between your data and the layout(here it is grid).
        Adapters control the contents displayed in the grids as well as how to display it.

        */

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        /*
            this block of code is executed as and when any image in the grid is clicked

        */

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(getApplicationContext(),display.class);/*

                creating a new intent to go to display activity.

                */
                i.putExtra("name",position);/*

                Passing the position of the image in the array images[] to the display activity
                with the id of "name".


                */
                startActivity(i); //launching the display activity.
            }
        });
    }



}
