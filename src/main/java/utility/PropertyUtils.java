package utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtils {
    /**+
     * Gets the property value of the key present in ui.properties
     * @param key contains the key for which its value has to be retrieved
     * @return String which is the value of the key
     */
    public static String getProperty(String key)
    {
        Properties props=new Properties();
        InputStream is=ClassLoader.getSystemResourceAsStream("properties/ui/ui.properties");
        try
        {
            props.load(is);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        if((System.getProperty(key)!=null)&&(!System.getProperty(key).isEmpty()))
        {
            return System.getProperty(key);
        }
        else
        {
            return props.getProperty(key);
        }
    }
}
