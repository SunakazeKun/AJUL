/*
 * The MIT License
 *
 * Copyright 2022 Aurum.
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
package com.aurumsmods.ajul;

/**
 * A utility class that provides new mathematical functions and constants.
 * @author Aurum
 */
public final class MathUtil {
    private MathUtil() { throw new IllegalStateException(); }
    
    /**
     * The golden ratio.
     */
    public static final double PHI = 1.618033988749895;
    
    /**
     * The irrational square root of 2.
     */
    public static final double SQRT_2 = 1.414213562373095;
    
    /**
     * The irrational square root of 3.
     */
    public static final double SQRT_3 = 1.732050807568877;
    
    /**
     * The irrational square root of 5.
     */
    public static final double SQRT_5 = 2.236067977499790;
    
    /**
     * The natural logarithm of 2.
     */
    public static final double LN_2 = 0.693147180559945;
    
    /**
     * Calculates the fibonacci number for the given natural number.
     * @param n the natural number.
     * @return the fibonacci number.
     */
    public static long fibonacci(int n) {
        if (n < 1)
            return 0;
        
        long a = 1;
        long b = 0;
        long tmp;
        
        for (int i = 1 ; i < n ; i++) {
            tmp = b;
            b = a;
            a += tmp;
        }
        
        return a;
    }
    
    /**
     * Clamps a value beween a lower and an upper bound. If {@code max} is less than {@code min}, the two values are swapped.
     * @param min the lower bound.
     * @param max the upper bound.
     * @param val the value to clamp.
     * @return the clamped value.
     */
    public static int clamp(int min, int max, int val) {
        if (max < min) {
            int tmp = max;
            max = min;
            min = tmp;
        }
        
        if (val < min)
            return min;
        else if (val > max)
            return max;
        return val;
    }
    
    /**
     * Clamps a value beween a lower and an upper bound. If {@code max} is less than {@code min}, the two values are swapped.
     * @param min the lower bound.
     * @param max the upper bound.
     * @param val the value to clamp.
     * @return the clamped value.
     */
    public static long clamp(long min, long max, long val) {
        if (max < min) {
            long tmp = max;
            max = min;
            min = tmp;
        }
        
        if (val < min)
            return min;
        else if (val > max)
            return max;
        return val;
    }
    
    /**
     * Clamps a value beween a lower and an upper bound. If {@code max} is less than {@code min}, the two values are swapped.
     * @param min the lower bound.
     * @param max the upper bound.
     * @param val the value to clamp.
     * @return the clamped value.
     */
    public static float clamp(float min, float max, float val) {
        if (max < min) {
            float tmp = max;
            max = min;
            min = tmp;
        }
        
        if (val < min)
            return min;
        else if (val > max)
            return max;
        return val;
    }
    
    /**
     * Clamps a value beween a lower and an upper bound. If {@code max} is less than {@code min}, the two values are swapped.
     * @param min the lower bound.
     * @param max the upper bound.
     * @param val the value to clamp.
     * @return the clamped value.
     */
    public static double clamp(double min, double max, double val) {
        if (max < min) {
            double tmp = max;
            max = min;
            min = tmp;
        }
        
        if (val < min)
            return min;
        else if (val > max)
            return max;
        return val;
    }
    
    /**
     * Clamps the given value between 0.0f and 1.0f.
     * @param val the value to clamp.
     * @return the clamped value.
     */
    public static float clamp01(float val) {
        if (val < 0.0f)
            return 0.0f;
        else if (val > 1.0f)
            return 1.0f;
        return val;
    }
    
    /**
     * Clamps the given value between 0.0 and 1.0.
     * @param val the value to clamp.
     * @return the clamped value.
     */
    public static double clamp01(double val) {
        if (val < 0.0f)
            return 0.0f;
        else if (val > 1.0f)
            return 1.0f;
        return val;
    }
    
    /**
     * Calculates the linear interpolation for two input numbers and a parameter.
     * @param a the first number.
     * @param b the second number.
     * @param t the parameter (expected to be a number in the closed interval [0, 1])
     * @return the linear interpolation.
     */
    public static float lerp(float a, float b, float t) {
        return (1.0f - t) * a + t * b;
    }
    
    /**
     * Calculates the linear interpolation for two input numbers and a parameter.
     * @param a the first number.
     * @param b the second number.
     * @param t the parameter, expected to be a number in the closed interval [0, 1]
     * @return the linear interpolation.
     */
    public static double lerp(double a, double b, double t) {
        return (1.0 - t) * a + t * b;
    }
}
