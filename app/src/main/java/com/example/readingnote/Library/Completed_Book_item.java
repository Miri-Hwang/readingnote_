package com.example.readingnote.Library;

import androidx.appcompat.app.AppCompatActivity;

public class Completed_Book_item  extends AppCompatActivity {
    String ttitle, image, author, publisher, startdate, notelist, rate, review, enddate;


    public String getTtitle() {
        return ttitle;
    }

    public void setTitle(String ttitle) {
        this.ttitle = ttitle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getNotelist() {
        return notelist;
    }

    public void setNotelist(String notelist) {
        this.notelist = notelist;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public Completed_Book_item(String ttitle, String image, String author, String publisher, String startdate, String notelist, String rate, String review, String enddate) {
        this.ttitle =ttitle;
        this.image = image;
        this.author = author;
        this.publisher = publisher;
        this.startdate = startdate;
        this.notelist = notelist;
        this.rate = rate;
        this.review = review;
        this.enddate = enddate;
    }
}
