package com.luxoft.cjp.unrelated;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;

import static org.junit.Assert.*;
    /*
    one test - one reason to fail, małe kroki, cykliczne: red, green, refactor
    Fast, Independent, Repeatable, Self-validating, Timely
    Po kolei:
    1.Pierwszy najprostszy przypadek i programujemy od najprostszego do najbardziej pokręconego przypadku
    2.W bdd każdy test niesie jakieś przesłanie biznesowe - czy funkcjonalność biznesowa działa?
    Tomasz Żądło Mariusz Balawajder
     */
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

    @Test
    public void shouldExtractProtocol() throws MalformedURLException {
        UrlDecomposer urlDecomposer = new UrlDecomposer();
        assert(true);
    }
    @Test
    public void shouldExtractDomain(){
   // SplittedUrl splittedUrl = new SplittedUrl();
    }
    @Test
    public void shouldExtractPath(){}
    @Test
    public void shouldThrowExceptionIfMalformed(){}
    @Test
    public void shouldThrowExceptionIfEmptyScreen(){}
}