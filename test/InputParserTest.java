/**
 * Created by Administrator on 2015/1/2.
 */

import com.thoughtworks.pos.domains.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.thoughtworks.pos.services.services.InputParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class InputParserTest {

    private File indexFile;
    private File itemsFile;
    private File VIPFile;

    @Before
    public void setUp() throws Exception {
        indexFile = new File("./sampleIndex.json");
        itemsFile = new File("./itemsFile.json");
        VIPFile = new File("./VIPFile.json");
    }

    @After
    public void tearDown() throws Exception {
        if(indexFile.exists()){
            indexFile.delete();
        }
        if(itemsFile.exists()){
            itemsFile.delete();
        }
        if(VIPFile.exists()){
            VIPFile.delete();
        }

    }

    @Test
    public void testParseJsonFileToItems() throws Exception {
        String sampleIndex = new StringBuilder()
                .append("{\n")
                .append("'ITEM000004':{\n")
                .append("\"name\": '电池',\n")
                .append("\"unit\": '个',\n")
                .append("\"price\": 2.00,\n")
                .append("\"discount\": 0.8,\n")
                .append("\"vipdiscount\": 0.9\n")
                .append("}\n")
                .append("}\n")
                .toString();
        WriteToFile(indexFile, sampleIndex);

        String sampleItems = new StringBuilder()
                .append("{\n" +
                        "   \"user\": 'USER0001',\n" +
                        "    \"items\": [\n" +
                        "     'ITEM000004',\n" +
                        "     'ITEM000004',\n" +
                        "     'ITEM000004',\n" +
                        "     'ITEM000004',\n" +
                        "     'ITEM000004',\n" +
                        "     'ITEM000004',\n" +
                        "     'ITEM000004',\n" +
                        "     'ITEM000004'\n" +
                        " ]    \n" +
                        "}" )

                .toString();
        WriteToFile(itemsFile, sampleItems);

        String sampleVIP = new StringBuilder()
                .append("{\n" +
                        "  'USER0001': {\n" +
                        "    \"name\": 'USER 001',\n" +
                        "    \"isVip\": true,\n" +
                        "    \"points\":700\n" +
                        "  },\n" +
                        "  'USER0002': {\n" +
                        "    \"name\": 'USER 002',\n" +
                        "    \"isVip\": false\n" +
                        "  },\n" +
                        "  'USER0003': {\n" +
                        "    \"name\": 'USER 003',\n" +
                        "    \"isVip\": true,\n" +
                        "    \"points\":500\n" +
                        "  }\n" +
                        "}")
                .toString();
        WriteToFile(VIPFile, sampleVIP);


        InputParser inputParser = new InputParser(indexFile, itemsFile,VIPFile);
        ArrayList<Item> items = inputParser.parser().getItems();

        assertThat(items.size(), is(8));
        Item item = items.get(0);
        assertThat(item.getName(), is("电池"));
        assertThat(item.getBarcode(), is("ITEM000004"));
        assertThat(item.getUnit(), is("个"));
        assertThat(item.getPrice(), is(2.00));
        assertThat(item.getDiscount(), is(0.8));
        assertThat(item.getVipdiscount(), is(0.9));
    }

    private void WriteToFile(File file, String content) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.write(content);
        printWriter.close();
    }


}
