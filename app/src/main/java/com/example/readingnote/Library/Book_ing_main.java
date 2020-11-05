package com.example.readingnote.Library;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.readingnote.R;

public class Book_ing_main extends AppCompatActivity {
    private String Book_ing_main_title, Book_ing_number;

    public Book_ing_main(String book_ing_title, String book_ing_number) {
        Book_ing_main_title = book_ing_title;
        Book_ing_number = book_ing_number;
    }

    public String getBook_ing_title() {
        return Book_ing_main_title;
    }

    public String getBook_ing_number() {
        return Book_ing_number;
    }
}