/*
 * The MIT License
 *
 * Copyright 2021 Aurum.
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
package com.aurumsmods.ajul.util;

import java.util.Objects;

/**
 * A utility class that provides new helper methods to deal with common array operations. Most of them are based on common
 * {@code Collection} operations.
 * @author Aurum
 */
public final class ArrayUtil {
    private ArrayUtil() {
        throw new IllegalStateException("Instantiation of this class is forbidden!");
    }
    
    /**
     * Retrieves the index of the given value in the array using linear search. If the value cannot be found, -1 is returned
     * instead.
     * @param arr the array to search through.
     * @param val the value to be searched for.
     * @param off the starting offset in the array.
     * @return the index of the given value in the array if it exists. Otherwise, -1 is returned.
     */
    public static int indexOf(byte[] arr, byte val, int off) {
        Objects.checkFromIndexSize(off, 1, arr.length);
        
        for ( ; off < arr.length ; off++) {
            if (arr[off] == val)
                return off;
        }
        
        return -1;
    }
    
    /**
     * Retrieves the index of the given value in the array using linear search. If the value cannot be found, -1 is returned
     * instead.
     * @param arr the array to search through.
     * @param val the value to be searched for.
     * @param off the starting offset in the array.
     * @return the index of the given value in the array if it exists. Otherwise, -1 is returned.
     */
    public static int indexOf(short[] arr, short val, int off) {
        Objects.checkFromIndexSize(off, 1, arr.length);
        
        for ( ; off < arr.length ; off++) {
            if (arr[off] == val)
                return off;
        }
        
        return -1;
    }
    
    /**
     * Retrieves the index of the given value in the array using linear search. If the value cannot be found, -1 is returned
     * instead.
     * @param arr the array to search through.
     * @param val the value to be searched for.
     * @param off the starting offset in the array.
     * @return the index of the given value in the array if it exists. Otherwise, -1 is returned.
     */
    public static int indexOf(int[] arr, int val, int off) {
        Objects.checkFromIndexSize(off, 1, arr.length);
        
        for ( ; off < arr.length ; off++) {
            if (arr[off] == val)
                return off;
        }
        
        return -1;
    }
    
    /**
     * Retrieves the index of the given value in the array using linear search. If the value cannot be found, -1 is returned
     * instead.
     * @param arr the array to search through.
     * @param val the value to be searched for.
     * @param off the starting offset in the array.
     * @return the index of the given value in the array if it exists. Otherwise, -1 is returned.
     */
    public static int indexOf(long[] arr, long val, int off) {
        Objects.checkFromIndexSize(off, 1, arr.length);
        
        for ( ; off < arr.length ; off++) {
            if (arr[off] == val)
                return off;
        }
        
        return -1;
    }
    
    /**
     * Retrieves the index of the given value in the array using linear search. If the value cannot be found, -1 is returned
     * instead.
     * @param arr the array to search through.
     * @param val the value to be searched for.
     * @param off the starting offset in the array.
     * @return the index of the given value in the array if it exists. Otherwise, -1 is returned.
     */
    public static int indexOf(float[] arr, float val, int off) {
        Objects.checkFromIndexSize(off, 1, arr.length);
        
        for ( ; off < arr.length ; off++) {
            if (arr[off] == val)
                return off;
        }
        
        return -1;
    }
    
    /**
     * Retrieves the index of the given value in the array using linear search. If the value cannot be found, -1 is returned
     * instead.
     * @param arr the array to search through.
     * @param val the value to be searched for.
     * @param off the starting offset in the array.
     * @return the index of the given value in the array if it exists. Otherwise, -1 is returned.
     */
    public static int indexOf(double[] arr, double val, int off) {
        Objects.checkFromIndexSize(off, 1, arr.length);
        
        for ( ; off < arr.length ; off++) {
            if (arr[off] == val)
                return off;
        }
        
        return -1;
    }
    
    /**
     * Retrieves the index of the given value in the array using linear search. If the value cannot be found, -1 is returned
     * instead.
     * @param arr the array to search through.
     * @param val the value to be searched for.
     * @param off the starting offset in the array.
     * @return the index of the given value in the array if it exists. Otherwise, -1 is returned.
     */
    public static int indexOf(boolean[] arr, boolean val, int off) {
        Objects.checkFromIndexSize(off, 1, arr.length);
        
        for ( ; off < arr.length ; off++) {
            if (arr[off] == val)
                return off;
        }
        
        return -1;
    }
    
    /**
     * Retrieves the index of the given value in the array using linear search. If the value cannot be found, -1 is returned
     * instead.
     * @param arr the array to search through.
     * @param val the value to be searched for.
     * @param off the starting offset in the array.
     * @return the index of the given value in the array if it exists. Otherwise, -1 is returned.
     */
    public static int indexOf(char[] arr, char val, int off) {
        Objects.checkFromIndexSize(off, 1, arr.length);
        
        for ( ; off < arr.length ; off++) {
            if (arr[off] == val)
                return off;
        }
        
        return -1;
    }
    
    /**
     * Retrieves the index of the given value in the array using linear search. If the value cannot be found, -1 is returned
     * instead.
     * @param arr the array to search through.
     * @param val the value to be searched for.
     * @param off the starting offset in the array.
     * @return the index of the given value in the array if it exists. Otherwise, -1 is returned.
     */
    public static int indexOf(Object[] arr, Object val, int off) {
        Objects.checkFromIndexSize(off, 1, arr.length);
        
        for ( ; off < arr.length ; off++) {
            Object elem = arr[off];
            if (val == null ? elem == null : val.equals(elem))
                return off;
        }
        
        return -1;
    }
    
    /**
     * Checks and returns {@code true} if the given value exists in the array. Otherwise, {@code false} is returned.
     * @param arr the array to search through.
     * @param val the value to be searched for.
     * @return returns {@code true} if the given value exists in the array. Otherwise, {@code false} is returned.
     */
    public static boolean contains(byte[] arr, byte val) {
        return indexOf(arr, val, 0) != -1;
    }
    
    /**
     * Checks and returns {@code true} if the given value exists in the array. Otherwise, {@code false} is returned.
     * @param arr the array to search through.
     * @param val the value to be searched for.
     * @return returns {@code true} if the given value exists in the array. Otherwise, {@code false} is returned.
     */
    public static boolean contains(short[] arr, short val) {
        return indexOf(arr, val, 0) != -1;
    }
    
    /**
     * Checks and returns {@code true} if the given value exists in the array. Otherwise, {@code false} is returned.
     * @param arr the array to search through.
     * @param val the value to be searched for.
     * @return returns {@code true} if the given value exists in the array. Otherwise, {@code false} is returned.
     */
    public static boolean contains(int[] arr, int val) {
        return indexOf(arr, val, 0) != -1;
    }
    
    /**
     * Checks and returns {@code true} if the given value exists in the array. Otherwise, {@code false} is returned.
     * @param arr the array to search through.
     * @param val the value to be searched for.
     * @return returns {@code true} if the given value exists in the array. Otherwise, {@code false} is returned.
     */
    public static boolean contains(long[] arr, long val) {
        return indexOf(arr, val, 0) != -1;
    }
    
    /**
     * Checks and returns {@code true} if the given value exists in the array. Otherwise, {@code false} is returned.
     * @param arr the array to search through.
     * @param val the value to be searched for.
     * @return returns {@code true} if the given value exists in the array. Otherwise, {@code false} is returned.
     */
    public static boolean contains(float[] arr, float val) {
        return indexOf(arr, val, 0) != -1;
    }
    
    /**
     * Checks and returns {@code true} if the given value exists in the array. Otherwise, {@code false} is returned.
     * @param arr the array to search through.
     * @param val the value to be searched for.
     * @return returns {@code true} if the given value exists in the array. Otherwise, {@code false} is returned.
     */
    public static boolean contains(double[] arr, double val) {
        return indexOf(arr, val, 0) != -1;
    }
    
    /**
     * Checks and returns {@code true} if the given value exists in the array. Otherwise, {@code false} is returned.
     * @param arr the array to search through.
     * @param val the value to be searched for.
     * @return returns {@code true} if the given value exists in the array. Otherwise, {@code false} is returned.
     */
    public static boolean contains(boolean[] arr, boolean val) {
        return indexOf(arr, val, 0) != -1;
    }
    
    /**
     * Checks and returns {@code true} if the given value exists in the array. Otherwise, {@code false} is returned.
     * @param arr the array to search through.
     * @param val the value to be searched for.
     * @return returns {@code true} if the given value exists in the array. Otherwise, {@code false} is returned.
     */
    public static boolean contains(char[] arr, char val) {
        return indexOf(arr, val, 0) != -1;
    }
    
    /**
     * Reverses the contents of the specified array.
     * @param arr the array to be reversed.
     */
    public static void reverse(byte[] arr) {
        int lastIndex = arr.length - 1;
        
        for (int l = 0, r = lastIndex ; l < arr.length / 2 ; l++, r--) {
            byte tmp = arr[l];
            arr[l] = arr[r];
            arr[r] = tmp;
        }
    }
    
    /**
     * Reverses the contents of the specified array.
     * @param arr the array to be reversed.
     */
    public static void reverse(short[] arr) {
        int lastIndex = arr.length - 1;
        
        for (int l = 0, r = lastIndex ; l < arr.length / 2 ; l++, r--) {
            short tmp = arr[l];
            arr[l] = arr[r];
            arr[r] = tmp;
        }
    }
    
    /**
     * Reverses the contents of the specified array.
     * @param arr the array to be reversed.
     */
    public static void reverse(int[] arr) {
        int lastIndex = arr.length - 1;
        
        for (int l = 0, r = lastIndex ; l < arr.length / 2 ; l++, r--) {
            int tmp = arr[l];
            arr[l] = arr[r];
            arr[r] = tmp;
        }
    }
    
    /**
     * Reverses the contents of the specified array.
     * @param arr the array to be reversed.
     */
    public static void reverse(long[] arr) {
        int lastIndex = arr.length - 1;
        
        for (int l = 0, r = lastIndex ; l < arr.length / 2 ; l++, r--) {
            long tmp = arr[l];
            arr[l] = arr[r];
            arr[r] = tmp;
        }
    }
    
    /**
     * Reverses the contents of the specified array.
     * @param arr the array to be reversed.
     */
    public static void reverse(float[] arr) {
        int lastIndex = arr.length - 1;
        
        for (int l = 0, r = lastIndex ; l < arr.length / 2 ; l++, r--) {
            float tmp = arr[l];
            arr[l] = arr[r];
            arr[r] = tmp;
        }
    }
    
    /**
     * Reverses the contents of the specified array.
     * @param arr the array to be reversed.
     */
    public static void reverse(double[] arr) {
        int lastIndex = arr.length - 1;
        
        for (int l = 0, r = lastIndex ; l < arr.length / 2 ; l++, r--) {
            double tmp = arr[l];
            arr[l] = arr[r];
            arr[r] = tmp;
        }
    }
    
    /**
     * Reverses the contents of the specified array.
     * @param arr the array to be reversed.
     */
    public static void reverse(boolean[] arr) {
        int lastIndex = arr.length - 1;
        
        for (int l = 0, r = lastIndex ; l < arr.length / 2 ; l++, r--) {
            boolean tmp = arr[l];
            arr[l] = arr[r];
            arr[r] = tmp;
        }
    }
    
    /**
     * Reverses the contents of the specified array.
     * @param arr the array to be reversed.
     */
    public static void reverse(char[] arr) {
        int lastIndex = arr.length - 1;
        
        for (int l = 0, r = lastIndex ; l < arr.length / 2 ; l++, r--) {
            char tmp = arr[l];
            arr[l] = arr[r];
            arr[r] = tmp;
        }
    }
    
    /**
     * Reverses the contents of the specified array.
     * @param arr the array to be reversed.
     */
    public static void reverse(Object[] arr) {
        int lastIndex = arr.length - 1;
        
        for (int l = 0, r = lastIndex ; l < arr.length / 2 ; l++, r--) {
            Object tmp = arr[l];
            arr[l] = arr[r];
            arr[r] = tmp;
        }
    }
}
