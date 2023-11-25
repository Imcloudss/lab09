package it.unibo.mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private final JFrame frame = new JFrame("My second Java graphical interface");

    public SimpleGUIWithFileChooser(final Controller controller) {
        final JPanel panel1 = new JPanel();
        final JTextArea textArea = new JTextArea();
        final JButton save = new JButton("Save");

        panel1.setLayout(new BorderLayout());
        
        panel1.add(textArea, BorderLayout.CENTER);
        panel1.add(save, BorderLayout.SOUTH);

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

        final JPanel panel2 = new JPanel();
        final JTextField filePath = new JTextField(controller.getDestinationPath());
        final JButton chooseFile = new JButton("Browse...");

        panel2.setLayout(new BorderLayout());

        panel2.add(filePath, BorderLayout.CENTER);
        panel2.add(chooseFile, BorderLayout.LINE_END);

        chooseFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser fc = new JFileChooser("Choose where to save");
                fc.setSelectedFile(controller.getCurrentDestination());
                final int result = fc.showSaveDialog(frame);
                switch (result) {
                case JFileChooser.APPROVE_OPTION:
                    final File newDest = fc.getSelectedFile();
                    controller.setNewDestination(newDest);
                    filePath.setText(newDest.getPath());
                    break;
                case JFileChooser.CANCEL_OPTION:
                    break;
                default:
                    JOptionPane.showMessageDialog(frame, result, "Meh!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel1.add(panel2, BorderLayout.NORTH);

        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new SimpleGUI(new Controller()).display();
    }

}
