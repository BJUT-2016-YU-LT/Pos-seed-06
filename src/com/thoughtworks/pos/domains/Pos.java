package com.thoughtworks.pos.domains;

import com.thoughtworks.pos.common.EmptyShoppingCartException;
import com.thoughtworks.pos.services.services.ReportDataGenerator;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2014/12/28.
 */
public class Pos {
    public String getShoppingList(ShoppingChart shoppingChart) throws EmptyShoppingCartException {

        Report report = new ReportDataGenerator(shoppingChart).generate();
        int a = (int)report.getTotal();
        a=a/5;
        int b = shoppingChart.getPoint()*a;


        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式



            StringBuilder shoppingListBuilder = new StringBuilder()

                    .append("***商店购物清单***\n");
        if(shoppingChart.getisvip()==true)
        {

            shoppingListBuilder

                    .append("会员编号：").append(shoppingChart.getVIPstr()).append(" ")
                    .append("会员积分:").append(b).append("\n")
                    .append("----------------------\n");




        }
        shoppingListBuilder
                    .append("打印时间：")
                    .append(df.format(new Date()))// new Date()为获取当前系统时间
                    .append("\n");

        for (ItemGroup itemGroup : report.getItemGroupies()) {

            shoppingListBuilder.append(
                    new StringBuilder()
                            .append("名称：").append(itemGroup.groupName()).append("，")
                            .append("数量：").append(itemGroup.groupSize()).append(itemGroup.groupUnit()).append("，")
                            .append("单价：").append(String.format("%.2f", itemGroup.groupPrice())).append("(元)").append("，")
                            .append("小计：").append(String.format("%.2f", itemGroup.subTotal())).append("(元)").append("\n")
                            .toString());
        }
        StringBuilder subStringBuilder = shoppingListBuilder;
        boolean promotion = report.getPromotion();

        if (promotion == true) {
            subStringBuilder
                    .append("----------------------\n")
                    .append("挥泪赠送商品：\n");
            for (ItemGroup itemGroup : report.getItemGroupies()) {
                if(itemGroup.promotion()==true)
                    subStringBuilder.append(
                    new StringBuilder()
                        .append("名称：").append(itemGroup.groupName()).append("，")
                        .append("数量：").append("1").append(itemGroup.groupUnit()).append("\n"));

            }
        }
         subStringBuilder
                .append("----------------------\n")
                .append("总计：").append(String.format("%.2f", report.getTotal())).append("(元)").append("\n");

        double saving = report.getSaving();


        if (saving == 0) {
            return subStringBuilder

                    .append("**********************\n")
                    .toString();
        }
        return subStringBuilder

                .append("节省：").append(String.format("%.2f", saving)).append("(元)").append("\n")
                .append("**********************\n")
                .toString();
    }
}
