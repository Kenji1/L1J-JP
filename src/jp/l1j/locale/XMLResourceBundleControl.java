/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.l1j.locale;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class XMLResourceBundleControl extends ResourceBundle.Control {

	public List<String> getFormats(String baseName) {
		if (baseName == null) {
			throw new NullPointerException();
		}
		return Arrays.asList("xml");
	}
	
	public ResourceBundle newBundle(String baseName,
			Locale locale,
			String format,
			ClassLoader loader,
			boolean reload)
			throws IllegalAccessException,
			InstantiationException,
			IOException {
		if (baseName == null || locale == null || format == null || loader == null) {
			throw new NullPointerException();
		}
		ResourceBundle bundle = null;
		if (format.equals("xml")) {
			String bundleName = toBundleName(baseName, locale);
			String resourceName = toResourceName(bundleName, format);
			/*
			InputStream stream = null;
			if (reload) {
				URL url = loader.getResource(resourceName);
				if (url != null) {
					URLConnection connection = url.openConnection();
					if (connection != null) {
						// キャッシュを無効にすることで、再ロード用の最新データを
						// 取得できるようにします。
						connection.setUseCaches(false);
						stream = connection.getInputStream();
					}
				}
			} else {
				stream = loader.getResourceAsStream(resourceName);
			}
			*/
			
			// TODO start
			File file = new File(resourceName.replace("//", "./"));
			InputStream stream = new FileInputStream(file);
			// TODO end
			if (stream != null) {
				BufferedInputStream bis = new BufferedInputStream(stream);
				bundle = new XMLResourceBundle(bis);
				bis.close();
			}
		}
		return bundle;
	}

}
