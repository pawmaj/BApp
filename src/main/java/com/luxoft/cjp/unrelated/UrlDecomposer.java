package com.luxoft.cjp.unrelated;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by pamajcher on 2015-06-30.
 */
public class UrlDecomposer {
    public SplittedUrl getSplittedUrl(String url) throws MalformedURLException, IllegalArgumentException{
        checkInput(url);
        URL urlObj = new URL(url);
        return new SplittedUrl(decompose(urlObj)[0],decompose(urlObj)[1],decompose(urlObj)[2]);
    }

    private String[] decompose(URL url) {
        String[] result = {"","",""};
        result[0] = url.getProtocol();
        result[1] = url.getHost();
        result[2] = url.getPath();
        return result;
    }

    private void checkInput(String url) throws IllegalArgumentException {
        if (url==null) throw new IllegalArgumentException(url);
        if (url.equals("")) throw new IllegalArgumentException(url);
    }


}
