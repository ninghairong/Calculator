package com.nhr.calculator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        Tool cal = new Tool();

        System.out.println("结果为"+cal.calculate("25"));

    }
    @Test
    public void replace(){
       // System.out.println(ArithHelper.processSqrt("(0-2)s9"));
        //System.out.println(ArithHelper.NP("3+3"));
        System.out.println(ArithHelper.NP("33+(0-133)+(0-133)+(0-133)"));
    }
}