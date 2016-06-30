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
 * Created by Administrator on 2014/12/28.
 */
public class PosTest {

    @Test
    public void testGetCorrectShoppingListWhenDifferentItemHaveSameItemName() throws  Exception{
        // given
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000", "雪碧", "瓶", 2.00));
        shoppingChart.add(new Item("ITEM000002", "雪碧", "瓶", 3.00));
        shoppingChart.setisvip(false);
        shoppingChart.setVIPstr("USER0001");
        // when
        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        // then
        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "打印时间："
                        +df.format(new Date())
                        + "\n名称：雪碧，数量：1瓶，单价：2.00(元)，小计：2.00(元)\n"
                        + "名称：雪碧，数量：1瓶，单价：3.00(元)，小计：3.00(元)\n"
                        + "----------------------\n"
                        + "总计：5.00(元)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }

    @Test(expected = EmptyShoppingCartException.class)
    public void testThrowExceptionWhenNoItemsInShoppingCart() throws EmptyShoppingCartException{
        // given
        ShoppingChart shoppingChart = new ShoppingChart();

        // when
        Pos pos = new Pos();
        pos.getShoppingList(shoppingChart);
    }

    @Test
    public void testShouldSupportDiscountWhenHavingOneFavourableItem() throws EmptyShoppingCartException {
        // given
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000", "雪碧", "瓶", 2.00, 0.8,0.9));
        shoppingChart.setisvip(true);
        shoppingChart.setVIPstr("USER0001");
        // when
        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        // then
        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "打印时间："
                        +df.format(new Date())
                        + "\n名称：雪碧，数量：1瓶，单价：2.00(元)，小计：1.44(元)\n"
                        + "----------------------\n"
                        + "总计：1.44(元)\n"
                        + "节省：0.56(元)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }
}