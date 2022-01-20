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
package com.aurumsmods.ajul.lang;

/**
 * Provides utility functions to handle IEEE 754 "half format" floating-point values. These solutions were originally created by
 * stackoverflow user x4u. See the respective thread
 * <a href="https://stackoverflow.com/questions/6162651/half-precision-floating-point-in-java">over here</a>.
 * @author Aurum
 */
public final class IEEE754Half {
    private IEEE754Half() {
        throw new IllegalStateException("Instantiation of this class is forbidden!");
    }
    
    /**
     * Minimum exponent a normalized half value may have.
     */
    public static final int MIN_EXPONENT = -14;
    
    /**
     * Maximum exponent a finite half value may have.
     */
    public static final int MAX_EXPONENT = 15;
    
    /**
     * The number of bits used to represent a half value.
     */
    public static final int SIZE = 16;
    
    /**
     * The number of bytes used to represent a half value.
     */
    public static final int BYTES = 2;
    
    /**
     * Returns the {@code float} value corresponding to the given 16-bit representation. The input is a representation of a
     * floating-point value according to the IEEE 754 floating-point "half format" bit layout. Java does not feature a proper
     * primitive data type for this format.
     * @see Float#intBitsToFloat(int) 
     * @param bits a short value that represents the half format.
     * @return the {@code float} floating-point value.
     */
    public static float shortBitsToFloat(short bits) {
        int sign = bits & 0x8000; // 1 bit sign
        int mant = bits & 0x03FF; // 10 bits mantissa
        int exp =  bits & 0x7C00; // 5 bits exponent
        
        if (exp == 0x7C00) // NaN or Inf?
            exp = 0x3FC00;
        else if (exp != 0) // Normalized value
            exp += 0x1C000; // exp - 15 + 127
        else if (mant != 0) { // Normalize subnormal
            exp = 0x1C400;
            
            do {
                mant <<= 1; // mantissa * 2
                exp -= 0x400; // decrease exp by 1
            }
            while((mant & 0x400) == 0);
            
            mant &= 0x3FF; // discard subnormal bit
        }
        
        return Float.intBitsToFloat(sign << 16 | (exp | mant) << 13);
    }
    
    /**
     * Returns a {@code short} value corresponding to the given {@code float} value. The {@code float} will be rounded to fit
     * the allowed range that is applicable by the half-precision floating-point format.
     * @see Float#floatToIntBits(float) 
     * @param h the half-float value.
     * @return a short value that represents the half-float.
     */
    public static short floatToShortBits(float h) {
        int bits = Float.floatToIntBits(h); // float bits
        int sign = bits >>> 16 & 0x8000; // sign only
        int val = (bits & 0x7FFFFFFF) + 0x1000; // rounded value
        
        if (val >= 0x47800000) { // might be or become NaN/Inf
            if ((bits & 0x7FFFFFFF) >= 0x47800000) {
                if (val < 0x7F800000) // value too large -> make it +/-Inf
                    return (short)(sign | 0x7C00);
                else // keep NaN (and Inf) bits
                    return (short)(sign | 0x7C00 | (bits & 0x007FFFFF) >>> 13);
            }
            
            return (short)(sign | 0x7BFF); // unrounded not quite Inf
        }
        else if (val >= 0x38800000) // normalized value
            return (short)(sign | val - 0x38000000 >>> 13); // exp - 127 + 15
        else if (val < 0x33000000) // value too small -> becomes +/-0
            return (short)sign;
        
        // tmp exp for subnormal calc
        val = (bits & 0x7FFFFFFF) >>> 23;
        
        // add subnormal bit, round depending on cut off, div by 2^(1-(exp-127+15)) and >> 13 | exp=0
        return (short)(sign | ((bits & 0x7FFFFF | 0x800000) + (0x800000 >>> val - 102) >>> 126 - val));
    }
}
