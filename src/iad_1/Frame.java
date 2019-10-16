/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iad_1;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Sasha
 */
public class Frame extends JFrame implements WindowListener {

    private TreePanel treePanel;
    private JLabel label = new JLabel("Дерево");
    public Frame(Node root) {
        treePanel = new TreePanel(root);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        this.getContentPane().add(label);
        this.getContentPane().add(treePanel);
        this.setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Дерево");
        addWindowListener(this);
    }

    public void repaintTreePanel() {
        treePanel.repaint();
    }
    public void setTextToLabel(String text)
    {
        label.setText(text);
        repaint();
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        MainFrame.getInstance().setVisible(true);
        MainFrame.getInstance().stopTesting();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
