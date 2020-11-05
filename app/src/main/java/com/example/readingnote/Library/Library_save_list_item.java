package com.example.readingnote.Library;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.readingnote.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Library_save_list_item extends AppCompatActivity {
    public String Newbook_title;
    public String Newbook_image;
    public String Newbook_author;
    public String Newbook_publisher;
    public String bmp, Newbook_date;
    public ArrayList<Note_item> NoteList;

    public String getNewbook_date() {
        return Newbook_date;
    }

    public void setNewbook_date(String newbook_date) {
        Newbook_date = newbook_date;
    }

    public String getBmp() {
        return bmp;
    }

    public void setBmp(String bmp) {
        this.bmp = bmp;
    }

    public ArrayList<Note_item> getNotelist() {
        return NoteList;
    }

    public void setNotelist(ArrayList<Note_item> notelist) {
        this.NoteList = notelist;
    }

    public Library_save_list_item(){}

    public ArrayList<Note_item> getNoteList() {
        return NoteList;
    }

    public void setNoteList(ArrayList<Note_item> noteList) {
        NoteList = noteList;
    }

    //Constructor
    public Library_save_list_item(String newbook_title, String newbook_image, String newbook_author, String newbook_publisher, String date, ArrayList<Note_item> NoteList) {
        Newbook_title = newbook_title;
        Newbook_image = newbook_image;
        Newbook_author = newbook_author;
        Newbook_publisher = newbook_publisher;
        Newbook_date = date;
        this.NoteList = NoteList;
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


}