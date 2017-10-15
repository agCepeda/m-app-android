package com.meisshi.meisshi.model;

/**
 * Created by DevAg on 04/09/2017.
 */

public class CardField {

    private int x;
    private int y;

    private int width;
    private int height;

    private String name;
    private String color;
    private int fontSize;
    private String align;
    private String style;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public int getFontSize() {
        return fontSize;
    }


    public void setAlign(String align) {
        this.align = align;
    }

    public String getAlign() {
        return align;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getStyle() {
        return style;
    }

    public void setName(String name) {
        this.name = name;
    }
}
