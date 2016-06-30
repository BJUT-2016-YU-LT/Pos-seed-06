package com.thoughtworks.pos.domains;

import java.util.ArrayList;

/**
 * Created by Administrator on 2014/12/28.
 */
public class ShoppingChart {
    private ArrayList<Item> items = new ArrayList<Item>();
    private boolean isvip ;
    private String VIPstr;

    public String getVIPstr() {
        return VIPstr;
    }
    public boolean getisvip() {
        return isvip;
    }
    public void setVIPstr(String VIPstr) {
        this.VIPstr = VIPstr;
    }
    public void setisvip(boolean isvip) {
        this.isvip = isvip;
    }

    public void add(Item item) {
     this.items.add(item);

    }



    public ArrayList<Item> getItems() {
        return items;
    }


}
