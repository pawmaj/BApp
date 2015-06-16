package com.luxoft.cjp.model;

import org.junit.Test;

/**
 * Created by pamajcher on 2015-05-29.
 */
public class BankClientTest {

    @Test
    public void clientEqualsMustBeReflexive() throws Exception {
        Client c1 = new Client("Aldona",0,"aldona@al.pl","578556444","Warszawa","f");
        if (c1.equals(c1)==false) throw new Exception();

    }
}

