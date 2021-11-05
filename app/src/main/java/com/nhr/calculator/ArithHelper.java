package com.nhr.calculator;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ArithHelper {

    // 默认除法运算精度
    private static final int DEF_DIV_SCALE = 16;

    // 这个类不能实例化
    private ArithHelper() {
    }

    /**
     * 提供精确的加法运算。
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */


    public static double add(String v1, String v2) {
        java.math.BigDecimal b1 = new java.math.BigDecimal(v1);
        java.math.BigDecimal b2 = new java.math.BigDecimal(v2);
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */


    public static double sub(String v1, String v2) {
        java.math.BigDecimal b1 = new java.math.BigDecimal(v1);
        java.math.BigDecimal b2 = new java.math.BigDecimal(v2);
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */


    public static double mul(String v1, String v2) {
        java.math.BigDecimal b1 = new java.math.BigDecimal(v1);
        java.math.BigDecimal b2 = new java.math.BigDecimal(v2);
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */


    public static double div(String v1, String v2) {
        java.math.BigDecimal b1 = new java.math.BigDecimal(v1);
        java.math.BigDecimal b2 = new java.math.BigDecimal(v2);
        return b1.divide(b2, DEF_DIV_SCALE, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    public static boolean findMinDistance(String expression) {
        int length = expression.length();
        int flag = 0;
        for (int i = length - 1; i > 0; i--) {
            if (expression.charAt(i) == '+' || expression.charAt(i) == '-' || expression.charAt(i) == '*' || expression.charAt(i) == '/') {
                flag = i;
                break;
            }
        }
        for (int i = flag; i < length; i++) {
            if (expression.charAt(i) == '.')
                return false;
        }
        return true;
    }

    public static String NP(String expression) {
        System.out.println(expression);
        int length = expression.length();
        int flag = 0;
        for (int i = length - 1; i > 0; i--) {
            if (expression.charAt(i) == '+' || expression.charAt(i) == '-' || expression.charAt(i) == '*' || expression.charAt(i) == '/') {
                flag = i;
                break;
            }
        }

        String temp = "";
        if(expression.contains("(")){
            temp = expression.substring(flag-2);
            System.out.println(temp);
        }

        if (temp.contains("(")) {
            //  Log.e("TAG", "NP: if"+expression.substring(0,flag)+expression.substring(flag+4,length-1));
            System.out.println("if" + expression.substring(0, flag - 2) + expression.substring(flag + 1, length - 1));
            return expression.substring(0, flag - 2) + expression.substring(flag + 1, length - 1);
        }
        if(flag==0){
            return "(0-"+expression+")";
        }
        //System.out.println(expression + "  flag:" + flag);
        //System.out.println("flag:" + expression.charAt(flag - 2));
//        Log.e("TAG", "NP: "+expression.substring(0,flag)+"(0-"+expression.substring(flag+1,length)+")");
        return expression.substring(0, flag + 1) + "(0-" + expression.substring(flag + 1, length) + ")";

    }

    public static String processSqrt(String expression) {
        StringBuffer result;
        StringBuffer temp = new StringBuffer(expression);
        int star = -1;
        int end = -1;
        for (int i = 0; i < temp.toString().length(); i++) {
            if (temp.toString().charAt(i) == 's') {
                if (i != 0 && (temp.toString().charAt(i - 1) == '0' ||
                        temp.toString().charAt(i - 1) == '1' ||
                        temp.toString().charAt(i - 1) == '2' ||
                        temp.toString().charAt(i - 1) == '3' ||
                        temp.toString().charAt(i - 1) == '4' ||
                        temp.toString().charAt(i - 1) == '5' ||
                        temp.toString().charAt(i - 1) == '6' ||
                        temp.toString().charAt(i - 1) == '7' ||
                        temp.toString().charAt(i - 1) == '8' ||
                        temp.toString().charAt(i - 1) == '9'
                        ||
                        temp.toString().charAt(i - 1) == ')')) {
                    temp.insert(i, '*');
                }
            }
        }
        result = temp;
        // System.out.println(result);
        for (int i = 0; i < temp.toString().length(); i++) {

            if (temp.toString().charAt(i) == 's') {
                star = i;
                continue;
            }
            if (star != -1 && (temp.toString().charAt(i) == '+' ||
                    temp.toString().charAt(i) == '-' ||
                    temp.toString().charAt(i) == '*' ||
                    temp.toString().charAt(i) == '/'
                    ||
                    i == temp.toString().length() - 1)) {
                end = i;
                if (i == temp.toString().length() - 1)
                    end++;
            }
            if (end != -1) {
                result.replace(star, end, "" + Math.sqrt(Double.parseDouble(temp.toString().substring(star + 1, end))));
                star = -1;
                end = -1;
            }

        }

        return result.toString();
    }
}