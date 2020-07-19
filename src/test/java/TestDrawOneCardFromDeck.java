import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import utility.HttpUtil;
import utility.PropertyUtils;

public class TestDrawOneCardFromDeck {

    //Draw one or more cards from the Deck
    @Test
    /**+
     * Draw one or more cards from the Deck
     */
    public void test2() {
        try {
            String url3 = PropertyUtils.getProperty("url");
            JSONObject resp3 = HttpUtil.get(url3);
            System.out.println("Deck ID=" + resp3.getString("deck_id"));
            System.out.println();
            String url2 = "https://deckofcardsapi.com/api/deck/" + resp3.getString("deck_id") + "/draw/";
            System.out.println("New URL=" + url2);
            JSONObject resp2 = HttpUtil.get(url2);
            if((resp2.getString("success")).equals("true"))
            {
                System.out.println(resp3.getString("deck_id")+" present and status is successful");
                Assert.assertTrue("true", Boolean.parseBoolean((resp2.getString("success"))));

            }
            else
            {
                System.out.println(resp3.getString("deck_id")+" not present and status is not successful");
                Assert.assertFalse("false", Boolean.parseBoolean((resp2.getString("success"))));
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
