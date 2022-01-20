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
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import java.util.Objects;

/**
 * A binary data input stream that provides readability of primitive data types from an underlying input stream based on the
 * specified {@code ByteOrder}.
 * @author Aurum
 */
public final class BinaryInputStream extends FilterInputStream implements DataInput, IEEE754HalfInput {
    /**
     * The byte order in which the data is stored.
     * @see ByteOrder
     */
    private final ByteOrder endianness;
    
    /**
     * A working buffer that temporarily stores read binary data. Fits up to 8 bytes.
     */
    private final byte[] buffer;
    
    /**
     * Creates a {@code BinaryInputStream} that wraps the underlying input stream; the system's native byte order will be used
     * when reading binary data.
     * @param in the underlying input stream.
     */
    public BinaryInputStream(InputStream in) {
        this(in, ByteOrder.nativeOrder());
    }
    
    /**
     * Creates a {@code BinaryInputStream} that wraps the underlying input stream; it uses the specified {@code ByteOrder} when
     * reading binary data.
     * @param in the underlying input stream.
     * @param endian the endianness of the specified input stream.
     */
    public BinaryInputStream(InputStream in, ByteOrder endian) {
        super(in);
        endianness = Objects.requireNonNull(endian);
        buffer = new byte[8];
    }
    
    /**
     * Returns the byte order of the underlying stream.
     * @return the byte order of the underlying stream.
     */
    public ByteOrder endianness() {
        return endianness;
    }
    
    private void readIntoBuffer(int n) throws IOException {
        int total = in.readNBytes(buffer, 0, n);
        if (total != n)
            throw new EOFException();
    }

    /**
     * Reads the requested number of bytes from the input stream into the given byte array. This method blocks until {@code len}
     * bytes of input data have been read, end of stream is detected, or an exception is thrown. This method does not close the
     * input stream.
     * @see InputStream#readNBytes(byte[], int, int) 
     * @param b the byte array into which the data is read.
     * @throws IOException if an error occurs during reading.
     */
    @Override
    public void readFully(byte[] b) throws IOException {
        in.readNBytes(b, 0, b.length);
    }

    /**
     * Reads the requested number of bytes from the input stream into the given byte array. This method blocks until {@code len}
     * bytes of input data have been read, end of stream is detected, or an exception is thrown. This method does not close the
     * input stream.
     * @see InputStream#readNBytes(byte[], int, int) 
     * @param b the byte array into which the data is read.
     * @param off the start offset in {@code b} at which the data is written.
     * @param len the maximum number of bytes to read.
     * @throws IOException if an error occurs during reading.
     */
    @Override
    public void readFully(byte[] b, int off, int len) throws IOException {
        in.readNBytes(b, off, len);
    }

    /**
     * Skips over and discards exactly {@code n} bytes of data from this input stream. If {@code n} is zero or negative, then no
     * bytes are skipped. This method blocks until {@code n} bytes have been skipped, end of stream is detected, or an exception
     * is thrown.
     * @param n the number of bytes to skip.
     * @return the number of bytes that were skipped.
     * @throws IOException if an error occurs during reading.
     */
    @Override
    public int skipBytes(int n) throws IOException {
        if (n < 1)
            return 0;
        
        int total = 0;
        int cur;

        while ((total < n) && ((cur = (int)in.skip(n - total)) > 0))
            total += cur;

        return total;
    }
    
    /**
     * Reads one byte from the input stream and returns {@code true} if that byte is nonzero and {@code false} if it is
     * zero.
     * @return the {@code boolean} value read.
     * @throws EOFException if this stream reached the end before reading all the bytes.
     * @throws IOException if an error occurs during reading.
     */
    @Override
    public boolean readBoolean() throws IOException {
        int ch = in.read();
        if (ch < 0)
            throw new EOFException();
        return ch != 0;
    }
    
    /**
     * Reads and returns one signed {@code byte} from the input stream.
     * @return the signed {@code byte} value read.
     * @throws EOFException if this stream reached the end before reading all the bytes.
     * @throws IOException if an error occurs during reading.
     */
    @Override
    public byte readByte() throws IOException {
        int ch = in.read();
        if (ch < 0)
            throw new EOFException();
        return (byte)ch;
    }

    /**
     * Reads and returns one unsigned {@code byte} from the input stream.
     * @return the unsigned {@code byte} value read.
     * @throws EOFException if this stream reached the end before reading all the bytes.
     * @throws IOException if an error occurs during reading.
     */
    @Override
    public int readUnsignedByte() throws IOException {
        int ch = in.read();
        if (ch < 0)
            throw new EOFException();
        return ch;
    }
    
    /**
     * Reads and returns one signed {@code short}, which occupies 2 bytes, from the input stream. It reads the bytes into a
     * temporary buffer first and converts them to a {@code short}.
     * @see BitConverter#getShort(byte[], int, java.nio.ByteOrder) 
     * @return the signed {@code short} value read.
     * @throws EOFException if this stream reached the end before reading all the bytes.
     * @throws IOException if an error occurs during reading.
     */
    @Override
    public short readShort() throws IOException {
        readIntoBuffer(2);
        return BitConverter.getShort(buffer, 0, endianness);
    }

    /**
     * Reads and returns one unsigned {@code short}, which occupies 2 bytes, from the input stream. It reads the bytes into a
     * temporary buffer first, converts them to a {@code short} and zero-extends the {@code short} to 32 bits.
     * @see BitConverter#getUnsignedShort(byte[], int, java.nio.ByteOrder) 
     * @return the unsigned {@code short} value read.
     * @throws EOFException if this stream reached the end before reading all the bytes.
     * @throws IOException if an error occurs during reading.
     */
    @Override
    public int readUnsignedShort() throws IOException {
        readIntoBuffer(2);
        return BitConverter.getUnsignedShort(buffer, 0, endianness);
    }
    
    /**
     * Reads and returns one {@code char}, which occupies 2 bytes, from the input stream. It reads the bytes into a
     * temporary buffer first and converts them to a {@code char}.
     * @see BitConverter#getChar(byte[], int, java.nio.ByteOrder) 
     * @return the {@code char} value read.
     * @throws EOFException if this stream reached the end before reading all the bytes.
     * @throws IOException if an error occurs during reading.
     */
    @Override
    public char readChar() throws IOException {
        readIntoBuffer(2);
        return BitConverter.getChar(buffer, 0, endianness);
    }
    
    /**
     * Reads and returns one signed {@code int}, which occupies 4 bytes, from the input stream. It reads the bytes into a
     * temporary buffer first and converts them to an {@code int}.
     * @see BitConverter#getInt(byte[], int, java.nio.ByteOrder) 
     * @return the signed {@code int} value read.
     * @throws EOFException if this stream reached the end before reading all the bytes.
     * @throws IOException if an error occurs during reading.
     */
    @Override
    public int readInt() throws IOException {
        readIntoBuffer(4);
        return BitConverter.getInt(buffer, 0, endianness);
    }
    
    /**
     * Reads and returns one signed {@code long}, which occupies 8 bytes, from the input stream. It reads the bytes into a
     * temporary buffer first and converts them to a {@code long}.
     * @see BitConverter#getLong(byte[], int, java.nio.ByteOrder) 
     * @return the signed {@code long} value read.
     * @throws EOFException if this stream reached the end before reading all the bytes.
     * @throws IOException if an error occurs during reading.
     */
    @Override
    public long readLong() throws IOException {
        readIntoBuffer(8);
        return BitConverter.getLong(buffer, 0, endianness);
    }

    /**
     * Reads and returns one half-{@code float}, which occupies 2 bytes, from the input stream. It reads the bytes into a
     * temporary buffer first and converts them to a half-{@code float}.
     * @see BitConverter#getHalf(byte[], int, java.nio.ByteOrder) 
     * @return the half-{@code float} value read.
     * @throws EOFException if this stream reached the end before reading all the bytes.
     * @throws IOException if an error occurs during reading.
     */
    @Override
    public float readHalf() throws IOException {
        readIntoBuffer(2);
        return BitConverter.getHalf(buffer, 0, endianness);
    }
    
    /**
     * Reads and returns one {@code float}, which occupies 4 bytes, from the input stream. It reads the bytes into a
     * temporary buffer first and converts them to a {@code float}.
     * @see BitConverter#getFloat(byte[], int, java.nio.ByteOrder) 
     * @return the {@code float} value read.
     * @throws EOFException if this stream reached the end before reading all the bytes.
     * @throws IOException if an error occurs during reading.
     */
    @Override
    public float readFloat() throws IOException {
        readIntoBuffer(4);
        return BitConverter.getFloat(buffer, 0, endianness);
    }
    
    /**
     * Reads and returns one {@code double}, which occupies 8 bytes, from the input stream. It reads the bytes into a
     * temporary buffer first and converts them to a {@code double}.
     * @see BitConverter#getDouble(byte[], int, java.nio.ByteOrder) 
     * @return the {@code double} value read.
     * @throws EOFException if this stream reached the end before reading all the bytes.
     * @throws IOException if an error occurs during reading.
     */
    @Override
    public double readDouble() throws IOException {
        readIntoBuffer(8);
        return BitConverter.getDouble(buffer, 0, endianness);
    }
    
    /**
     * Not implemented due to being obsolete. To read lines, the preferred way is to use a {@code BufferedReader} instead.
     * @return n/a
     * @throws IOException n/a
     * @deprecated The preferred way to read lines of text is via the {@code BufferedReader.readLine()} method.
     */
    @Override
    @Deprecated
    public String readLine() throws IOException {
        throw new UnsupportedOperationException("Not implemented. Use BufferedReader.readLine() instead!");
    }
    
    /**
     * Reads a Unicode character string encoded in modified UTF-8 format from the input stream. This string
     * of characters is then returned as a {@code String}.
     * @see DataInputStream#readUTF(java.io.DataInput) 
     * @return the Unicode string read.
     * @throws IOException if an error occurs during reading.
     */
    @Override
    public String readUTF() throws IOException {
        return DataInputStream.readUTF(this);
    }
}
