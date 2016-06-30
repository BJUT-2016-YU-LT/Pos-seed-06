package com.thoughtworks.pos.services.services;

import com.thoughtworks.pos.common.EmptyShoppingCartException;
import com.thoughtworks.pos.domains.Item;
import com.thoughtworks.pos.domains.Pos;
import com.thoughtworks.pos.domains.ShoppingChart;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by pc on 2016/6/29.
 */
public class test02 {
    @Test
    public void testShouldSupportDiscountWhenHavingOneFavourableItem() throws EmptyShoppingCartException {
        // given
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000001", "雪碧", "瓶", 2.00, 0.8));

        // when
        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

        // then
        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "打印时间："+df.format(new Date())+"\n"
                        + "名称：雪碧，数量：1瓶，单价：2.00(元)，小计：1.60(元)\n"
                        + "----------------------\n"
                        + "总计：1.60(元)\n"
                        + "节省：0.40(元)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }
    @Test
    public void testShouldSupportDiscountWhenHavingTwoFavourableItem() throws EmptyShoppingCartException {
        // given
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000001", "雪碧", "瓶", 2.00, 0.8));
        shoppingChart.add(new Item("ITEM000001", "雪碧", "瓶", 2.00, 0.8));

        // when
        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

        // then
        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "打印时间："+df.format(new Date())+"\n"
                        + "名称：雪碧，数量：2瓶，单价：2.00(元)，小计：3.20(元)\n"
                        + "----------------------\n"
                        + "总计：3.20(元)\n"
                        + "节省：0.80(元)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }
    @Test
    public void testShouldSupportDiscountWhenHavingMultipleItem() throws EmptyShoppingCartException {
        // given
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000001", "雪碧", "瓶", 2.00, 0.8));
        shoppingChart.add(new Item("ITEM000001", "雪碧", "瓶", 2.00, 0.8));
        shoppingChart.add(new Item("ITEM000000", "可口可乐", "瓶", 2.00,0.8));
        shoppingChart.add(new Item("ITEM000004","电池","个",3.00,0.7));
        shoppingChart.add(new Item("ITEM000004","电池","个",3.00,0.7));
        shoppingChart.add(new Item("ITEM000004","电池","个",3.00,0.7));

        // when
        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

        // then
        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "打印时间："+df.format(new Date())+"\n"
                        + "名称：雪碧，数量：2瓶，单价：2.00(元)，小计：3.20(元)\n"
                        + "名称：可口可乐，数量：1瓶，单价：2.00(元)，小计：1.60(元)\n"
                        + "名称：电池，数量：3个，单价：3.00(元)，小计：6.30(元)\n"
                        + "----------------------\n"
                        + "总计：11.10(元)\n"
                        + "节省：3.90(元)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }

}
