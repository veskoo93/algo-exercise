package com.rqpa.algo.text;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IntegerToWordsTransformerTestCase
{
    @Test
    public void test0()
    {
        doTest(0, "zero");
    }

    @Test
    public void test1()
    {
        doTest(1, "one");
    }

    @Test
    public void test10()
    {
        doTest(10, "ten");
    }

    @Test
    public void test13()
    {
        doTest(13, "thirteen");
    }

    @Test
    public void test30()
    {
        doTest(30, "thirty");
    }

    @Test
    public void test42()
    {
        doTest(42, "forty-two");
    }

    @Test
    public void test100()
    {
        doTest(100, "one hundred");
    }

    @Test
    public void test115()
    {
        doTest(115, "one hundred and fifteen");
    }

    private void doTest(int number, String expectedString)
    {
        Assertions.assertEquals(expectedString, IntegerToWordsTransformer.instance.toWords(number));
    }
}
