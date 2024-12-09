/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.border.AbstractBorder;

/**
 *
 * @author rid
 */

    

public class RoundBorder extends AbstractBorder {
    private int radius;

    public RoundBorder(int radius) {
        this.radius = radius;
    }

    @Override
    public Insets getBorderInsets(java.awt.Component c) {
        return new Insets(5, 5, 5, 5); // Define padding
    }

    @Override
    public void paintBorder(java.awt.Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.GRAY); // Border color
        g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius); // Draw the rounded border
    }
}