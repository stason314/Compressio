package com.AI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stanislav on 17.12.2017.
 */
public class Result {

    public List<Question> questions = new ArrayList<>();
    public String name;
    public float pConst;
    public float pCurrent;

    public Result() {
        pCurrent = pConst;
    }
}
