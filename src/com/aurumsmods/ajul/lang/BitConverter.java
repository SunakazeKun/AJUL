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
package com.aurumsmods.ajul.lang;

import java.nio.ByteOrder;
import java.util.Objects;

/**
 * A utility class that provides helper methods to convert various primitive data types into binary byte array representations
 * and vice-versa. These can deal with big-endian and little-endian representations whereas Java libraries usually deal with
 * big-endian data only.
 * @author Aurum
 */
public final class BitConverter {
    private BitConverter() {
        throw new IllegalStateException("Instantiation of this class is forbidden!");
    }
    
    /**
     * Reads the {@code boolean} value at the specified offset in the raw data array. If the {@code byte} is 0, {@code false} is
     * returned, otherwise {@code true} is returned.
     * @param arr the input array.
     * @param off the offset in the data array.
     * @return the {@code boolean} value.
     */
    public static boolean getBoolean(byte[] arr, int off) {
        return arr[off] != 0;
    }
    
    /**
     * Reads the {@code byte} value (-128 to 127) at the specified offset in the raw data array.
     * @param arr the input array.
     * @param off the offset in the data array.
     * @return the {@code byte} value.
     */
    public static byte getByte(byte[] arr, int off) {
        return arr[off];
    }
    
    /**
     * Reads the {@code unsigned byte} value (0 to 255) at the specified offset in the raw data array.
     * @param arr the input array.
     * @param off the offset in the data array.
     * @return the {@code unsigned byte} value.
     */
    public static int getUnsignedByte(byte[] arr, int off) {
        return arr[off] & 0xFF;
    }
    
    /**
     * Reads the {@code short} value (-32768 to 32767) at the specified offset in the raw data array. The way the bytes are
     * interpreted depends on the specified {@code ByteOrder}.
     * @param arr the input array.
     * @param off the offset in the data array.
     * @param endian the byte order that specifies how the data will be interpreted.
     * @return the {@code short} value.
     */
    public static short getShort(byte[] arr, int off, ByteOrder endian) {
        int b0 = arr[off++] & 0xFF;
        int b1 = arr[off  ] & 0xFF;
        
        if (Objects.requireNonNull(endian) == ByteOrder.BIG_ENDIAN)
            return (short)((b0 << 8) | b1);
        else
            return (short)((b1 << 8) | b0);
    }
    
    /**
     * Reads the {@code unsigned short} value (0 to 65535) at the specified offset in the raw data array. The way the bytes are
     * interpreted depends on the specified {@code ByteOrder}.
     * @param arr the input array.
     * @param off the offset in the data array.
     * @param endian the byte order that specifies how the data will be interpreted.
     * @return the {@code unsigned short} value.
     * @see BitConverter#getShort(byte[], int, java.nio.ByteOrder) 
     */
    public static int getUnsignedShort(byte[] arr, int off, ByteOrder endian) {
        return getShort(arr, off, endian) & 0xFFFF;
    }
    
    /**
     * Reads the {@code char} value (0 to 65535) at the specified offset in the raw data array. The way the bytes are
     * interpreted depends on the specified {@code ByteOrder}.
     * @param arr the input array.
     * @param off the offset in the data array.
     * @param endian the byte order that specifies how the data will be interpreted.
     * @return the {@code char} value.
     * @see BitConverter#getShort(byte[], int, java.nio.ByteOrder) 
     */
    public static char getChar(byte[] arr, int off, ByteOrder endian) {
        return (char)getShort(arr, off, endian);
    }
    
    /**
     * Reads the {@code int} value (-2^31 to 2^31-1) at the specified offset in the raw data array. The way the bytes are
     * interpreted depends on the specified {@code ByteOrder}.
     * @param arr the input array.
     * @param off the offset in the data array.
     * @param endian the byte order that specifies how the data will be interpreted.
     * @return the {@code int} value.
     */
    public static int getInt(byte[] arr, int off, ByteOrder endian) {
        int b0 = arr[off++] & 0xFF;
        int b1 = arr[off++] & 0xFF;
        int b2 = arr[off++] & 0xFF;
        int b3 = arr[off  ] & 0xFF;
        
        if (Objects.requireNonNull(endian) == ByteOrder.BIG_ENDIAN)
            return ((b0 << 24) | (b1 << 16) | (b2 << 8) | b3);
        else
            return ((b3 << 24) | (b2 << 16) | (b1 << 8) | b0);
    }
    
    /**
     * Reads the {@code long} value (-2^63 to 2^63-1) at the specified offset in the raw data array. The way the bytes are
     * interpreted depends on the specified {@code ByteOrder}.
     * @param arr the input array.
     * @param off the offset in the data array.
     * @param endian the byte order that specifies how the data will be interpreted.
     * @return the {@code long} value.
     */
    public static long getLong(byte[] arr, int off, ByteOrder endian) {
        int b0 = arr[off++] & 0xFF;
        int b1 = arr[off++] & 0xFF;
        int b2 = arr[off++] & 0xFF;
        int b3 = arr[off++] & 0xFF;
        int b4 = arr[off++] & 0xFF;
        int b5 = arr[off++] & 0xFF;
        int b6 = arr[off++] & 0xFF;
        int b7 = arr[off  ] & 0xFF;
        
        if (Objects.requireNonNull(endian) == ByteOrder.BIG_ENDIAN)
            return ((long)b7 << 56) | ((long)b6 << 48) | ((long)b5 << 40) | ((long)b4 << 32) | (b3 << 24) | (b2 << 16) | (b1 <<  8) | b0;
        else
            return ((long)b0 << 56) | ((long)b1 << 48) | ((long)b2 << 40) | ((long)b3 << 32) | (b4 << 24) | (b5 << 16) | (b6 <<  8) | b7;
    }
    
    /**
     * Reads the half-float value at the specified offset in the raw data array. The way the bytes are interpreted depends on
     * the specified {@code ByteOrder}.
     * @param arr the input array.
     * @param off the offset in the data array.
     * @param endian the byte order that specifies how the data will be interpreted.
     * @return the half-float value.
     * @see #getShort(byte[], int, java.nio.ByteOrder) 
     * @see IEEE754Half#shortBitsToFloat(short)  
     */
    public static float getHalf(byte[] arr, int off, ByteOrder endian) {
        return IEEE754Half.shortBitsToFloat(getShort(arr, off, endian));
    }
    
    /**
     * Reads the {@code float} value at the specified offset in the raw data array. The way the bytes are interpreted depends on
     * the specified {@code ByteOrder}.
     * @param arr the input array.
     * @param off the offset in the data array.
     * @param endian the byte order that specifies how the data will be interpreted.
     * @return the {@code float} value.
     * @see #getInt(byte[], int, java.nio.ByteOrder) 
     * @see Float#intBitsToFloat(int) 
     */
    public static float getFloat(byte[] arr, int off, ByteOrder endian) {
        return Float.intBitsToFloat(getInt(arr, off, endian));
    }
    
    /**
     * Reads the {@code double} value at the specified offset in the raw data array. The way the bytes are interpreted depends
     * on the specified {@code ByteOrder}.
     * @param arr the input array.
     * @param off the offset in the data array.
     * @param endian the byte order that specifies how the data will be interpreted.
     * @return the {@code double} value.
     * @see #getLong(byte[], int, java.nio.ByteOrder) 
     * @see Double#longBitsToDouble(long) 
     */
    public static double getDouble(byte[] arr, int off, ByteOrder endian) {
        return Double.longBitsToDouble(getLong(arr, off, endian));
    }
    
    /**
     * Writes the {@code byte} value at the specified offset in the raw data array.
     * @param arr the output array.
     * @param off the offset in the data array.
     * @param val the {@code byte} value.
     */
    public static void putBytes(byte[] arr, int off, byte val) {
        arr[off] = val;
    }
    
    /**
     * Writes the {@code short} value at the specified offset in the raw data array. The way the bytes are written depends on
     * the specified {@code ByteOrder}.
     * @param arr the output array.
     * @param off the offset in the data array.
     * @param endian the byte order that specifies how the bytes will be written.
     * @param val the {@code short} value.
     */
    public static void putBytes(byte[] arr, int off, ByteOrder endian, short val) {
        if (Objects.requireNonNull(endian) == ByteOrder.BIG_ENDIAN) {
            arr[off++] = (byte)(val >>> 8);
            arr[off  ] = (byte)(val & 0xFF);
        }
        else {
            arr[off++] = (byte)(val & 0xFF);
            arr[off  ] = (byte)(val >>> 8);
        }
    }
    
    /**
     * Writes the {@code char} value at the specified offset in the raw data array. The way the bytes are written depends on the
     * specified {@code ByteOrder}.
     * @param arr the output array.
     * @param off the offset in the data array.
     * @param endian the byte order that specifies how the bytes will be written.
     * @param val the {@code char} value.
     * @see #putBytes(byte[], int, java.nio.ByteOrder, short) 
     */
    public static void putBytes(byte[] arr, int off, ByteOrder endian, char val) {
        putBytes(arr, off, endian, (short)val);
    }
    
    /**
     * Writes the {@code int} value at the specified offset in the raw data array. The way the bytes are written depends on the
     * specified {@code ByteOrder}.
     * @param arr the output array.
     * @param off the offset in the data array.
     * @param endian the byte order that specifies how the bytes will be written.
     * @param val the {@code int} value.
     */
    public static void putBytes(byte[] arr, int off, ByteOrder endian, int val) {
        if (Objects.requireNonNull(endian) == ByteOrder.BIG_ENDIAN) {
            arr[off++] = (byte) (val >>> 24);
            arr[off++] = (byte)((val >>> 16) & 0xFF);
            arr[off++] = (byte)((val >>>  8) & 0xFF);
            arr[off  ] = (byte) (val         & 0xFF);
        }
        else {
            arr[off++] = (byte) (val         & 0xFF);
            arr[off++] = (byte)((val >>>  8) & 0xFF);
            arr[off++] = (byte)((val >>> 16) & 0xFF);
            arr[off  ] = (byte) (val >>> 24);
        }
    }
    
    /**
     * Writes the {@code long} value at the specified offset in the raw data array. The way the bytes are written depends on the
     * specified {@code ByteOrder}.
     * @param arr the output array.
     * @param off the offset in the data array.
     * @param endian the byte order that specifies how the bytes will be written.
     * @param val the {@code long} value.
     */
    public static void putBytes(byte[] arr, int off, ByteOrder endian, long val) {
        if (Objects.requireNonNull(endian) == ByteOrder.BIG_ENDIAN) {
            arr[off++] = (byte) (val >>> 56);
            arr[off++] = (byte)((val >>> 48) & 0xFF);
            arr[off++] = (byte)((val >>> 40) & 0xFF);
            arr[off++] = (byte)((val >>> 32) & 0xFF);
            arr[off++] = (byte)((val >>> 24) & 0xFF);
            arr[off++] = (byte)((val >>> 16) & 0xFF);
            arr[off++] = (byte)((val >>>  8) & 0xFF);
            arr[off  ] = (byte) (val         & 0xFF);
        }
        else {
            arr[off++] = (byte) (val         & 0xFF);
            arr[off++] = (byte)((val >>>  8) & 0xFF);
            arr[off++] = (byte)((val >>> 16) & 0xFF);
            arr[off++] = (byte)((val >>> 24) & 0xFF);
            arr[off++] = (byte)((val >>> 32) & 0xFF);
            arr[off++] = (byte)((val >>> 40) & 0xFF);
            arr[off++] = (byte)((val >>> 48) & 0xFF);
            arr[off  ] = (byte) (val >>> 56);
        }
    }
    
    /**
     * Writes the half-float value at the specified offset in the raw data array. The way the bytes are written depends on the
     * specified {@code ByteOrder}.
     * @param arr the output array.
     * @param off the offset in the data array.
     * @param endian the byte order that specifies how the bytes will be written.
     * @param val the half-float value.
     * @see #putBytes(byte[], int, java.nio.ByteOrder, short) 
     * @see IEEE754Half#floatToShortBits(float) 
     */
    public static void putBytesHalf(byte[] arr, int off, ByteOrder endian, float val) {
        putBytes(arr, off, endian, IEEE754Half.floatToShortBits(val));
    }
    
    /**
     * Writes the {@code float} value at the specified offset in the raw data array. The way the bytes are written depends on
     * the specified {@code ByteOrder}.
     * @param arr the output array.
     * @param off the offset in the data array.
     * @param endian the byte order that specifies how the bytes will be written.
     * @param val the {@code float} value.
     * @see #putBytes(byte[], int, java.nio.ByteOrder, int) 
     * @see Float#floatToIntBits(float) 
     */
    public static void putBytes(byte[] arr, int off, ByteOrder endian, float val) {
        putBytes(arr, off, endian, Float.floatToIntBits(val));
    }
    
    /**
     * Writes the {@code double} value at the specified offset in the raw data array. The way the bytes are written depends on
     * the specified {@code ByteOrder}.
     * @param arr the output array.
     * @param off the offset in the data array.
     * @param endian the byte order that specifies how the bytes will be written.
     * @param val the {@code double} value.
     * @see #putBytes(byte[], int, java.nio.ByteOrder, long) 
     * @see Double#doubleToLongBits(double) 
     */
    public static void putBytes(byte[] arr, int off, ByteOrder endian, double val) {
        putBytes(arr, off, endian, Double.doubleToLongBits(val));
    }
    
    /**
     * Creates a byte array that fits 1 byte and writes the specified {@code byte} value into it.
     * @param val the {@code byte} value.
     * @return the byte array containing the {@code byte} value.
     */
    public static byte[] toBytes(byte val) {
        return new byte[] { val };
    }
    
    /**
     * Creates a byte array that fits 2 bytes and writes the specified {@code short} value into it.
     * @param val the {@code short} value.
     * @param endian the byte order that specifies how the bytes will be written.
     * @return the byte array containing the {@code short} value.
     * @see #putBytes(byte[], int, java.nio.ByteOrder, short) 
     */
    public static byte[] toBytes(short val, ByteOrder endian) {
        byte[] ret = new byte[2];
        putBytes(ret, 0, endian, val);
        return ret;
    }
    
    /**
     * Creates a byte array that fits 2 bytes and writes the specified {@code char} value into it.
     * @param val the {@code char} value.
     * @param endian the byte order that specifies how the bytes will be written.
     * @return the byte array containing the {@code char} value.
     * @see #putBytes(byte[], int, java.nio.ByteOrder, char) 
     */
    public static byte[] toBytes(char val, ByteOrder endian) {
        byte[] ret = new byte[2];
        putBytes(ret, 0, endian, val);
        return ret;
    }
    
    /**
     * Creates a byte array that fits 4 bytes and writes the specified {@code int} value into it.
     * @param val the {@code int} value.
     * @param endian the byte order that specifies how the bytes will be written.
     * @return the byte array containing the {@code int} value.
     * @see #putBytes(byte[], int, java.nio.ByteOrder, int) 
     */
    public static byte[] toBytes(int val, ByteOrder endian) {
        byte[] ret = new byte[4];
        putBytes(ret, 0, endian, val);
        return ret;
    }
    
    /**
     * Creates a byte array that fits 8 bytes and writes the specified {@code long} value into it.
     * @param val the {@code long} value.
     * @param endian the byte order that specifies how the bytes will be written.
     * @return the byte array containing the {@code long} value.
     * @see #putBytes(byte[], int, java.nio.ByteOrder, long) 
     */
    public static byte[] toBytes(long val, ByteOrder endian) {
        byte[] ret = new byte[8];
        putBytes(ret, 0, endian, val);
        return ret;
    }
    
    /**
     * Creates a byte array that fits 4 bytes and writes the specified half-float value into it.
     * @param val the half-float value.
     * @param endian the byte order that specifies how the bytes will be written.
     * @return the byte array containing the half-float value.
     * @see #putBytes(byte[], int, java.nio.ByteOrder, short) 
     * @see IEEE754Half#floatToShortBits(float) 
     */
    public static byte[] toBytesHalf(float val, ByteOrder endian) {
        return toBytes(IEEE754Half.floatToShortBits(val), endian);
    }
    
    /**
     * Creates a byte array that fits 4 bytes and writes the specified {@code float} value into it.
     * @param val the {@code float} value.
     * @param endian the byte order that specifies how the bytes will be written.
     * @return the byte array containing the {@code float} value.
     * @see #putBytes(byte[], int, java.nio.ByteOrder, int) 
     * @see Float#floatToIntBits(float) 
     */
    public static byte[] toBytes(float val, ByteOrder endian) {
        return toBytes(Float.floatToIntBits(val), endian);
    }
    
    /**
     * Creates a byte array that fits 8 bytes and writes the specified {@code double} value into it.
     * @param val the {@code double} value.
     * @param endian the byte order that specifies how the bytes will be written.
     * @return the byte array containing the {@code double} value.
     * @see #putBytes(byte[], int, java.nio.ByteOrder, long) 
     * @see Double#doubleToLongBits(double) 
     */
    public static byte[] toBytes(double val, ByteOrder endian) {
        return toBytes(Double.doubleToLongBits(val), endian);
    }
}
