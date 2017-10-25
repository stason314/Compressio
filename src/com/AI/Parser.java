package com.AI;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Stanislav on 25.10.2017.
 */
public class Parser {

    public List<String> stringList;
    private String str = "";

    public Parser(String fileName) {
        try {
            stringList = Files.readAllLines(Paths.get(fileName), Charset.forName("cp1251"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String temp: stringList){
            str += temp + "\n";
        }
        System.out.println(str );
    }
}
