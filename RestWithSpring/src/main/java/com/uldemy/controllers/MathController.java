package com.uldemy.controllers;

import com.uldemy.exceptions.UnsuportedMathException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sum")
public class MathController {

    @GetMapping("/{numberOne}/{numberTwo}")
    public Double sum(@PathVariable("numberOne") String numberOne,
                      @PathVariable("numberTwo") String numberTwo) throws Exception {

        if (!isNumeric(numberOne) || !isNumeric(numberTwo) ){
            throw new UnsuportedMathException("please set a numeric value!");
        }

        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    private Double convertToDouble(String strNumber) {
        if (strNumber == null) return 0D;
        String number = strNumber.replaceAll(",", ".");
        if (isNumeric(number)) return Double.parseDouble(number);
        return 0D;
    }

    private boolean isNumeric(String strNumber){
        if(strNumber == null) return false;
        String number = strNumber.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

}
