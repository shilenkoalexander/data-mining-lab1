/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iad_1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author Sasha
 */
public class TreePanel extends JPanel {

    private int h = 10;
    private int dx = 200;
    private int dy = 50;
    private Dimension screenSize;
    private Node root;
    private int counter;

    public TreePanel(Node root) {
        this.root = root;
        setVisible(true);
        setBorder(BorderFactory.createLineBorder(Color.black));
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    }

    private Color getRandomColor() {
        Random rand = new Random();

        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        return new Color(r, g, b);
    }

    @Override
    public void paint(Graphics g) {
        counter = 0;
        int x = screenSize.width / 2;
        int y = 15;
        int color_rgb = 55;
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(new GradientPaint(0, 0, new Color(250, 250, 250), 0, getHeight(), new Color(238, 238, 238)));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.RED);
        g2d.fillOval(x, y, h, h);
        g2d.setColor(new Color(color_rgb, color_rgb, color_rgb));
        g2d.drawString("Корень " + root.getMessage(), x + 10, y);
        counter++;
        drawNode(g2d, root, x, y, dx);
    }

    public void drawNode(Graphics2D g, Node node, int x, int y, int d) {
        Color color = Color.BLACK;
        g.setColor(color);
        if (node.getLeft() != null) {
            g.drawLine(x + h / 2, y + h, x - d + h / 2, y + dy);
            if (node.getLeft().getIsFill()) {
                g.setColor(Color.RED);
                g.fillOval(x - d, y + dy, h, h);
            }
            else {
                g.drawOval(x - d, y + dy, h, h);
            }
            g.setColor(Color.BLACK);
            g.drawString(node.getLeft().getMessage(), x - d + h + 5, y + dy + h);
        }
        g.setColor(color);
        if (node.getRight() != null) {
            g.drawLine(x + h / 2, y + h, x + d + h / 2, y + dy);
            if (node.getRight().getIsFill()) {
                g.setColor(Color.RED);
                g.fillOval(x + d, y + dy, h, h);
            }
            else {
                g.drawOval(x + d, y + dy, h, h);
            }
            g.setColor(Color.BLACK);
            g.drawString(node.getRight().getMessage(), x + d + h + 5, y + dy + h);
        }
        if (node.getLeft() != null) {
            drawNode(g, node.getLeft(), x - d, y + dy, d - 30);
        }
        if (node.getRight() != null) {
            drawNode(g, node.getRight(), x + d, y + dy, d - 30);
        }
    }
}
