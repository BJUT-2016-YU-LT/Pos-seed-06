package com.thoughtworks.pos.domains;

import java.util.ArrayList;

/**
 * Created by Administrator on 2014/12/28.
 */
public class ShoppingChart {
    private ArrayList<Item> items = new ArrayList<Item>();
    private boolean isvip ;
    private String VIPstr;
    private String name;
    private int points;

    public String getVIPstr() {
        return VIPstr;
    }
    public boolean getisvip() {
        return isvip;
    }
    public int getPoints(){
        return points;
    }
    public int getPoint(){
        if(points>=0&&points<=200)
        {
            return 1;
        }
        else if(points>200&points<=500)
        {
            return 3;
        }
        else return 5;

    }
    public String getName(){return name;}
    public void setVIPstr(String VIPstr) {
        this.VIPstr = VIPstr;
    }
    public void setisvip(boolean isvip) {
        this.isvip = isvip;
    }
    public void setName(String name){this.name = name;}

    public void setPoints(int points) {this.points = points;}

    public void add(Item item) {
     this.items.add(item);

    }



    public ArrayList<Item> getItems() {
        return items;
    }


}
