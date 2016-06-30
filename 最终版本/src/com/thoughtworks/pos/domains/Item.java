package com.thoughtworks.pos.domains;

import com.thoughtworks.pos.bin.PosCLI;
import com.thoughtworks.pos.domains.VIPdb;
import java.util.Scanner;

/**
 * Created by Administrator on 2014/12/28.
 */
public class Item {
    private String barcode;
    private String name;
    private String unit;
    private double price;
    private double discount;
    private double Vipdiscount;
    private boolean promotion;




    public Item() {
    }





    public Item(String barcode, String name, String unit, double price) {

        this.setBarcode(barcode);
        this.setName(name);
        this.setUnit(unit);
        this.setPrice(price);
    }

    public Item(String barcode, String name, String unit, double price, double discount, double Vipdiscount) {
        this(barcode, name, unit, price);
        this.setDiscount(discount);
        this.setVipdiscount(Vipdiscount);
    }


    public Item(String barcode, String name, String unit, double price,double discount, double Vipdiscount,boolean promotion) {
        this(barcode, name, unit, price);
        if(discount==1.00&&Vipdiscount==1.00) this.setPromotion(promotion);
        else {this.setDiscount(discount);
            this.setVipdiscount(Vipdiscount);
        }//同时存在优惠和打折时默认按照打折计算

    }



    public String getName() {
        return name;
    }
    public String getUnit() {
        return unit;
    }



    public double getPrice() {
        return price;
    }

    public String getBarcode() { return barcode; }

    public double getDiscount() {
      if(discount==0.00)
      {
          return 1.00;
      }
      return discount;
    }
    public boolean getPromotion(){
        return promotion;
    }

    public double getVipdiscount(){
        if(Vipdiscount==0.00)
        {
            return 1.00;
        }
        return Vipdiscount;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDiscount(double discount) {this.discount = discount;}

    public void setPromotion(boolean promotion){this.promotion=promotion;}

    public void setVipdiscount(double Vipdiscount) {this.Vipdiscount = Vipdiscount;}

}
