package com.luxoft.cjp.unrelated;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by pamajcher on 2015-06-30.
 */
public class UrlDecomposerTest {


    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    /*
    one test - one reason to fail
    Fast, Independent, Repeatable, Self-validating, Timely
     */
    @Test
    public void emptyStringShouldReturnNothing(){}
    @Test
    public void extractProtocol(){}
    @Test
    public void extractDomain(){}
    @Test
    public void extractPath(){}
    @Test
    public void nonUrlShouldReturnNothing(){}
}