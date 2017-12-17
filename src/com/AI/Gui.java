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
    public File file;
    Parser parser;
    int num = 1;
    private String[] questions;
    int ques = 0;

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
                int answer = Integer.parseInt(relpyField.getText());
                num++;
                probabilitiesTextField.setText("");
                if (answer == 5){
                    for (int i = 0; i < parser.probability.length; i++){
                        parser.probability[i].pCurrent = (parser.probability[i].questions.get(ques).pPlus * parser.probability[i].pCurrent) /
                                ((parser.probability[i].questions.get(ques).pPlus * parser.probability[i].pCurrent) +
                                (parser.probability[i].questions.get(ques).pMinus * (1 - parser.probability[i].pCurrent))) ;
                    }
                }
                if (answer == -5){
                    for (int i = 0; i < parser.probability.length; i++){
                        parser.probability[i].pCurrent = ((1 - parser.probability[i].questions.get(ques).pPlus) * parser.probability[i].pCurrent) /
                                (((1 - parser.probability[i].questions.get(ques).pPlus) * parser.probability[i].pCurrent) -
                                        (parser.probability[i].questions.get(ques).pMinus * (1 - parser.probability[i].pCurrent))) ;
                    }
                }
                if (answer == 0){
                    for (int i = 0; i < parser.probability.length; i++){
                        parser.probability[i].pCurrent = parser.probability[i].pCurrent;
                    }
                }
                if (answer > -5 && answer < 0){
                    for (int i = 0; i < parser.probability.length; i++){
                        parser.probability[i].pCurrent = (((answer + 5) * (parser.probability[i].pCurrent - parser.probability[i].questions.get(ques).pMinus)) /
                                5) + parser.probability[i].questions.get(ques).pMinus;
                    }
                }
                if (answer > 0 && answer < 5){
                    for (int i = 0; i < parser.probability.length; i++){
                        parser.probability[i].pCurrent = (((answer) * (parser.probability[i].questions.get(ques).pPlus - parser.probability[i].pCurrent)) /
                                5) + parser.probability[i].pCurrent;
                    }
                }
                ques++;
                printProbabilities();
                printQuestion(num);
            }
        });
    }


    private void appends() {
        parser = new Parser(fileName);
        questions = parser.questions.split("\n");

        authorField.append(parser.authors);
        questionsTextField.append(questions[num]);


    }
    private void printProbabilities(){
        for (Result result: parser.probability){
            probabilitiesTextField.append(result.name + "[" + result.pCurrent + "]" + "\n");
        }
    }
    private void printQuestion(int q){
        questionsTextField.setText(questions[q]);
    }
}
