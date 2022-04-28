package com.uldemy.services;

import com.uldemy.exceptions.UnsuportedMathException;
import com.uldemy.utils.MathUtils;
import org.springframework.stereotype.Service;

@Service
public class MathService {

    public Double sum( String numberOne,  String numberTwo){

        if (!MathUtils.isNumeric(numberOne) || !MathUtils.isNumeric(numberTwo) ){
            throw new UnsuportedMathException("please set a numeric value!");
        }

        return MathUtils.convertToDouble(numberOne) + MathUtils.convertToDouble(numberTwo);
    }

    public Double subtraction( String numberOne,  String numberTwo){

        if (!MathUtils.isNumeric(numberOne) || !MathUtils.isNumeric(numberTwo) ){
            throw new UnsuportedMathException("please set a numeric value!");
        }

        return MathUtils.convertToDouble(numberOne) - MathUtils.convertToDouble(numberTwo);
    }

    public Double multiplication( String numberOne,  String numberTwo){

        if (!MathUtils.isNumeric(numberOne) || !MathUtils.isNumeric(numberTwo) ){
            throw new UnsuportedMathException("please set a numeric value!");
        }

        return MathUtils.convertToDouble(numberOne) * MathUtils.convertToDouble(numberTwo);
    }

    public Double division( String numberOne,  String numberTwo){

        if (!MathUtils.isNumeric(numberOne) || !MathUtils.isNumeric(numberTwo) ){
            throw new UnsuportedMathException("please set a numeric value!");
        }

        return MathUtils.convertToDouble(numberOne) / MathUtils.convertToDouble(numberTwo);
    }

    public Double mean( String numberOne,  String numberTwo){

        if (!MathUtils.isNumeric(numberOne) || !MathUtils.isNumeric(numberTwo) ){
            throw new UnsuportedMathException("please set a numeric value!");
        }

        return (MathUtils.convertToDouble(numberOne) + MathUtils.convertToDouble(numberTwo)) / 2;
    }

    public Double squareRoot( String numberOne){

        if (!MathUtils.isNumeric(numberOne) ){
            throw new UnsuportedMathException("please set a numeric value!");
        }

        return Math.sqrt(MathUtils.convertToDouble(numberOne));
    }


}
