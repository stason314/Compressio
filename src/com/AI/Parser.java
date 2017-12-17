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

    private List<String> stringList;
    private  Iterator<String> iterator ;
    private String str = "";
    public String questions;
    public String authors;
    public Result[] probability;
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
            }else
            str += temp + "\n";

        }
        aqp = str.split("/r");
        authors = aqp[0];
        questions = aqp[1];
        probability = parseResults(aqp[2]);


    }


    private Result[] parseResults(String res){
        String[] prob = res.split("\n");
        Result[] results = new Result[prob.length];
        for (int i = 0; i < results.length; i++){
            results[i] = new Result();

            String[] temps = prob[i].split(",");
            results[i].name = temps[0];
            results[i].pConst = Float.parseFloat(temps[1]);
            results[i].pCurrent = results[i].pConst;
            for (int j = 2; j < temps.length; j++){
                j++;
                float pPlus = Float.parseFloat(temps[j]);
                j++;
                float pMinus = Float.parseFloat(temps[j]);
                results[i].questions.add(new Question(pPlus, pMinus));
            }
        }

        System.out.println(results[18].questions.get(5).pMinus);
        return results;
    }
}
