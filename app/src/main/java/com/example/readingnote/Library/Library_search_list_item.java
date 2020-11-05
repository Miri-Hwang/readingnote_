package com.example.readingnote.Library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.example.readingnote.R;

public class Library_search_list_item extends AppCompatActivity {
    public String Newbook_title;
    public String Newbook_image;
    public String Newbook_author;
    public String Newbook_publisher;
    public String bmp;



    public String getBmp() {
        return bmp;
    }

    public void setBmp(String bmp) {
        this.bmp = bmp;
    }




    public Library_search_list_item(){}
    //Constructor
    public Library_search_list_item(String newbook_title, String newbook_image, String newbook_author, String newbook_publisher) {
        Newbook_title = newbook_title;
        Newbook_image = newbook_image;
        Newbook_author = newbook_author;
        Newbook_publisher = newbook_publisher;

    }

    //Getter & Setter

    public String getNewbook_title() {
        return Newbook_title;
    }

    public void setNewbook_title(String newbook_title) {
        Newbook_title = newbook_title;
    }

    public String getNewbook_image() {
        return Newbook_image;
    }

    public void setNewbook_image(String newbook_image) {
        Newbook_image = newbook_image;
    }


    public String getNewbook_author() {
        return Newbook_author;
    }

    public void setNewbook_author(String newbook_author) {
        Newbook_author = newbook_author;
    }

    public String getNewbook_publisher() {
        return Newbook_publisher;
    }

    public void setNewbook_publisher(String newbook_publisher) {
        Newbook_publisher = newbook_publisher;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_search_list_item);
    }
}