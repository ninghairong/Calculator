package com.nhr.calculator;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
    String expression;
    String forCompute;
    String resultString;
    TextView input;
    TextView result;

    Button clear;
    Button delete;
    Button percent;
    Button div;

    Button seven;
    Button eight;
    Button nine;
    Button multiply;

    Button four;
    Button five;
    Button six;
    Button sub;

    Button one;
    Button two;
    Button three;
    Button add;

    Button sqrt;
    Button zero;
    Button point;
    Button equal;

    Tool cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); //Enable自定义的View
            actionBar.setCustomView(R.layout.actionbar_custom);//设置自定义的布局：actionbar_custom
        }
        expression = "";
        resultString = "";
        forCompute = "";
        cal = new Tool();

        input = findViewById(R.id.input);
        result = findViewById(R.id.result);
        input.setMovementMethod(ScrollingMovementMethod.getInstance());
        input.setText(expression);
        result.setText(resultString);
        result.setTextSize(25);
        result.setText("长按\"-\"以切换正负数！");

        clear = findViewById(R.id.clear);
        delete = findViewById(R.id.delete);
        percent = findViewById(R.id.percent);
        div = findViewById(R.id.div);

        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        multiply = findViewById(R.id.multiply);

        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        sub = findViewById(R.id.sub);

        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        add = findViewById(R.id.add);

        sqrt = findViewById(R.id.sqrt);
        zero = findViewById(R.id.zero);
        point = findViewById(R.id.point);
        equal = findViewById(R.id.equal);

        clear.setOnClickListener(this);
        delete.setOnClickListener(this);
        percent.setOnClickListener(this);
        div.setOnClickListener(this);


        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        multiply.setOnClickListener(this);

        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        sub.setOnClickListener(this);
        sub.setOnLongClickListener(this);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        add.setOnClickListener(this);

        sqrt.setOnClickListener(this);
        zero.setOnClickListener(this);
        point.setOnClickListener(this);
        equal.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        result.setText("");

        result.setTextSize(50);

        switch (view.getId()) {
            case R.id.clear:
                expression = "";
                forCompute = "";
                input.setText(expression);
                result.setText(expression);
                break;
            case R.id.delete:
                if (expression.equals("")) {
                    Toast.makeText(this, "已全部清空！", Toast.LENGTH_SHORT).show();
                    input.setText("已清空");
                    break;
                }
                if (expression.charAt(expression.length() - 1) == '%') {
                    expression = expression.substring(0, expression.length() - 1);
                    forCompute = forCompute.substring(0, forCompute.length() - 4);
                    Log.e("TAG", "onClick: " + forCompute);
                    input.setText(expression);
                    break;
                }
                if (forCompute.charAt(forCompute.length() - 1) == ')') {
                    int flag = -1;
                    for (int i = forCompute.length() - 1; i >= 0; i--) {
                        if (forCompute.charAt(i) == '(') {
                            flag = i;
                            break;
                        }
                    }
                    expression = expression.substring(0, flag);
                    forCompute = forCompute.substring(0, flag);
                    input.setText(expression);
                    break;
                }
                expression = expression.substring(0, expression.length() - 1);
                forCompute = forCompute.substring(0, forCompute.length() - 1);
                input.setText(expression);
                break;
            case R.id.percent:
                if (forCompute.length() > 0 && forCompute.charAt(forCompute.length() - 1) == 's') {
                    Toast.makeText(this, "请先输入数字", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (expression.equals("") || forCompute.substring(forCompute.length() - 1).equals("-") ||
                        forCompute.substring(forCompute.length() - 1).equals("+") ||
                        forCompute.substring(forCompute.length() - 1).equals("*") ||
                        forCompute.substring(forCompute.length() - 1).equals("/") ||
                        forCompute.charAt(forCompute.length() - 1) == 's'
                ) {
                    Toast.makeText(this, "请输入正确的表达式", Toast.LENGTH_SHORT).show();
                    break;
                }
                expression += "%";
                forCompute = forCompute + "/100";
                Log.e("TAG", "onClick: " + forCompute);
                input.setText(expression);
                break;
            case R.id.div:
                if (forCompute.length() > 0 && forCompute.charAt(forCompute.length() - 1) == 's') {
                    Toast.makeText(this, "请先输入数字", Toast.LENGTH_SHORT).show();
                    break;
                }

                if (expression.equals("") || expression.equals(" ") || expression.equals("/n")) {
                    expression = "0÷";
                    forCompute = "0/";
                    input.setText(expression);
                    break;
                }
                if (forCompute.substring(forCompute.length() - 1).equals("-") ||
                        forCompute.substring(forCompute.length() - 1).equals("+") ||
                        forCompute.substring(forCompute.length() - 1).equals("*") ||
                        forCompute.substring(forCompute.length() - 1).equals("/") ||
                        forCompute.charAt(forCompute.length() - 1) == 's') {
                    expression = expression.substring(0, expression.length() - 1);
                    forCompute = forCompute.substring(0, forCompute.length() - 1);
                    expression += "÷";
                    input.setText(expression);
                    forCompute += "/";
                    break;
                }
                forCompute += "/";
                expression += "÷";
                input.setText(expression);
                break;
            case R.id.seven:
                if (forCompute.length() != 0 && (forCompute.charAt(forCompute.length() - 1) == ')'
                        || expression.charAt(expression.length() - 1) == '%')) {
                    Toast.makeText(this, "请先输入运算符", Toast.LENGTH_SHORT).show();
                    break;
                }
                forCompute += "7";
                expression += "7";
                input.setText(expression);
                break;
            case R.id.eight:
                if (forCompute.length() != 0 && (forCompute.charAt(forCompute.length() - 1) == ')'
                        || expression.charAt(expression.length() - 1) == '%')) {
                    Toast.makeText(this, "请先输入运算符", Toast.LENGTH_SHORT).show();
                    break;
                }
                forCompute += "8";
                expression += "8";
                input.setText(expression);

                break;
            case R.id.nine:
                if (forCompute.length() != 0 && (forCompute.charAt(forCompute.length() - 1) == ')'
                        || expression.charAt(expression.length() - 1) == '%')) {
                    Toast.makeText(this, "请先输入运算符", Toast.LENGTH_SHORT).show();
                    break;
                }
                forCompute += "9";
                expression += "9";
                input.setText(expression);

                break;
            case R.id.multiply:
                if (forCompute.length() > 0 && forCompute.charAt(forCompute.length() - 1) == 's') {
                    Toast.makeText(this, "请先输入数字", Toast.LENGTH_SHORT).show();
                    break;
                }

                if (expression.equals("") || expression.equals(" ") || expression.equals("/n")) {
                    expression = "0×";
                    forCompute = "0*";
                    input.setText(expression);
                    break;
                }
                if (forCompute.substring(forCompute.length() - 1).equals("-") ||
                        forCompute.substring(forCompute.length() - 1).equals("+") ||
                        forCompute.substring(forCompute.length() - 1).equals("*") ||
                        forCompute.substring(forCompute.length() - 1).equals("/") ||
                        forCompute.charAt(forCompute.length() - 1) == 's') {
                    expression = expression.substring(0, expression.length() - 1);
                    forCompute = forCompute.substring(0, forCompute.length() - 1);
                    expression += "×";
                    forCompute += "*";
                    input.setText(expression);
                    break;
                }
                forCompute += "*";
                expression += "×";
                input.setText(expression);

                break;
            case R.id.four:
                if (forCompute.length() != 0 && (forCompute.charAt(forCompute.length() - 1) == ')'
                        || expression.charAt(expression.length() - 1) == '%')) {
                    Toast.makeText(this, "请先输入运算符", Toast.LENGTH_SHORT).show();
                    break;
                }
                forCompute += "4";
                expression += "4";
                input.setText(expression);

                break;
            case R.id.five:
                if (forCompute.length() != 0 && (forCompute.charAt(forCompute.length() - 1) == ')'
                        || expression.charAt(expression.length() - 1) == '%')) {
                    Toast.makeText(this, "请先输入运算符", Toast.LENGTH_SHORT).show();
                    break;
                }
                forCompute += "5";
                expression += "5";
                input.setText(expression);

                break;
            case R.id.six:
                if (forCompute.length() != 0 && (forCompute.charAt(forCompute.length() - 1) == ')'
                        || expression.charAt(expression.length() - 1) == '%')) {
                    Toast.makeText(this, "请先输入运算符", Toast.LENGTH_SHORT).show();
                    break;
                }
                forCompute += "6";
                expression += "6";
                input.setText(expression);

                break;
            case R.id.sub:
                if (forCompute.length() > 0 && forCompute.charAt(forCompute.length() - 1) == 's') {
                    Toast.makeText(this, "不允许给负数开平方", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (expression.equals("") || expression.equals(" ") || expression.equals("/n")) {
                    expression = "-";
                    forCompute = "0-";
                    input.setText(expression);
                    break;
                }
                if (forCompute.substring(forCompute.length() - 1).equals("-")) {
                    forCompute = forCompute.substring(0, forCompute.length() - 1);
                    expression = expression.substring(0, expression.length() - 1);
                    expression += "+";
                    input.setText(expression);
                    forCompute += "+";
                    break;
                }

                if (forCompute.substring(forCompute.length() - 1).equals("+") ||
                        forCompute.substring(forCompute.length() - 1).equals("*") ||
                        forCompute.substring(forCompute.length() - 1).equals("/") ||
                        forCompute.charAt(forCompute.length() - 1) == 's') {
                    expression = expression.substring(0, expression.length() - 1);
                    forCompute = forCompute.substring(0, forCompute.length() - 1);
                    expression += "-";
                    input.setText(expression);
                    forCompute += "-";
                    break;
                }


                forCompute += "-";
                expression += "-";
                input.setText(expression);

                break;
            case R.id.one:
                if (forCompute.length() != 0 && (forCompute.charAt(forCompute.length() - 1) == ')'
                        || expression.charAt(expression.length() - 1) == '%')) {
                    Toast.makeText(this, "请先输入运算符", Toast.LENGTH_SHORT).show();
                    break;
                }
                forCompute += "1";
                expression += "1";
                input.setText(expression);
                break;
            case R.id.two:
                if (forCompute.length() != 0 && (forCompute.charAt(forCompute.length() - 1) == ')'
                        || expression.charAt(expression.length() - 1) == '%')) {
                    Toast.makeText(this, "请先输入运算符", Toast.LENGTH_SHORT).show();
                    break;
                }
                forCompute += "2";
                expression += "2";
                input.setText(expression);

                break;
            case R.id.three:
                if (forCompute.length() != 0 && (forCompute.charAt(forCompute.length() - 1) == ')'
                        || expression.charAt(expression.length() - 1) == '%')) {
                    Toast.makeText(this, "请先输入运算符", Toast.LENGTH_SHORT).show();
                    break;
                }
                forCompute += "3";
                expression += "3";
                input.setText(expression);

                break;
            case R.id.add:
                if (forCompute.length() > 0 && forCompute.charAt(forCompute.length() - 1) == 's') {
                    Toast.makeText(this, "请先输入数字", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (expression.equals("") || expression.equals(" ") || expression.equals("/n")) {
                    expression = "0+";
                    forCompute = "0+";
                    input.setText(expression);
                    break;
                }
                if (forCompute.substring(forCompute.length() - 1).equals("-") ||
                        forCompute.substring(forCompute.length() - 1).equals("+") ||
                        forCompute.substring(forCompute.length() - 1).equals("*") ||
                        forCompute.substring(forCompute.length() - 1).equals("/")) {
                    expression = expression.substring(0, expression.length() - 1);
                    forCompute = forCompute.substring(0, forCompute.length() - 1);
                    expression += "+";
                    forCompute += "+";
                    input.setText(expression);
                    break;
                }

                forCompute += "+";
                expression += "+";
                input.setText(expression);

                break;
            case R.id.sqrt:
                if (forCompute.length() != 0 && forCompute.charAt(forCompute.length() - 1) == 's') {
                    break;
                }
                if (forCompute.length() != 0 && forCompute.charAt(forCompute.length() - 1) == '.') {
                    expression += "0√";
                    input.setText(expression);
                    forCompute += "0s";
                    break;
                }
                expression += "√";
                input.setText(expression);
                forCompute += "s";
                break;
            case R.id.zero:
                if (forCompute.length() != 0 && (forCompute.charAt(forCompute.length() - 1) == ')'
                        || expression.charAt(expression.length() - 1) == '%')) {
                    Toast.makeText(this, "请先输入运算符", Toast.LENGTH_SHORT).show();
                    break;
                }
                forCompute += "0";
                expression += "0";
                input.setText(expression);

                break;
            case R.id.point:

                if (!ArithHelper.findMinDistance(forCompute))
                    break;
                if (expression.equals("") || expression.equals(" ") ||
                        expression.equals("/n")) {
                    expression = "0.";
                    forCompute = "0.";
                    input.setText(expression);
                    break;
                }
                if (forCompute.length() != 0 && (forCompute.charAt(forCompute.length() - 1) == ')'
                        || expression.charAt(expression.length() - 1) == '%')) {
                    Toast.makeText(this, "请先输入运算符", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (forCompute.charAt(forCompute.length() - 1) == '+' ||
                        forCompute.charAt(forCompute.length() - 1) == '-' ||
                        forCompute.charAt(forCompute.length() - 1) == '*' ||
                        forCompute.charAt(forCompute.length() - 1) == '/' ||
                        forCompute.charAt(forCompute.length() - 1) == 's') {
//                    expression = expression.substring(0, expression.length() - 1);
//                    forCompute = forCompute.substring(0, forCompute.length() - 1);
                    expression += "0.";
                    forCompute += "0.";
                    input.setText(expression);
                    break;
                }
                expression += ".";
                forCompute += ".";
                input.setText(expression);
                break;
            case R.id.equal:
                if (expression.equals("") || expression.equals(" ") || expression.equals("/n")) {
                    expression = "0";
                    forCompute = "0";
                    input.setText("0");
                    result.setText("=0");
                    break;
                }
                if (forCompute.charAt(forCompute.length() - 1) == '+' ||
                        forCompute.charAt(forCompute.length() - 1) == '-' ||
                        forCompute.charAt(forCompute.length() - 1) == '*' ||
                        forCompute.charAt(forCompute.length() - 1) == '/' ||
                        forCompute.charAt(forCompute.length() - 1) == 's' ||
                        forCompute.charAt(forCompute.length() - 1) == '.'
                ) {
                    Toast.makeText(this, "请输入正确的表达式", Toast.LENGTH_SHORT).show();
                    break;
                }
                forCompute = ArithHelper.processSqrt(forCompute);
                resultString = "=";
                String tempResult = cal.calculate(forCompute);
                // Log.e("TAG", "onClick: " + cal.calculate(forCompute));
                resultString += tempResult;
                if (resultString.length() > 12) {
                    result.setMovementMethod(ScrollingMovementMethod.getInstance());
                    result.setTextSize(25);
                } else {
                    result.setTextSize(50);
                }
                // Log.e("TAG", "onClick: " + resultString);
                result.setText(resultString);


                //expression = tempResult;
                // forCompute = tempResult;
                break;
        }


    }

    @Override
    public boolean onLongClick(View view) {

        int flag = -1;
        for (int i = forCompute.length() - 1; i >= 0; i--) {
            if (forCompute.charAt(i) == 's') {
                flag = i;
                break;
            }
        }
        if (flag != -1) {
            if (!forCompute.substring(flag, forCompute.length() - 1).contains("+")
                    && !forCompute.substring(flag, forCompute.length() - 1).contains("-")
                    && !forCompute.substring(flag, forCompute.length() - 1).contains("*")
                    && !forCompute.substring(flag, forCompute.length() - 1).contains("/")) {
                Toast.makeText(this, "根号下不允许出现负数", Toast.LENGTH_SHORT).show();
                return true;

            }
        }
        if (forCompute.length() == 0 || forCompute.charAt(forCompute.length() - 1) == '+' ||
                forCompute.charAt(forCompute.length() - 1) == '-' ||
                forCompute.charAt(forCompute.length() - 1) == '*' ||
                forCompute.charAt(forCompute.length() - 1) == '/' ||
                forCompute.charAt(forCompute.length() - 1) == 's' ||
                forCompute.charAt(forCompute.length() - 1) == '.') {
            Toast.makeText(this, "请先输入数字", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (view.getId() == R.id.sub) {
            forCompute = ArithHelper.NP(forCompute);
            expression = forCompute;
            expression = expression.replace('s', '√');
            expression = expression.replace('*', '×');
            expression = expression.replace('/', '÷');
            input.setText(expression);
        }
        return true;
    }
}