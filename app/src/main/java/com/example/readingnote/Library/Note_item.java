package com.example.readingnote.Library;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Note_item extends AppCompatActivity {
    public String Savednote_date, Savednote_image, Savednote_contents;

    public Note_item(String savednote_date, String savednote_image, String savednote_contents) {
        Savednote_date = savednote_date;
        Savednote_image = savednote_image;
        Savednote_contents = savednote_contents;
    }

    public String getSavednote_date() {
        return Savednote_date;
    }

    public void setSavednote_date(String savednote_date) {
        Savednote_date = savednote_date;
    }

    public String getSavednote_image() {
        return Savednote_image;
    }

    public void setSavednote_image(String savednote_image) {
        Savednote_image = savednote_image;
    }

    public String getSavednote_contents() {
        return Savednote_contents;
    }

    public void setSavednote_contents(String savednote_contents) {
        Savednote_contents = savednote_contents;
    }
}