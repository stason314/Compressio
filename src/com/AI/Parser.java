package com.AI;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Stanislav on 25.10.2017.
 */
public class Parser {

    public List<String> stringList;
    private  Iterator<String> iterator ;
    private String str = "";
    public String questions;
    public String authors;
    private String aqp[];

    public Parser(String fileName) {
        try {
            stringList = Files.readAllLines(Paths.get(fileName), Charset.forName("cp1251"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        iterator = stringList.iterator();
        for (String temp: stringList){
            if (temp.equals("")){
                str += "/r";
            }
            str += temp + "\n";

        }
        aqp = str.split("/r");
        authors = aqp[0];
        questions = aqp[1];


        System.out.println(questions );
    }
}
