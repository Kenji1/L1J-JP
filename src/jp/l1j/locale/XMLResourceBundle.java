/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.l1j.locale;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

public class XMLResourceBundle extends ResourceBundle {
    private Properties properties;
    
    public XMLResourceBundle(InputStream stream) throws IOException {
        // Propertiesクラスを使用してXMLファイルを読み込む
        properties = new Properties();
        properties.loadFromXML(stream);
     }

    public Object handleGetObject(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        return properties.get(key);
    }

    @SuppressWarnings("unchecked")
    public Enumeration<String> getKeys() {
        return (Enumeration<String>)properties.propertyNames();
    }
}
