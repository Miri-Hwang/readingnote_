package com.example.readingnote.Library;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.readingnote.R;

public class Book_ing extends AppCompatActivity {
    String Book_ing_title, Book_ing_image, Book_ing_date, Book_ing_note_number;

    public Book_ing(String book_ing_title, String book_ing_image, String book_ing_date, String book_ing_note_number) {
        Book_ing_title = book_ing_title;
        Book_ing_image = book_ing_image;
        Book_ing_date = book_ing_date;
        Book_ing_note_number = book_ing_note_number;
    }

    public String getBook_ing_title() {
        return Book_ing_title;
    }

    public String getBook_ing_image() {
        return Book_ing_image;
    }

    public String getBook_ing_date() {
        return Book_ing_date;
    }

    public String getBook_ing_note_number() {
        return Book_ing_note_number;
    }
}