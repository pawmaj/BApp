package com.luxoft.cjp.unrelated;

/**
 * Created by pamajcher on 2015-07-02.
 */
public class SplittedUrl {
    private final String protocol;

    private final String host;

    private final String path;

    public SplittedUrl(String protocol, String host, String path) {
        this.protocol = protocol;
        this.host = host;
        this.path = path;
    }


    public String getProtocol() {
        return protocol;
    }

    public String getHost() {
        return host;
    }

    public String getPath() {
        return path;
    }
}
