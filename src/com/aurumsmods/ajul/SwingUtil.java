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

import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * A utility class that provides helper methods for Java Swing.
 * @author Aurum
 */
public final class SwingUtil {
    private SwingUtil() { throw new IllegalStateException(); }
    
    /**
     * Attempts to load and set the system's look and feel to be used for any form. Any potential exception that may be thrown
     * when trying to set the look and feel will be caught and the error will be printed to the console.
     * @see UIManager#setLookAndFeel(javax.swing.LookAndFeel) 
     * @see UIManager#getSystemLookAndFeelClassName() 
     */
    public static void trySetSystemUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
            System.err.println("Could not set System Look and Feel: " + ex);
        }
    }
    
    /**
     * Shows a message box dialog displaying the specified exception and its details. The exception is also printed to the
     * default error stream.
     * @see JOptionPane#showMessageDialog(java.awt.Component, java.lang.Object, java.lang.String, int) 
     * @param parent the parent component.
     * @param exception the exception.
     * @param title the message box's title.
     */
    public static void showExceptionBox(Component parent, Exception exception, String title) {
        System.err.println(exception);
        JOptionPane.showMessageDialog(parent, exception, title, JOptionPane.ERROR_MESSAGE);
    }
}
