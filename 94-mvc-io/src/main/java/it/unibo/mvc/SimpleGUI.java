package it.unibo.mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private static final int PROPORTION = 5;
    
    private final JFrame frame = new JFrame();

    public SimpleGUI(final Controller controller) {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());

        final JTextField textField = new JTextField();
        canvas.add(textField, BorderLayout.NORTH);

        final JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        canvas.add(textArea, BorderLayout.CENTER);

        final JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.LINE_AXIS));
        canvas.add(southPanel, BorderLayout.SOUTH);

        final JButton print = new JButton("Print");
        final JButton showHistory = new JButton("Show History");

        southPanel.add(print);
        southPanel.add(showHistory);

        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /**
         * Handlers
         */
        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.setNextString(textField.getText());
                    controller.printCurrentString();
                } catch (IllegalStateException iSE) {
                    JOptionPane.showMessageDialog(frame, iSE, "Error: " + iSE.getMessage(), JOptionPane.ERROR_MESSAGE);
                    iSE.printStackTrace();
                }
            }  
        });

        showHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    textArea.setText(controller.getHistoryOfPrintedStrings().toString());
                } catch (NullPointerException nPE) {
                    JOptionPane.showMessageDialog(frame, nPE, "Error: " + nPE.getMessage(), JOptionPane.ERROR_MESSAGE);
                    nPE.printStackTrace();
                }
            }
        });
        
    }

    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new SimpleGUI(new SimpleController()).display();
    }

}
