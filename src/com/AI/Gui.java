package com.AI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

/**
 * Created by Stanislav on 25.10.2017.
 */
public class Gui {
    private JButton openFileButton;
    private JButton replyButton;
    private JTextField relpyField;
    private JTextArea questionsTextField;
    private JTextArea probabilitiesTextField;
    public JPanel panel;
    private JTextArea authorField;
    private JFileChooser fileChooser;
    private String fileName;
    private String[] questions;

    public File file;

    Parser parser;

    public Gui() {
        openFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser = new JFileChooser();
                int ret = fileChooser.showDialog(null, "Open");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile();
                    if(fileChooser.isFileSelectionEnabled())
                        fileName = file.getPath();
                   appends();

                    System.out.println(fileName);
                }
            }
        });
        authorField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                e.consume();
            }
        });
        questionsTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                e.consume();
            }
        });
        probabilitiesTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                e.consume();
            }
        });
        replyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reload();
            }
        });
    }


    private void appends() {
        parser = new Parser(fileName);
        questions = parser.questions.split("\n");

        authorField.append(parser.authors);
        probabilitiesTextField.append(parser.probability[0].name + "[" + parser.probability[0].pCurrent + "]");
        questionsTextField.append(questions[1]);

    }
    private void reload(){
        probabilitiesTextField.setText("");
        questionsTextField.setText("");
    }
}
