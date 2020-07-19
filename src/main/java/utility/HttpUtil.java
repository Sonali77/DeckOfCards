package utility;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class HttpUtil {
    /**+
     * Gets the JsonObject of the URL
     * @param url URL from which the objects has to be read
     * @return JSONObject from which each object has to be retrieved
     * @throws Exception
     */
    public static JSONObject get(String url) throws Exception
    {
        //String url="https://deckofcardsapi.com/api/deck/new/";
        URL obj=new URL(url);
        HttpURLConnection con= (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent","Mozilla15.0");
        int responseCode=con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL=" +url);
        System.out.println("Response Code=" +responseCode);
        BufferedReader in=new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response=new StringBuffer();
        while((inputLine=in.readLine())!=null)
        {
            response.append(inputLine);
        }
        in.close();
        System.out.println(response.toString());
        JSONObject myResponse=new JSONObject(response.toString());
        //System.out.println(myResponse.getString("deck_id"));
        return myResponse;
    }

    /**+
     * Get query Params
     * @param url URL from where params had to be retrieved
     * @return Map
     * @throws IOException
     */
    static public Map<String, String> getQueryParams(String url)
            throws IOException {
        Map<String, String> params = new HashMap<String, String>();

        int start = url.indexOf('?');
        while (start != -1) {
            // read parameter name
            int equals = url.indexOf('=', start);
            String param = "";
            if (equals != -1) {
                param = url.substring(start + 1, equals);
            }
            else {
                param = url.substring(start + 1);
            }

            // read parameter value
            String value = "";
            if (equals != -1) {
                start = url.indexOf('&', equals);
                if (start != -1) {
                    value = url.substring(equals + 1, start);
                }
                else {
                    value = url.substring(equals + 1);
                }
            }

            params.put(URLDecoder.decode(param, "UTF-8"),
                    URLDecoder.decode(value, "UTF-8"));
        }

        return params;
    }

    /**
     * Returns the url without query parameters
     * @param url         Url containing query parameters
     * @return url        Url without query parameters
     * @throws IOException
     */
    static public String removeQueryParams(String url)
            throws IOException {
        int q = url.indexOf('?');
        if (q != -1) {
            return url.substring(0, q);
        }
        else {
            return url;
        }
    }

    /**+
     * returns the url after appending params
     * @param url Initial URL
     * @param params Parameters to be added
     * @return String which is the new full URL
     * @throws IOException
     */
     public static String appendQueryParams(String url,
                                           Map<String, String> params) throws IOException {
        String fullUrl = url;
        if (params != null) {
            boolean first = (fullUrl.indexOf('?') == -1);
            for (String param : params.keySet()) {
                if (first) {
                    fullUrl += '?';
                    first = false;
                }
                else {
                    fullUrl += '&';
                }
                String value = params.get(param);
                fullUrl += URLEncoder.encode(param, "UTF-8") + '=';
                fullUrl += URLEncoder.encode(value, "UTF-8");
            }
        }

        return fullUrl;
    }
}
