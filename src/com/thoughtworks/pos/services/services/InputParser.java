package com.thoughtworks.pos.services.services;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.pos.domains.Item;
import com.thoughtworks.pos.domains.ShoppingChart;
import com.thoughtworks.pos.domains.VIPdb;
import com.thoughtworks.pos.domains.idb;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;


/**
 * Created by Administrator on 2015/1/2.
 */
public class InputParser {
    private File indexFile;
    private File itemsFile;
    private File vipFile;
    private final ObjectMapper objectMapper;
    String VIPstr;

    public InputParser(File indexFile, File itemsFile,File vipFile) {
        this.indexFile = indexFile;
        this.itemsFile = itemsFile;
        this.vipFile = vipFile;


        objectMapper = new ObjectMapper(new JsonFactory());
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }

    public ShoppingChart parser() throws IOException {


        return BuildShoppingChart(getItemIndexes(),getVIP(),getBoughtItemBarCodes());
    }



    private ShoppingChart BuildShoppingChart(HashMap<String, Item> itemIndexes, HashMap<String, VIPdb> VIP,String[] barCodes) {
        ShoppingChart shoppingChart = new ShoppingChart();

        VIPdb vipdb=VIP.get(VIPstr);

        boolean isvip=vipdb.getisVip() ;
        shoppingChart.setisvip(isvip);
        shoppingChart.setVIPstr(VIPstr);


        if(isvip==true)
            for (String barcode : barCodes){

                Item mappedItem = itemIndexes.get(barcode);

                Item item = new Item(barcode, mappedItem.getName(), mappedItem.getUnit(), mappedItem.getPrice(), mappedItem.getDiscount(),mappedItem.getVipdiscount(), mappedItem.getPromotion());

                shoppingChart.add(item);
            }
        else
            for (String barcode : barCodes) {

                Item mappedItem = itemIndexes.get(barcode);

                Item item = new Item(barcode, mappedItem.getName(), mappedItem.getUnit(), mappedItem.getPrice(), mappedItem.getDiscount(),1.00, mappedItem.getPromotion());

                shoppingChart.add(item);
            }
        return shoppingChart;
    }

    private String[] getBoughtItemBarCodes() throws IOException {

        String iteamsStr = FileUtils.readFileToString(itemsFile);
        //System.out.println(iteamsStr.length());
        VIPstr = iteamsStr.substring(14,22);
        //System.out.println(VIPstr);
        String it=iteamsStr.substring(37,iteamsStr.length()-5);
        //System.out.println(it);
        return objectMapper.readValue(it, String[].class);
    }


    private HashMap<String, Item> getItemIndexes() throws IOException {
        String itemsIndexStr = FileUtils.readFileToString(indexFile);
        TypeReference<HashMap<String,Item>> typeRef = new TypeReference<HashMap<String,Item>>() {};
        return objectMapper.readValue(itemsIndexStr, typeRef);
    }

    private HashMap<String, VIPdb> getVIP() throws IOException {
        String VIPStr = FileUtils.readFileToString(vipFile);
        TypeReference<HashMap<String,VIPdb>> typeRef = new TypeReference<HashMap<String,VIPdb>>() {};
        return objectMapper.readValue(VIPStr, typeRef);

    }




}
