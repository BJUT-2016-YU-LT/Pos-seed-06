package com.thoughtworks.pos.bin;

import com.thoughtworks.pos.common.EmptyShoppingCartException;
import com.thoughtworks.pos.domains.Pos;
import com.thoughtworks.pos.domains.ShoppingChart;
import com.thoughtworks.pos.domains.VIPdb;
import com.thoughtworks.pos.services.services.InputParser;
import com.thoughtworks.pos.domains.Item;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Administrator on 2015/1/2.
 */
public class PosCLI {



    public static void main (String args[]) throws IOException, EmptyShoppingCartException {

        InputParser inputParser = new InputParser(new File(args[0]), new File(args[1]),new File(args[2]));

        ShoppingChart shoppingChart = inputParser.parser();






        Pos pos = new Pos();
        String shoppingList = pos.getShoppingList(shoppingChart);
        System.out.print(shoppingList);
    }
}
