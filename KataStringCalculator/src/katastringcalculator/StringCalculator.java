/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package katastringcalculator;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author keres
 */
class StringCalculator {

    private String locNumbers = "";
    private String locError   = "";
    
    private ArrayList<String> locDelimiters = new ArrayList<>();

    int add(String numbers) throws Exception {
        locNumbers = numbers;
        
        loadDefaultDelimeters();
        
        findNewDelimiters(locNumbers);
        
        ArrayList<Integer> values = parse(locNumbers);
        
        checkErrorDetected();
        
        return sum(values);
    }
    
    // Base delimiters
    private void loadDefaultDelimeters() {
        locDelimiters.add(",");
        locDelimiters.add("\n");
    }
    
    // Find and add new delimiters
    private void findNewDelimiters(String str) {
        String slashes = "";
        String possibleDelimiter = "";
        for (int i = 0; i < str.length() - 3; i++) {
            slashes = str.substring(i, i+2);
            possibleDelimiter = "" + str.charAt(i+2);
            if(slashes.equals("//") && !isNumeric(possibleDelimiter)) {
                locDelimiters.add(possibleDelimiter);
                
                // Remove "//" if new delimiter was found
                StringBuilder sb = new StringBuilder(str);
                sb.deleteCharAt(i);
                sb.deleteCharAt(i);
                locNumbers = sb.toString();
            }
        }
    }

    // Convert the string into valid numbers
    ArrayList<Integer> parse(String str) {
        ArrayList<Integer> Tmp = new ArrayList();

        if (DelimitersInSequence(str)) {
            locError = "Bad input, delimiters cannot be sequential";
            return null;
        }

        String delimiterSplitList = "";

        // Create list of delimiter for the string to be splittd
        for (int i = 0; i < locDelimiters.size(); i++) {
            delimiterSplitList += locDelimiters.get(i);
            if (i != locDelimiters.size() - 1) {
                delimiterSplitList += "|";
            }
        }

        String numbers[] = str.split(delimiterSplitList);

        if (str.length() == 0) {
            return null;
        }

        // Add the elemnt if is a number
        for (String item : numbers) {
            if(item.equals("")) {
                item = "0";
            }
            if (!isNumeric(item)) {
                locError = "Bad input, found non-digit symbol";
                return null;
            }
                Tmp.add(Integer.parseInt(item.trim()));
        }
        return Tmp;
    }

    private void checkErrorDetected() {
        if (locError.length() > 0) {
            System.out.println("Error detected: " + locError);
        }
    }

    // Check if string is a number
    boolean isNumeric(String s) {
        try {
            double d = Double.parseDouble(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    // Check if delimiter are used as first/last char, or if are sequential
    private boolean checkDelimiterPosition(String str) {
        String first = "" + str.charAt(0);
        String last  = "" + str.charAt((str.length() - 1));

        for (String sep : locDelimiters) {
            if (sep.equals(first) || sep.equals(last)) {
                return false;
            }
        }
        return true;
    }

    // Check if some delimiters are sequential (find 2 non-numbers sequentially)
    private boolean DelimitersInSequence(String str) {
        String firstChar  = "";
        String secondChar = "";
        
        for (int i = 0; i < str.length()-1; i++) {
            firstChar  = "" + str.charAt(i);
            secondChar = "" + str.charAt(i+1);

            if(isDelimiter(firstChar) && isDelimiter(secondChar)) {
                return true;
            }
        }
    
        return false;
    }

    // Check if char/string is a delimiter
    private boolean isDelimiter(String del) {
        for(String sx: locDelimiters) {
            if(del.equals(sx)) {
                return true;
            }
        }
        return false;
    }

    // Sum all values
    int sum(ArrayList<Integer> values) throws Exception {
        if (values != null && values.size() > 0) {
            int res = 0;
            for (int x : values) {
                if(x < 0) {
                    throw new Exception("negatives not allowed: " + x, null); 
                }
                res += x;
            }
            return res;
        }
        return 0;
    }
    
}
