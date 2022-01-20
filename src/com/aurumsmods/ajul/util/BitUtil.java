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
package com.aurumsmods.ajul.util;

/**
 * A utility class that provides helper functions for common bitwise operations and manipulations.
 * @author Aurum
 */
public final class BitUtil {
    private BitUtil() {
        throw new IllegalStateException("Instantiation of this class is forbidden!");
    }
    
    /**
     * Tests whether the specified bit in {@code val} is set or not. Returns {@code true} if set, {@code false} if not.
     * @param val the value to check the bit in.
     * @param bit the bit to be checked.
     * @return {@code true} if specified bit is set, {@code false} if not.
     */
    public static boolean test(int val, int bit) {
        return ((val >>> bit) & 1) == 1;
    }
    
    /**
     * Sets the specified bit in {@code val} and returns the result.
     * @param val the value to set the bit in.
     * @param bit the bit to be set.
     * @return {@code val} with specified bit set.
     */
    public static int set(int val, int bit) {
        return val | (1 << bit);
    }
    
    /**
     * Clears the specified bit in {@code val} and returns the result.
     * @param val the value to clear the bit in.
     * @param bit the bit to be cleared.
     * @return {@code val} after bit is cleared.
     */
    public static int clear(int val, int bit) {
        return val & ~(1 << bit);
    }
    
    /**
     * Toggles the specified bit in {@code val} and returns the result.
     * @param val the value to toggle the bit in.
     * @param bit the bit to be toggled.
     * @return {@code val} after bit is toggled.
     */
    public static int toggle(int val, int bit) {
        return val ^ (1 << bit);
    }
    
    /**
     * If {@code state} is true, the specified bit in {@code val} will be set, otherwise, the bit will be cleared. The resulting
     * value will be returned.
     * @param val the value to update the bit in.
     * @param bit the bit to be updated.
     * @param state declares whether to set ({@code true}) or clear ({@code false}) the bit.
     * @return the result of updating {@code val}.
     */
    public static int update(int val, int bit, boolean state) {
        return state ? set(val, bit) : clear(val, bit);
    }
    
    /**
     * Tests the set bits in {@code val} against the specified bitmask. Returns {@code true} if all of them are set,
     * {@code false} if not.
     * @param val the value to check the bits in.
     * @param mask the bitmask.
     * @return {@code true} if all specifies bits are set, {@code false} if not.
     */
    public static boolean testWithMask(int val, int mask) {
        return (val & mask) != 0;
    }
    
    /**
     * If {@code state} is true, the result of {@code val} | {@code mask} will be returned, otherwise, the result of 
     * {@code val} {@literal &} ~{@code mask} will be returned.
     * @param val the value to update.
     * @param mask the bitmask.
     * @param state declares whether to set ({@code true}) or clear ({@code false}) the bits.
     * @return the updated result.
     */
    public static int updateWithMask(int val, int mask, boolean state) {
        return state ? val | mask : val & ~mask;
    }
}
