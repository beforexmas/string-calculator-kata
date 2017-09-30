/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package katastringcalculator;

import java.util.ArrayList;

/**
 *
 * @author keres
 */
class StringCalculator {

    public String locError = "";

    int add(String numbers) {
        ArrayList<Integer> values = parse(numbers);
        checkErr();
        return sum(values);
    }

    ArrayList<Integer> parse(String str) {
        ArrayList<Integer> Tmp = new ArrayList();
        String numbers[] = str.split(",");

        if (str.length() == 0) {
            return null;
        }

        for (String item : numbers) {
            if (!isNumeric(item)) {
                locError = "Bad input, found non-digit symbol";
                return null;
            }
            Tmp.add(Integer.parseInt(item.trim()));
        }
        return Tmp;
    }

    private void checkErr() {
        if(locError.length() > 0) {
            System.out.println("Error detected: " + locError);
        }
    }
    
    int sum(ArrayList<Integer> values) {
        if (values != null && values.size() > 0) {
            int res = 0;
            for (int x : values) {
                res += x;
            }
            return res;
        }
        return 0;
    }

    boolean isNumeric(String s) {
        try {
            double d = Double.parseDouble(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
