package it.unibo.mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private static final int PROPORTION = 5;

    private final JFrame frame = new JFrame("My first Java graphical interface");

    public SimpleGUI(final Controller controller) {
        final JPanel panel = new JPanel();
        final JTextArea textArea = new JTextArea();
        final JButton save = new JButton("Save");

        panel.setLayout(new BorderLayout());
        
        panel.add(textArea, BorderLayout.CENTER);
        panel.add(save, BorderLayout.SOUTH);

        /*
         * Handlers
         */
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.save(textArea.getText());
                } catch (IOException iOE) {
                    JOptionPane.showMessageDialog(frame, iOE, "Error: " + iOE.getMessage(), JOptionPane.ERROR_MESSAGE);
                    iOE.printStackTrace();
                }
            } 
        });

        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    protected void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new SimpleGUI(new Controller()).display();
    }

}
