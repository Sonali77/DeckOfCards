import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import utility.HttpUtil;
import utility.PropertyUtils;

import java.util.HashMap;
import java.util.Map;

public class TestDeckOfCards {

    //Create a new Deck of cards and support adding joker with a POST
    @Test
    /**+
     * This method is used to create a new deck of cards and support using joker with a post
     */
    public void createANewDeckCardAndPostJoker() {
        try {
            // GET request
            String url1 = PropertyUtils.getProperty("url");
            JSONObject resp1 = HttpUtil.get(url1);
            System.out.println(resp1.getString("deck_id"));
            System.out.println();
            if((resp1.getString("success")).equals("true"))
            {
                System.out.println("GET a new deck of card status is successful for URL" + url1);
                Assert.assertTrue("true", Boolean.parseBoolean((resp1.getString("success"))));
            }
            else
            {
                System.out.println("GET a new deck of card status is not successful for URl" + url1);
                Assert.assertFalse("false", Boolean.parseBoolean((resp1.getString("success"))));
            }

            // Append query parameters to url
            String url = url1 + "post";
            Map<String, String> params = new HashMap<String, String>();
            //reading from ui.properties-<key,value>
            String key = PropertyUtils.getProperty("post.key");
            String value = PropertyUtils.getProperty("post.value");
            params.put(key, value);
            String fullUrl = HttpUtil.appendQueryParams(url, params);
            System.out.println("Sending 'POST' request to URL="+url1);
            System.out.println("fullUrl="+ fullUrl);
            System.out.println();

            // Extract query parameters from url
            Map<String, String> params2 = HttpUtil.getQueryParams(fullUrl);
            for (String param : params2.keySet()) {
                //System.out.println(param + ":" + params2.get(param));
                if((param.equals(key))&&(params2.get(param).equals(value)))
                {
                    System.out.println(param + ":" + params2.get(param)+" is present");
                    Assert.assertEquals(param,key);
                    Assert.assertEquals(params2.get(param),value);
                }
                else
                {
                    System.out.println(param + ":" + params2.get(param)+" is not present");
                }

            }
            System.out.println();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
