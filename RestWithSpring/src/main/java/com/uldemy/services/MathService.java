package com.uldemy.services;

import com.uldemy.exceptions.UnsuportedMathException;
import com.uldemy.utils.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MathService {

    @Autowired
    private MathUtils mathUtils;
    public Double sum( String numberOne,  String numberTwo){

        validation(numberOne, numberTwo);
        return mathUtils.convertToDouble(numberOne) + mathUtils.convertToDouble(numberTwo);
    }

    public Double subtraction( String numberOne,  String numberTwo){

        validation(numberOne, numberTwo);
        return mathUtils.convertToDouble(numberOne) - mathUtils.convertToDouble(numberTwo);
    }

    public Double multiplication( String numberOne,  String numberTwo){

        validation(numberOne, numberTwo);
        return mathUtils.convertToDouble(numberOne) * mathUtils.convertToDouble(numberTwo);
    }

    public Double division( String numberOne,  String numberTwo){

        validation(numberOne, numberTwo);
        return mathUtils.convertToDouble(numberOne) / mathUtils.convertToDouble(numberTwo);
    }

    public Double mean( String numberOne,  String numberTwo){

        validation(numberOne, numberTwo);
        return (mathUtils.convertToDouble(numberOne) + mathUtils.convertToDouble(numberTwo)) / 2;
    }

    public Double squareRoot( String numberOne){

        validation(numberOne);
        return Math.sqrt(mathUtils.convertToDouble(numberOne));
    }


    public void validation(String numberOne, String numberTwo){
        if (!mathUtils.isNumeric(numberOne) || !mathUtils.isNumeric(numberTwo) ){
            throw new UnsuportedMathException("please set a numeric value!");
        }
    }

    public void validation(String numberOne){
        if (!mathUtils.isNumeric(numberOne)){
            throw new UnsuportedMathException("please set a numeric value!");
        }
    }
}
