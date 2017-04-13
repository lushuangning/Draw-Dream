package cuit.drawdream.model.service.cookie;


import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

import java.util.List;


/**
 * Created by hus on 04/12/2016.
 */
public class CookiesManager implements CookieJar {
    private final MemoryCookieStore cookieStore = new MemoryCookieStore();
    private static CookiesManager manager = null;

    public static synchronized CookiesManager getManager(){
        if(null == manager){
            manager = new CookiesManager();
        }
        return manager;
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if ((cookies != null) && (cookies.size() > 0)) {
            for (Cookie item : cookies) {
                cookieStore.add(url, item);
            }
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {

        return cookieStore.get(url);
    }
}