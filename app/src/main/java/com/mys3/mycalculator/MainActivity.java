package com.mys3.mycalculator;

import android.content.Intent;
import android.content.res.Configuration;
import android.icu.text.DecimalFormat;
import android.renderscript.Double2;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.*;
import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    Double result = 0.;
    Double Answer = 0.;
    Double result2 = 0.;
    Double result3 = 0.;
    Double result4 = 0.;
    Double a = 0.;
    private String display = "";
    private String ans = "";

    private TextView textview,otextview;

    private Configuration config;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        config = getResources().getConfiguration();

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            LM_Fragement lm_fragement = new LM_Fragement();
            fragmentTransaction.replace(R.id.content,lm_fragement);
            fragmentTransaction.commit();
        }

        else
        {
            PM_Fragement pm_fragement = new PM_Fragement();
            fragmentTransaction.replace(R.id.content, pm_fragement);
            fragmentTransaction.commit();
        }

    }


    @Override
    protected void onStart()
    {
        super.onStart();
        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            textview = (TextView) findViewById(R.id.ltextViewCalculator);
            textview.setText(display);
        }

        else {
            textview = (TextView) findViewById(R.id.textViewCalculator);
            textview.setText(display);
        }
    }

    public void ButtonClick(View view)
    {
        Button button = (Button) view;

        String buttonText = button.getText().toString();

        String txtV = textview.getText().toString();

        if(txtV.contains("="))
        {
            textview.setText("");
            ButtonClick(button);
        }

        else if (buttonText.equals("."))
        {
            if (txtV.contains("."))
            {
                String[] s1 = txtV.split("[+|-|×|/]");
                String s3 = txtV.substring(txtV.length()-1,textview.length());

                int a = s1.length;
                int b = s1[a-1].split("[.]").length;

                if (s3.equals(".") || b > 1)
                {
                    textview.setText(txtV);
                }

                    else
                    {
                        String s2 = textview.getText().toString()+buttonText;
                        textview.setText(s2);
                    }
            }

            else {
                String s2 = textview.getText().toString()+buttonText;
                textview.setText(s2);
            }
        }



        else if(buttonText.matches("^[0-9]"))
        {
            String s = textview.getText().toString()+buttonText;
            textview.setText(s);
        }

        display = textview.getText().toString();

    }

    public void ArithmaticButtonClick(View view)
    {
        Button button = (Button) view;

        String buttonText = button.getText().toString();

        String txtV = textview.getText().toString();


        if (!txtV.equals(""))
        {
            String s3 = txtV.substring(txtV.length()-1,textview.length());

            if (s3.equals("+")|| s3.equals("-") || s3.equals("×")|| s3.equals("/"))
            {
                textview.setText(txtV);
            }

            else{

                if (txtV.contains("="))
                {
                    String s2 = ans+buttonText;
                    textview.setText(s2);
                }

                else    {
                    String s2 = txtV+buttonText;
                    textview.setText(s2);
                }

            }
        }


        else
        {
            if (buttonText.equals("-"))
            {
                String s2 = txtV+buttonText;
                textview.setText(s2);
            }

            else{
                textview.setText(txtV);
            }
        }
        display = textview.getText().toString();
    }

    public void EditButtonClick(View view)
    {
        if (textview.getText().length()>0)
        {
            textview.setText(textview.getText().subSequence(0, textview.getText().length() - 1));
        }
    }

    public void RefreshButtonClick(View view)
    {
        textview.setText("");
        display = "";
        ans = "";
    }

    public void ButtonSquar(View view)
    {
        String txtV = textview.getText().toString();

        if (!txtV.equals("")) {
            if (txtV.matches(".*[=].*")) {
                Double a = Double.valueOf(ans);

                display = String.valueOf(Math.pow(a, 2.0));

                double d = Double.valueOf(display);

                String[] s = display.split("[.]");

                double x = d - Math.floor(d);

                if (x > 0) {
                    textview.setText(String.valueOf(display));
                    ans = display;
                    display = textview.getText().toString();
                } else {
                    textview.setText(s[0]);
                    ans = s[0];
                    display = textview.getText().toString();
                }
            }

            if (!txtV.matches(".*[=].*")) {


            if (!txtV.matches(".*[+].*") && !txtV.matches(".*[-].*") && !txtV.matches(".*[×].*") && !txtV.matches(".*[/].*")) {
                Double a = Double.valueOf(display);

                display = String.valueOf(Math.pow(a, 2.0));

                double d = Double.valueOf(display);

                String[] s = display.split("[.]");

                double x = d - Math.floor(d);

                if (x > 0) {
                    textview.setText(String.valueOf(display));
                    display = textview.getText().toString();
                    ans = display;
                } else {
                    textview.setText(s[0]);
                    ans = s[0];
                    display = textview.getText().toString();
                }

            }

                else {
                    textview.setText("0");
                    display = "0";
                    ans = "0";
                }

            }
        }

    }

    public void ButtonSquarRoot(View view) {

        String txtV = textview.getText().toString();

        if (!txtV.equals("")) {
            if (txtV.contains("=")) {
                Double a = Double.valueOf(ans);

                display = String.valueOf(Math.pow(a, 0.5));

                double d = Double.valueOf(display);

                String[] s = display.split("[.]");

                double x = d - Math.floor(d);

                if ( x > 0) {
                    textview.setText(String.valueOf(display));
                    ans = display;
                    display = textview.getText().toString();
                } else {
                    textview.setText(s[0]);
                    ans = s[0];
                    display = textview.getText().toString();
                }
            }

            if (!txtV.matches(".*[=].*")) {


                if (!txtV.matches(".*[+].*") && !txtV.matches(".*[-].*") && !txtV.matches(".*[×].*") && !txtV.matches(".*[/].*")) {
                    Double a = Double.valueOf(display);

                    display = String.valueOf(Math.pow(a, 0.5));

                    double d = Double.valueOf(display);

                    String[] s = display.split("[.]");

                    double x = d - Math.floor(d);

                    if (x > 0) {
                        textview.setText(String.valueOf(display));
                        display = textview.getText().toString();
                        ans = display;
                    } else {
                        textview.setText(s[0]);
                        ans = s[0];
                        display = textview.getText().toString();
                    }

                }

                else {
                    textview.setText("0");
                    display = "0";
                    ans = "0";
                }

            }

        }

    }


    public void ButtonPercentageClick(View view)
    {
        String txtV = textview.getText().toString();

        if (!txtV.equals("")) {
            if (txtV.contains("=")) {
                Double a = Double.valueOf(ans);

                display = String.valueOf(a/100);

                double d = Double.valueOf(display);

                String[] s = display.split("[.]");

                double x = d - Math.floor(d);

                if ( x > 0) {
                    textview.setText(String.valueOf(display));
                    ans = display;
                    display = textview.getText().toString();
                } else {
                    textview.setText(s[0]);
                    ans = s[0];
                    display = textview.getText().toString();
                }
            }

            if (!txtV.matches(".*[=].*")) {


                if (!txtV.matches(".*[+].*") && !txtV.matches(".*[-].*") && !txtV.matches(".*[×].*") && !txtV.matches(".*[/].*")) {
                    Double a = Double.valueOf(display);

                    display = String.valueOf(a/100);

                    double d = Double.valueOf(display);

                    String[] s = display.split("[.]");

                    double x = d - Math.floor(d);

                    if (x > 0) {
                        textview.setText(String.valueOf(display));
                        display = textview.getText().toString();
                        ans = display;
                    } else {
                        textview.setText(s[0]);
                        ans = s[0];
                        display = textview.getText().toString();
                    }

                }

                else {
                    textview.setText("0");
                    display = "0";
                    ans = "0";
                }

            }
        }

    }

    public void ButtonInverse(View view)
    {
        String txtV = textview.getText().toString();

        if (!txtV.equals("")) {

            if (txtV.matches(".*[=].*")) {
                Double a = Double.valueOf(ans);

                display = String.valueOf(1/a);

                double d = Double.valueOf(display);

                String[] s = display.split("[.]");

                double x = d - Math.floor(d);

                if ( x > 0) {
                    textview.setText(String.valueOf(display));
                    ans = display;
                    display = textview.getText().toString();
                }
                else {
                    textview.setText(s[0]);
                    ans = s[0];
                    display = textview.getText().toString();
                }
            }

            if (!txtV.matches(".*[=].*")) {

                if (!txtV.matches(".*[+].*") && !txtV.matches(".*[-].*") && !txtV.matches(".*[×].*") && !txtV.matches(".*[/].*")) {
                    Double a = Double.valueOf(display);

                    display = String.valueOf(1/a);

                    double d = Double.valueOf(display);

                    String[] s = display.split("[.]");

                    double x = d - Math.floor(d);

                    if (x > 0) {
                        textview.setText(String.valueOf(display));
                        display = textview.getText().toString();
                        ans = display;
                    } else {
                        textview.setText(s[0]);
                        ans = s[0];
                        display = textview.getText().toString();
                    }

                }

                else{
                    textview.setText("0");
                    display = "0";
                    ans = "0";
                }

            }
        }

    }

    public void AnsButtonClick(View view)
    {
        if (textview.getText().toString().contains("="))
        {
            textview.setText(ans);
        }

        else{

            String txtV = textview.getText().toString();

            char lastchar = txtV.charAt(txtV.length() - 1);

            String ch = String.valueOf(lastchar);

            if( !ch.equals("+") && !ch.equals("-") && !ch.equals("×") && !ch.equals("/"))
            {
                String s = txtV+"×"+ans;
                textview.setText(s);
            }

            else
            {
                String s = txtV+ans;
                textview.setText(s);
            }

        }

        display = textview.getText().toString();
    }


    // Calculator //
    public void AnswerButtonClicked(View view)
    {

            Answer = RecursiveCalculator(display);

            String[] s = Answer.toString().split("[.]");

            double x = Answer - Math.floor(Answer);

            if (x > 0)
            {
                textview.setText(display+"="+String.valueOf(Answer));
                ans = Answer.toString();
            }

            else{
                textview.setText(display+"="+s[0]);
                ans = s[0];
            }

            result = 0.;

    }

    public double RecursiveCalculator(String eq)
    {
        int eqLength = eq.length();

        if (eqLength > 0 && (eq.matches(".*[+].*") ||eq.matches(".*[-].*") || eq.matches(".*[×].*") || eq.matches(".*[/].*")))
        {
            if (eq.matches(".*[+].*")) {

                String[] s = eq.split("[+]");

                for (String x: s){
                    if(x.length()>1 && (x.matches(".*[-].*") || x.matches(".*[×].*") || x.matches(".*[/].*"))) {
                        result += minusPart(x);
                        result2 = 0.;
                    }
                }

                for (String x: s)
                {
                    if ((!x.matches(".*[-].*") && !x.matches(".*[×].*") && !x.matches(".*[/].*")) && !x.equals(""))
                    {
                        result += Double.valueOf(x);
                    }
                }
            }


            else if (eq.contains("-"))
            {

                String[] s = eq.split("[-]");

                String s1 = eq.substring(s[0].length(),eq.length());

                String[] ss = s1.split("[-]");

                String[] s3 = eq.split("[0-9]");

                if (!s[0].equals(""))
                {
                    if(s3[0].equals("-"))
                    {
                        if ((s[0].matches(".*[×].*") || s[0].matches(".*[/].*")))
                        {
                            result -= multiplePart(s[0]);
                            result3 = 0.;
                            result4 = 0.;
                        }

                        if ((!s[0].matches(".*[×].*") && !s[0].matches(".*[/].*")))
                        {
                            result -= Double.valueOf(s[0]);
                        }
                    }

                    if(!s3[0].equals("-"))
                    {
                        if ((s[0].matches(".*[×].*") || s[0].matches(".*[/].*")))
                        {
                            result += multiplePart(s[0]);
                            result3 = 0.;
                            result4 = 0.;
                        }

                        if ((!s[0].matches(".*[×].*") && !s[0].matches(".*[/].*")))
                        {
                            result += Double.valueOf(s[0]);
                        }
                    }

                }

                for (String x: ss){
                    if(x.length()>1 && (x.matches(".*[×].*") || x.matches(".*[/].*"))) {

                        result -= multiplePart(x);
                        result3 = 0.;
                        result4 = 0.;
                    }
                }

                for (int i = 1; i < s.length; i++)
                {
                    if ((!s[i].matches(".*[×].*") && !s[i].matches(".*[/].*")) && !s[i].equals(""))
                    {
                        result -= Double.valueOf(s[i]);
                    }
                }

            }

            else if (eq.contains("×"))
            {

                String[] s = eq.split("[×]");

                String s1 = eq.substring(s[0].length(),eq.length());

                String[] ss = s1.split("[×]");

                if (s[0] != null)
                {
                    if (s[0].matches(".*[/].*"))
                    {
                        result += divisionPart(s[0]);
                        result4 = 0.;
                    }

                    if (!s[0].matches(".*[/].*"))
                    {
                        result += Double.valueOf(s[0]);
                    }
                }

                for (int i = 1; i < s.length; i++)
                {
                    if (!s[i].matches(".*[/].*") && !s[i].equals(""))
                    {
                        result *= Double.valueOf(s[i]);
                    }
                }

                for (String x: ss){
                    if(x.length()>1) {
                        if (x.matches(".*[/].*")) {
                            result *= divisionPart(x);
                            result4 = 0.;
                        }
                    }
                }

            }

            else if (eq.contains("/"))
            {
                String[] s = eq.split("[/]");

                if (!s[0].equals(""))
                {
                    if (result == 0)
                    {
                        result += Double.valueOf(s[0]);
                    }
                }

                for (int i = 1; i < s.length; i++)
                {
                    if (!s[i].equals(""))
                    {
                        result /= Double.valueOf(s[i]);
                    }
                }
            }

        }

        else {
            result = Double.valueOf(eq);
        }
        return result;
    }

    public double minusPart(String eq)
    {
        if (eq.contains("-"))
        {

            String[] s = eq.split("[-]");

            String s1 = eq.substring(s[0].length(),eq.length());

            String[] ss = s1.split("[-]");

            String[] s3 = eq.split("[0-9]");

            if (!s[0].equals(""))
            {
                if(s3[0].equals("-"))
                {
                    if ((s[0].matches(".*[×].*") || s[0].matches(".*[/].*")))
                    {
                        result2 -= multiplePart(s[0]);
                        result3 = 0.;
                        result4 = 0.;
                    }

                    if ((!s[0].matches(".*[×].*") && !s[0].matches(".*[/].*")))
                    {
                        result2 -= Double.valueOf(s[0]);
                    }
                }

                if(!s3[0].equals("-"))
                {
                    if ((s[0].matches(".*[×].*") || s[0].matches(".*[/].*")))
                    {
                        result2 += multiplePart(s[0]);
                        result3 = 0.;
                        result4 = 0.;
                    }

                    if (!(s[0].matches(".*[×].*") && !s[0].matches(".*[/].*")))
                    {
                        result2 += Double.valueOf(s[0]);
                    }
                }

            }


            for (String x: ss){
                if(x.length()>1) {
                    result2 -= multiplePart(x);
                    result3 = 0.;
                    result4 = 0.;
                }
            }

            for (int i = 1; i < s.length; i++)
            {
                if ((!s[i].matches(".*[×].*") && !s[i].matches(".*[/].*")) && !s[i].equals(""))
                {
                    result2 -= Double.valueOf(s[i]);
                }
            }

        }

        else
        {
            result2 = multiplePart(eq);
            result3 = 0.;
            result4 = 0.;
        }

        return result2;
    }

    public double multiplePart(String eq)
    {
        if (eq.contains("×"))
        {
            String[] s = eq.split("[×]");

            String s1 = eq.substring(s[0].length(),eq.length());

            String[] ss = s1.split("[×]");

            if (!s[0].equals(""))
            {
                if ((s[0].contains("/")))
                {
                    result3 += divisionPart(s[0]);
                    result4 = 0.;
                }

                if (!(s[0].contains("/")))
                {
                    result3 += Double.valueOf(s[0]);
                }
            }

            for (int i = 1; i < s.length; i++)
            {
                if (!s[i].matches(".*[/].*") && !s[i].equals(""))
                {
                    result3 *= Double.valueOf(s[i]);
                }
            }

            for (String x: ss){
                if(x.length()>1) {
                    if (x.contains("/")) {
                        result3 *= divisionPart(x);
                        result4 = 0.;
                    }
                }
            }

        }

        else
        {
            result3 = divisionPart(eq);
            result4 = 0.;
        }

        return result3;
    }

    public double divisionPart(String eq)
    {
        int eqLength = eq.length();

        if (eqLength > 1) {

            String[] s = eq.split("[/]");

            if (!s[0].equals("")) {
                    result4 += Double.valueOf(s[0]);
            }

            for (int i = 1; i < s.length; i++) {
                if (!s[i].equals("")) {
                    result4 /= Double.valueOf(s[i]);
                }
            }
        }

        return result4;
    }

// Calculator //





}
