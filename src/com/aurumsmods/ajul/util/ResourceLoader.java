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

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * A resource loader that will retrieve resource streams from a specified {@code Class}' module path.
 * @see Class#getResourceAsStream(java.lang.String) 
 * @author Aurum
 */
public class ResourceLoader {
    /**
     * The source module class.
     */
    private final Class sourceClass;
    
    /**
     * Constructs a new {@code ResourceLoader} using the specified source {@code Class}.
     * @param srcclass the source module class.
     */
    public ResourceLoader(Class srcclass) {
        sourceClass = srcclass;
    }
    
    /**
     * Finds a resource with the given path and returns an input stream holding the resource's data.
     * @see Class#getResourceAsStream(java.lang.String) 
     * @param path the resource's path in the module.
     * @return an input stream containing the resource's data.
     */
    public InputStream openStream(String path) {
        return sourceClass.getResourceAsStream(path);
    }
    
    /**
     * Attempts to read an image from the given path. The parsed image will be returned. If an error occured during reading,
     * {@code null} will be returned instead.
     * @param path the resource's path in the module.
     * @return the parsed image. {@code null} if an error occured during reading.
     */
    public BufferedImage readImage(String path) {
        try {
            return ImageIO.read(openStream(path));
        }
        catch (IOException ex) {
            System.err.println("Couldn't load image: " + path);
            return null;
        }
    }
    
    /**
     * Attempts to read an image icon from the given path. The parsed icon will be returned. If an error occured during reading,
     * {@code null} will be returned instead.
     * @param path the resource's path in the module.
     * @return the parsed icon. {@code null} if an error occured during reading.
     */
    public ImageIcon readIcon(String path) {
        try {
            return new ImageIcon(ImageIO.read(openStream(path)));
        }
        catch (IOException ex) {
            System.err.println("Couldn't load icon: " + path);
            return null;
        }
    }
    
    /**
     * Attempts to read all the bytes from the given path. The read bytes will be returned. If an error occured during reading,
     * {@code null} will be returned instead.
     * @param path the resource's path in the module.
     * @return the read bytes. {@code null} if an error occured during reading.
     */
    public byte[] readBinary(String path) {
        try (InputStream in = openStream(path)) {
            return in.readAllBytes();
        }
        catch (IOException ex) {
            System.err.println("Couldn't load binary: " + path);
            return null;
        }
    }
}
