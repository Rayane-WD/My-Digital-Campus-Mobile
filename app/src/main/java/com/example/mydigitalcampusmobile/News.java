package com.example.mydigitalcampusmobile;

public class News {


    int id_img;
    String title;
    String url;

    public News(int img, String title, String url){

        this.id_img = img;
        this.title = title;
        this.url = url;

    }


    public int getId_img() {
        return id_img;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
