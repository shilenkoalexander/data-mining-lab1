/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iad_1;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Sasha
 */
public class MainFrame extends JFrame {
    private Handler handler = new Handler();
    private static MainFrame instance;
    private JTextPane textPane;
    private JButton createTreeButton;
    private JButton treeShowButton;
    private JButton testTreeButton;
    private JButton pruningButton;
    private JButton clearButton;
    private JButton stopButton;
    private JLabel errorLabel;
    private JLabel errorTestLabel;
    private JCheckBox yesTreeShow;
    private Thread testingThread = null;
    private Frame frame = null;

    public static synchronized MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }

    public MainFrame() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Лабораторная № 1");
        Container contentPane = getContentPane();
        BoxLayout mgr = new BoxLayout(contentPane, BoxLayout.PAGE_AXIS);
        contentPane.setLayout(mgr);
        contentPane.setBackground(new Color(255, 197, 194));

        createTreeButton = new JButton("Обучение");
        createTreeButton.setBackground(new Color(171, 9, 0));
        createTreeButton.setForeground(new Color(251, 249, 235));
        createTreeButton.addActionListener(e -> {
            readData();
            testTreeButton.setEnabled(true);
            treeShowButton.setEnabled(true);
            pruningButton.setEnabled(true);
            yesTreeShow.setEnabled(true);
        });

        treeShowButton = new JButton("Дерево");
        treeShowButton.setEnabled(false);
        treeShowButton.setBackground(new Color(171, 9, 0));
        treeShowButton.setForeground(new Color(251, 249, 235));
        treeShowButton.addActionListener(e -> frame = new Frame(handler.getTree().getRoot()));

        testTreeButton = new JButton("Тест");
        testTreeButton.setEnabled(false);
        testTreeButton.setBackground(new Color(171, 9, 0));
        testTreeButton.setForeground(new Color(251, 249, 235));
        testTreeButton.addActionListener(e -> {
            createTreeButton.setEnabled(false);
            testTreeButton.setEnabled(false);
            treeShowButton.setEnabled(false);
            pruningButton.setEnabled(false);
            yesTreeShow.setEnabled(false);
            stopButton.setEnabled(true);
            testingThread = new Thread(() -> {
                if (yesTreeShow.isSelected()) {
                    MainFrame.getInstance().setVisible(false);
                    frame = new Frame(handler.getTree().getRoot());
                    handler.readData(true, true);
                } else {
                    handler.readData(true, false);
                }
            });
            testingThread.start();

        });
        pruningButton = new JButton("Отсечение ветвей");
        pruningButton.setBackground(new Color(171, 9, 0));
        pruningButton.setForeground(new Color(251, 249, 235));
        pruningButton.setEnabled(false);
        pruningButton.addActionListener(e -> handler.getTree().pruning());

        clearButton = new JButton("Очистить");
        clearButton.setBackground(new Color(171, 9, 0));
        clearButton.setForeground(new Color(251, 249, 235));
        clearButton.addActionListener(e -> textPane.setText(""));

        stopButton = new JButton("Стоп");
        stopButton.setEnabled(false);
        stopButton.addActionListener(e -> stopTesting());
        stopButton.setBackground(new Color(171, 9, 0));
        stopButton.setForeground(new Color(251, 249, 235));

        yesTreeShow = new JCheckBox("Показать дерево");
        yesTreeShow.setEnabled(false);
        yesTreeShow.setSelected(false);

        errorLabel = new JLabel();
        errorTestLabel = new JLabel();

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(createTreeButton);
        buttonPanel.add(treeShowButton);
        buttonPanel.add(pruningButton);
        buttonPanel.add(testTreeButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(yesTreeShow);
        buttonPanel.add(clearButton);
        buttonPanel.add(new JLabel("Ошибка классификации ="));
        buttonPanel.add(errorLabel);
        buttonPanel.add(new JLabel("Ошибка тестирования ="));
        buttonPanel.add(errorTestLabel);

        textPane = new JTextPane();
        JScrollPane jsp = new JScrollPane(textPane);
        contentPane.add(buttonPanel);
        contentPane.add(jsp);

        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    public void readData() {
        handler.readData(false, false);
    }

    public void showMessage(String message) {
        try {
            Document doc = textPane.getDocument();
            doc.insertString(doc.getLength(), message + "\n", null);
        } catch (BadLocationException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void repaintTree() {
        if (frame != null) {
            frame.repaintTreePanel();
        }
    }

    public void setTextToFrame(String text) {
        if (frame != null) {
            frame.setTextToLabel(text);
        }
    }

    public void setError(String error) {
        errorLabel.setText(error);
        repaint();
    }

    public void setErrorTest(String error) {
        errorTestLabel.setText(error);
        repaint();
    }

    public void endTesting() {
        createTreeButton.setEnabled(true);
        testTreeButton.setEnabled(true);
        treeShowButton.setEnabled(true);
        pruningButton.setEnabled(true);
        yesTreeShow.setEnabled(true);
        stopButton.setEnabled(false);
    }

    public void stopTesting() {
        if (testingThread != null) {
            if (testingThread.isAlive()) {
                testingThread.stop();
            }
        }
        endTesting();
    }
}
