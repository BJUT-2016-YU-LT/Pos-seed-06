package com.thoughtworks.pos.domains;

import java.util.List;
import java.util.Scanner;


/**
 * Created by Administrator on 2014/12/31.
 */
public class Report{
    private List<ItemGroup> itemGroupies;

    public Report(List<ItemGroup> itemGroupies){
        this.itemGroupies = itemGroupies;
    }

    public List<ItemGroup> getItemGroupies() {
        return itemGroupies;
    }
    int a ;





    public double getTotal(){

        double result = 0.00;
        for (ItemGroup itemGroup : itemGroupies)
        { result += itemGroup.subTotal();}

        a=(int)result;

        return result;
    }
public int points(){

    return a;
}



    public double getSaving(){

        double result = 0.00;
        for (ItemGroup itemGroup : itemGroupies)
            result += itemGroup.saving();
        return result;
    }
    public boolean getPromotion(){
        boolean result = false;
        for (ItemGroup itemGroup : itemGroupies)
            if (itemGroup.promotion()==true) result = true;
        return result;

    }
}
