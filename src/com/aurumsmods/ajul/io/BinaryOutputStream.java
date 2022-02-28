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
package com.aurumsmods.ajul.io;

import com.aurumsmods.ajul.lang.BitConverter;
import java.io.DataOutput;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UTFDataFormatException;
import java.nio.ByteOrder;

/**
 * A binary data output stream that provides writability of primitive data types to an underlying input stream based on the
 * specified {@code ByteOrder}.
 * @author Aurum
 */
public final class BinaryOutputStream extends FilterOutputStream implements DataOutput, IEEE754HalfOutput {
    /**
     * The byte order in which the data is stored.
     * @see ByteOrder
     */
    private final ByteOrder endianness;
    
    /**
     * A working buffer that temporarily stores written binary data. Fits up to 8 bytes.
     */
    private final byte[] buffer;
    
    /**
     * Creates a {@code BinaryOutputStream} that wraps an underlying output stream; the system's native byte order will be used
     * when writing binary data.
     * @param out the underlying output stream.
     */
    public BinaryOutputStream(OutputStream out) {
        this(out, ByteOrder.nativeOrder());
    }
    
    /**
     * Creates a {@code BinaryOutputStream} that wraps an underlying output stream; it uses the specified {@code ByteOrder}
     * when writing binary data. If {@code endian} is {@code null}, the system's native byte order will be used.
     * @param out the underlying output stream.
     * @param endian the endianness of the specified input stream.
     */
    public BinaryOutputStream(OutputStream out, ByteOrder endian) {
        super(out);
        endianness = endian;
        buffer = new byte[8];
    }
    
    /**
     * Returns the byte order of the underlying stream.
     * @return the byte order of the underlying stream.
     */
    public ByteOrder endianness() {
        return endianness;
    }

    /**
     * Writes a {@code boolean} value to the output stream. If the value is {@code true}, 1 is written to the output stream, 0
     * otherwise.
     * @param val the {@code boolean} value.
     * @throws IOException if an error occurs during writing.
     */
    @Override
    public void writeBoolean(boolean val) throws IOException {
        out.write(val ? 1 : 0);
    }

    /**
     * Writes a {@code byte} value to the output stream.
     * @param val the {@code byte} value.
     * @throws IOException if an error occurs during writing.
     */
    @Override
    public void writeByte(int val) throws IOException {
        out.write(val);
    }

    /**
     * Writes a {@code short} value, which occupies 2 bytes, to the output stream.
     * @see BitConverter#putBytes(byte[], int, java.nio.ByteOrder, short) 
     * @param val the {@code short} value.
     * @throws IOException if an error occurs during writing.
     */
    @Override
    public void writeShort(int val) throws IOException {
        BitConverter.putBytes(buffer, 0, endianness, (short)val);
        out.write(buffer, 0, 2);
    }

    /**
     * Writes a {@code char} value, which occupies 2 bytes, to the output stream.
     * @see BitConverter#putBytes(byte[], int, java.nio.ByteOrder, char) 
     * @param val the {@code char} value.
     * @throws IOException if an error occurs during writing.
     */
    @Override
    public void writeChar(int val) throws IOException {
        BitConverter.putBytes(buffer, 0, endianness, (char)val);
        out.write(buffer, 0, 2);
    }

    /**
     * Writes an {@code int} value, which occupies 4 bytes, to the output stream.
     * @see BitConverter#putBytes(byte[], int, java.nio.ByteOrder, int) 
     * @param val the {@code int} value.
     * @throws IOException if an error occurs during writing.
     */
    @Override
    public void writeInt(int val) throws IOException {
        BitConverter.putBytes(buffer, 0, endianness, val);
        out.write(buffer, 0, 4);
    }

    /**
     * Writes a {@code long} value, which occupies 8 bytes, to the output stream.
     * @see BitConverter#putBytes(byte[], int, java.nio.ByteOrder, long) 
     * @param val the {@code long} value.
     * @throws IOException if an error occurs during writing.
     */
    @Override
    public void writeLong(long val) throws IOException {
        BitConverter.putBytes(buffer, 0, endianness, val);
        out.write(buffer, 0, 8);
    }

    /**
     * Writes a half-{@code float} value, which occupies 2 bytes, to the output stream.
     * @see BitConverter#putBytesHalf(byte[], int, java.nio.ByteOrder, float) 
     * @param val the half-{@code float} value.
     * @throws IOException if an error occurs during writing.
     */
    @Override
    public void writeHalf(float val) throws IOException {
        BitConverter.putBytesHalf(buffer, 0, endianness, val);
        out.write(buffer, 0, 2);
    }

    /**
     * Writes a {@code float} value, which occupies 4 bytes, to the output stream.
     * @see BitConverter#putBytes(byte[], int, java.nio.ByteOrder, float) 
     * @param val the {@code float} value.
     * @throws IOException if an error occurs during writing.
     */
    @Override
    public void writeFloat(float val) throws IOException {
        BitConverter.putBytes(buffer, 0, endianness, val);
        out.write(buffer, 0, 4);
    }

    /**
     * Writes a {@code double} value, which occupies 8 bytes, to the output stream.
     * @see BitConverter#putBytes(byte[], int, java.nio.ByteOrder, double) 
     * @param val the {@code double} value.
     * @throws IOException if an error occurs during writing.
     */
    @Override
    public void writeDouble(double val) throws IOException {
        BitConverter.putBytes(buffer, 0, endianness, val);
        out.write(buffer, 0, 8);
    }

    /**
     * Writes every character in the given string to the output stream. For every character, only the low-order byte is written.
     * The high-order eight bits of each character are ignored.
     * @param str the string.
     * @throws IOException if an error occurs during writing.
     */
    @Override
    public void writeBytes(String str) throws IOException {
        for (char ch : str.toCharArray())
            out.write((byte)ch);
    }

    /**
     * Writes every character in the given string to the output stream. For each character, two bytes are actually written.
     * @see #writeChar(int) 
     * @param str the string.
     * @throws IOException if an error occurs during writing.
     */
    @Override
    public void writeChars(String str) throws IOException {
        for (char ch : str.toCharArray())
            writeChar(str.charAt(ch));
    }

    /**
     * Writes two bytes of length information to the output stream, followed by the modified UTF-8 representation of every
     * character in the given string. Each character in the string is converted to a group of one, two, or three bytes,
     * depending on the value of the character. The endianness of this  stream will be ignored and all written data is output in
     * big-endian.
     * @see #writeUTF(java.lang.String, java.io.DataOutput) 
     * @param str the string.
     * @throws IOException if an error occurs during writing.
     */
    @Override
    public void writeUTF(String str) throws IOException {
        writeUTF(str, this);
    }
    
    /**
     * Reimplementation of {@link java.io.DataOutputStream#writeUTF(String, java.io.DataOutput)} with public access.
     * @param str the string to be written.
     * @param out the data output.
     * @return the number of written bytes.
     * @throws IOException if any I/O exception occurs.
     */
    public static int writeUTF(String str, DataOutput out) throws IOException {
        // Determine encoded bytes length
        int strlen = str.length();
        int utflen = strlen;
        int totallen = utflen + 2;
        
        for (int i = 0; i < strlen; i++) {
            int ch = str.charAt(i);
            if (ch >= 0x80 || ch == 0)
                utflen += (ch >= 0x800) ? 2 : 1;
        }

        if (utflen > 65535 || utflen < strlen)
            throw new UTFDataFormatException(String.format("Encoded string \"%s ...\" is too long.", str.substring(0, 8)));

        // Create buffer and start writing
        byte[] bytearr = new byte[totallen];

        int count = 0;
        bytearr[count++] = (byte)(utflen >>> 8);
        bytearr[count++] = (byte)(utflen & 0xFF);

        int i = 0;
        for ( ; i < strlen ; i++) { // optimized for initial run of ASCII
            int ch = str.charAt(i);
            if (ch >= 0x80 || ch == 0)
                break;
            bytearr[count++] = (byte)ch;
        }

        for ( ; i < strlen ; i++) {
            int ch = str.charAt(i);
            
            if (ch < 0x80 && ch != 0)
                bytearr[count++] = (byte)ch;
            else if (ch >= 0x800) {
                bytearr[count++] = (byte)(0xE0 | ((ch >> 12) & 0x0F));
                bytearr[count++] = (byte)(0x80 | ((ch >>  6) & 0x3F));
                bytearr[count++] = (byte)(0x80 | ( ch        & 0x3F));
            }
            else {
                bytearr[count++] = (byte)(0xC0 | ((ch >>  6) & 0x1F));
                bytearr[count++] = (byte)(0x80 | ( ch        & 0x3F));
            }
        }
        
        out.write(bytearr, 0, totallen);
        return totallen;
    }
}
