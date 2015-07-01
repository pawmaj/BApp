package com.luxoft.cjp.unrelated;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by pamajcher on 2015-06-30.
 */
public class UrlDecomposer {

    public String[] decompose(String url) throws MalformedURLException, IllegalArgumentException{
        checkInput(url);
        URL urlObj = new URL(url);

        String[] result =null;
        result[0] = urlObj.getProtocol();
        result[1] = urlObj.getHost();
        result[2] = urlObj.getPath();
        return result;
    }

    private void checkInput(String url) throws IllegalArgumentException {
        if (url==null) throw new IllegalArgumentException(url);
        if (url.equals("")) throw new IllegalArgumentException(url);
    }


}
