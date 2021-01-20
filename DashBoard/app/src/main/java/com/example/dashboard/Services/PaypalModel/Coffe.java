package com.example.dashboard.Services.PaypalModel;

public class Coffe {
    private String title, subTitle, description;
    private int image, background, decoration, titleColor;

    public Coffe(String title, String subTitle, String description, int image, int background, int decoration, int titleColor) {
        this.title = title;
        this.subTitle = subTitle;
        this.description = description;
        this.image = image;
        this.background = background;
        this.decoration = decoration;
        this.titleColor = titleColor;
    }

    public String getTitle() {
        return title;
    }


    public String getSubTitle() {
        return subTitle;
    }


    public String getDescription() {
        return description;
    }


    public int getImage() {
        return image;
    }


    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public int getDecoration() {
        return decoration;
    }

}
