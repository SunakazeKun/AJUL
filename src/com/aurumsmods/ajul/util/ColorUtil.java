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

import com.aurumsmods.ajul.lang.IllegalInstantiationException;

/**
 * A utility class that provides methods to convert between ARGB and other non-default color formats.
 * @author Aurum
 */
public final class ColorUtil {
    private ColorUtil() {
        throw new IllegalInstantiationException();
    }
    
    /**
     * Converts the specified 16-bit BGR555 color into a 32-bit ARGB color. The alpha channel value is always set to 255.
     * @param bgr555 the BGR555 color that should be converted.
     * @return the converted BGR555 color in ARGB color format.
     */
    public static int BGR555ToARGB(int bgr555) {
        int b = (bgr555 & 0x7C00) >>> 7;
        int g = (bgr555 & 0x03E0) >>> 2;
        int r = (bgr555 & 0x001F) << 3;
        return 0xFF000000 | (r << 16) | (g << 8) | b;
    }
        
    /**
     * Converts the specified 326-bit ARGB color into a 16-bit BGR555 color. The alpha channel information will be discarded.
     * @param argb the ARGB color that should be converted.
     * @return the converted ARGB color in BGR555 color format.
     */
    public static int ARGBToBGR555(int argb) {
        int b = (argb & 0x0000F8) << 7;
        int g = (argb & 0x00F800) >>> 6;
        int r = (argb & 0xF80000) >>> 19;
        return b | g | r;
    }
}
