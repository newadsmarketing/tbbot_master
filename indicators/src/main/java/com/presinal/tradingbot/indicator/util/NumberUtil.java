/*
 * The MIT License
 *
 * Copyright 2018 Miguel Presinal.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.presinal.tradingbot.indicator.util;

import java.math.MathContext;

/**
 *
 * @author Miguel Presinal<presinal378@gmail.com>
 * @since 1.0
 */
public class NumberUtil {

    public static final MathContext MATHCONTEXT = MathContext.DECIMAL64;
    
    public static int decimalPlace = 8;
    
    public static void setDecimalPlace(int decimalPlace){
        NumberUtil.decimalPlace = decimalPlace;
    }
    
    public static double round(double value){
        return round(value, decimalPlace);
    }
    
    public static double round(double value, int decimalPlaces){
        String strNum = "1";
        for(int i = 0 ; i < decimalPlaces; i++) {
            strNum += "0";
        }
        
        double factor = Double.parseDouble(strNum);
        //double)Math.round(expectedPP*100)/100)
        return ((double) Math.round(value*factor)/factor);
    }
}
