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

import java.util.Enumeration;
import java.util.Iterator;

/**
 * Provides an adapter that wraps a given {@code Iterator} as an {@code Enumeration}. This is needed for backwards compatibility
 * in some cases.
 * @param <T> the type of elements returned by this enumeration.
 * @author Aurum
 */
public final class IteratorEnumeration<T> implements Enumeration<T> {
    /**
     * The wrapped {@code Iterator} instance that backs this enumeration.
     */
    private final Iterator<T> iterator;
    
    /**
     * Constructs a new iterator adapter for the specified {@code Iterator}.
     * @param iter the iterator that should be adapted.
     */
    public IteratorEnumeration(Iterator<T> iter) {
        iterator = iter;
    }
    
    /**
     * Tests if the underlying {@code Iterator} has at least one more element.
     * @return {@code true} if this enumeration object contains at least one more element, {@code false} otherwise.
     */
    @Override
    public boolean hasMoreElements() {
        return iterator.hasNext();
    }
    
    /**
     * Returns the next element of the underlying {@code Iterator} if it has at least one more element.
     * @return the next element of the underlying {@code Iterator}.
     */
    @Override
    public T nextElement() {
        return iterator.next();
    }
    
    /**
     * Returns the underlying {@code Iterator}.
     * @return the underlying {@code Iterator}.
     */
    @Override
    public Iterator<T> asIterator() {
        return iterator;
    }
}
